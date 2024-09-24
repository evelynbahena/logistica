package com.erillamhc.whm.persistence.mapper;

import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.erillamhc.whm.persistence.i18n.MessageProviderFacade;
import com.erillamhc.whm.persistence.util.UtilFacade;

import java.io.Serializable;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;

/**
 * Version 1.1 Se agregan operaciones para generar JSON desde un Bean y
 * viceversa. Version 1.2 Mejora el parseo entre objetos y JSON.
 *
 * @author Ivo Danic G.
 * @version 1.3
 * @since 1.0
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class Mapper implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(Mapper.class.getName());
    private static final String METHOD_GET = "get";
    private static final String METHOD_IS = "is";
    private static final String METHOD_SET = "set";
    private static final String PATRON_FECHAS_ENTRADA = "yyyy-MM-dd'T'hh:mm:ss";
    private static final String PATRON_FECHAS_SALIDA = "dd/MM/yyy HH:mm:ss";

    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new HashMap<>();

    private static final List<Class<? extends Annotation>> ANNOTATION_FORBBIDEN = Arrays.asList(
            OneToMany.class, OneToOne.class, ManyToMany.class, ManyToOne.class
    );

    @Inject
    private MessageProviderFacade bundle;

    static {
        PRIMITIVE_WRAPPER_MAP.put(Boolean.TYPE, Boolean.class);
        PRIMITIVE_WRAPPER_MAP.put(Byte.TYPE, Byte.class);
        PRIMITIVE_WRAPPER_MAP.put(Character.TYPE, Character.class);
        PRIMITIVE_WRAPPER_MAP.put(Short.TYPE, Short.class);
        PRIMITIVE_WRAPPER_MAP.put(Integer.TYPE, Integer.class);
        PRIMITIVE_WRAPPER_MAP.put(Long.TYPE, Long.class);
        PRIMITIVE_WRAPPER_MAP.put(Double.TYPE, Double.class);
        PRIMITIVE_WRAPPER_MAP.put(Float.TYPE, Float.class);
        PRIMITIVE_WRAPPER_MAP.put(Void.TYPE, Void.TYPE);
    }

    /**
     * Maps wrapper {@code Class}es to their corresponding primitive types.
     */
    private static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE_MAP = new HashMap<>();

    static {
        for (final Map.Entry<Class<?>, Class<?>> entry : PRIMITIVE_WRAPPER_MAP.entrySet()) {
            final Class<?> primitiveClass = entry.getKey();
            final Class<?> wrapperClass = entry.getValue();
            if (!primitiveClass.equals(wrapperClass)) {
                WRAPPER_PRIMITIVE_MAP.put(wrapperClass, primitiveClass);
            }
        }
    }

    /**
     * Crea un segundo objeto a partir de la informaci?n de un objeto fuente.
     * Las dos clases deben tener el mismo nombre de propiedad para realizar el
     * analisis y generar el objeto de salida.
     *
     * @param <T>
     * @param clazz Tipo de Clase resultante.
     * @param in Objeto que contiene la informaci?n para transformarlo a otro
     * objeto.
     * @return Objeto resultante de la transformaci?n.
     * @throws
     * com.mx.dmlink.toolbox.persistence.mapper.MapperParseObjectException
     */
    @Lock(LockType.READ)
    public <T> T parseBetweenObject(Class<T> clazz, Object in) throws MapperParseObjectException {
        T out;
        try {
            out = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.log(Level.SEVERE, bundle.getString("mapper.error.init.instance"), ex.getMessage());
            throw new MapperParseObjectException(ex);
        }
        if (in != null && out != null) {
            final Map<String, Object> fieldsIn = getMapValueFields(in, true);
            final Map<String, Object> fieldsOut = getMapValueFields(out, true);

            for (Entry<String, Object> entry : fieldsIn.entrySet()) {
                parseValueBetweenObject(fieldsOut, entry, out);
            }
        }
        return out;
    }

    private void parseValueBetweenObject(Map<String, Object> fieldsOut, Entry<String, Object> entry,
            Object out) throws MapperParseObjectException {
        if (fieldsOut.containsKey(entry.getKey()) && entry.getValue() != null
                && out != null) {

            Field fieldOut = findField(out, entry.getKey());

            Object value = entry.getValue();
            if (fieldOut != null && value != null) {
                parseValueObject(fieldOut, value, out, entry);
            }
        }
    }

    private void parseValueObject(Field fieldOut, Object value, Object out, Entry<String, Object> entry) throws MapperParseObjectException {
        Class<?> valueTypeClass = value.getClass();
        Class<?> outTypeClass = fieldOut.getType();

        if (fieldOut.getType() == List.class) {
            final ParameterizedType generic = (ParameterizedType) fieldOut.getGenericType();
            Class<?> classGeneric = (Class<?>) generic.getActualTypeArguments()[0];
            if (!isPrimitiveOrJavaPlatform(classGeneric)) {
                Object valueOut = parseBetweenObjectoList(classGeneric, value);
                setMethodOut(out, outTypeClass, valueOut, entry.getKey());
            } else {
                setMethodOut(out, outTypeClass, value, entry.getKey());
            }
        } else if (outTypeClass != valueTypeClass
                && !isPrimitiveOrJavaPlatform(outTypeClass)) {
            Object valueOut = parseBetweenObject(outTypeClass, value);
            setMethodOut(out, outTypeClass, valueOut, entry.getKey());
        } else {
            setMethodOut(out, outTypeClass, value, entry.getKey());
        }
    }

    private Field findField(Object out, String fieldname) {
        Class<?> classField = out.getClass();
        Boolean notfoundField = Boolean.TRUE;
        Field fieldOut = null;
        while (notfoundField) {
            if (Object.class == classField) {
                notfoundField = Boolean.FALSE;
            } else {
                try {
                    fieldOut = classField.getDeclaredField(fieldname);
                    notfoundField = Boolean.FALSE;
                } catch (NoSuchFieldException fieldException) {
                    classField = classField.getSuperclass();
                    printLogException(fieldException, bundle.getString("mapper.error.field.notfound"));
                }
            }
        }
        return fieldOut;
    }

    private void setMethodOut(Object out, Class<?> fieldOutType, Object value, String field) throws MapperParseObjectException {
        try {
            String camelCase = generateCamelCaseNameMethod(field, METHOD_SET);
            Method methodSet = out.getClass().getMethod(camelCase, fieldOutType);
            methodSet.invoke(out, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.log(Level.INFO, field);
            LOGGER.log(Level.INFO, bundle.getString("mapper.error.method.setnotfound"), ex.getMessage());
            throw new MapperParseObjectException(ex);
        }
    }

    private <T> List<T> parseBetweenObjectoList(Class<T> clazz, Object in) throws MapperParseObjectException {
        List<T> list = null;
        if (in instanceof List<?>) {
            List<?> source = (List<?>) in;
            list = new ArrayList<>();
            for (Object o : source) {
                T t = parseBetweenObject(clazz, o);
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Genera un Mapa con las propiedades y valores que un contenga un objeto.
     *
     * @param object Objeto al cual se le genera el mapa.
     * @param nullable Valor <code>True</code> indica que se incluye los
     * propiedades con valores nulos, caso contrario no se incluye en el mapa.
     * @return Mapa con las propiedades y valor del objeto.
     */
    public Map<String, Object> getMapValueFields(Object object, boolean nullable) {
        final Map<String, Object> mapfields = new HashMap<>();
        if (object != null) {
            List<Field> fields = getFields(object);
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    findValueFields(mapfields, f, object, nullable);
                }
            }
        }
        return mapfields;
    }

    private boolean hasAnnotation(Field field, List<Class<? extends Annotation>> annotations) {
        for (Class<? extends Annotation> annotation : annotations) {
            if (field.getAnnotation(annotation) == null) {
                return false;
            }
        }
        return true;
    }

    public Method findMethodGET(Field field, Object object) {
        String nameField = generateCamelCaseNameMethod(field.getName(), METHOD_GET);
        try {
            return object.getClass().getMethod(nameField);
        } catch (NoSuchMethodException ex) {
            printLogException(ex, bundle.getString("mapper.error.method.getnotfound"));
            try {
                return object.getClass().getMethod(nameField.replaceFirst(METHOD_GET, METHOD_IS));
            } catch (NoSuchMethodException ex1) {
                printLogException(ex1, bundle.getString("mapper.error.method.isnotfound"));
                return null;
            }
        }
    }

    private void findValueFields(Map<String, Object> mapfields, Field f, Object object, boolean nullable) {
        Method method = findMethodGET(f, object);

        if (method != null) {
            Object value = null;

            try {
                value = method.invoke(object);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                printLogException(ex, bundle.getString("mapper.error.method.invoke"));
            }
            String field = f.getName();

            if (nullable) {
                mapfields.put(field, value);
            } else {
                if (value != null) {
                    mapfields.put(field, value);
                }
            }
        }

    }

    /**
     * Genera un <code>String</code> con formato camelCase para el nombre de
     * campos
     *
     * @param fieldName Nombre del campo a generar en camelCase
     * @param typeMethod Prefijo que concatena para el nombre del campo
     * @return String con el formato calmelCase. Ejemplo: getPropiedadUno
     */
    private String generateCamelCaseNameMethod(String fieldName, String typeMethod) {
        if (fieldName != null) {
            final StringBuilder camelCase = new StringBuilder(typeMethod);
            camelCase.append(fieldName.substring(0, 1).toUpperCase());
            camelCase.append(fieldName.substring(1));
            return camelCase.toString();
        }
        return "";
    }

    private List<Field> filterAnnotationField(Field[] fields) {
        List<Field> fs = new ArrayList<>();
        for (Field field : fields) {
            if (!hasAnnotation(field, ANNOTATION_FORBBIDEN)) {
                fs.add(field);
            }
        }
        return fs;
    }

    /**
     * Genera una lista de los campos que tiene declarado un objeto.
     *
     * @param object Objeto al que se le genera la lista.
     * @return Lista de objeto de tipo <code>java.lang.Field</code>
     */
    public List<Field> getFields(Object object) {
        final List<Field> fields = new ArrayList<>();
        if (object != null) {
            Class<?> classField = object.getClass();
            boolean notfoundField = Boolean.TRUE;
            while (notfoundField) {
                if (Object.class == classField) {
                    notfoundField = Boolean.FALSE;
                } else {
                    fields.addAll(filterAnnotationField(classField.getDeclaredFields()));
                    classField = classField.getSuperclass();
                }
            }
        }
        return fields;
    }

    /**
     * Genera un JSON apartir de un objeto.
     *
     * @param object Objeto que contiene la informaci?n para generar el JSON.
     * @return Regresa <code>String</code> del JSON.
     * @throws com.mx.dmlink.toolbox.persistence.mapper.MapperParseJSONException
     */
    @Lock(LockType.READ)
    public String parseObjectToJSON(Object object) throws MapperParseJSONException {
        JsonObjectBuilder job = innerParseObjectToJSON(object);
        return job.build().toString();
    }

    /**
     * Genera un JSON de acuerdo al JSR 353.
     *
     * @param object Objeto que contiene la informaci?n para generar el JSON.
     * @return Regresa <code>JsonObjectBuilder</code>
     * @throws Exception
     */
    private JsonObjectBuilder innerParseObjectToJSON(Object object) throws MapperParseJSONException {
        final JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder objectBuild = factory.createObjectBuilder();
        if (object != null) {
            final Map<String, Object> fields = getMapValueFields(object, false);

            for (Entry<String, Object> f : fields.entrySet()) {
                parseValueFromObject(f, objectBuild, factory);
            }
        }
        return objectBuild;
    }

    private void parseValueInnerFromObjectJSON(Entry<String, Object> f, JsonObjectBuilder objectBuild) throws MapperParseJSONException {
        Object value = f.getValue();
        Class<?> valueClass = value.getClass();
        if (!isPrimitiveOrJavaPlatform(valueClass)) {
            JsonObjectBuilder job = innerParseObjectToJSON(value);
            objectBuild.add(f.getKey(), job);
        } else if (valueClass.getSuperclass() == Number.class) {
            objectBuild.add(f.getKey(), new BigDecimal(String.valueOf(value)));
        } else if (valueClass == Date.class) {
            Date date = (Date) value;
            final SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHAS_SALIDA);
            objectBuild.add(f.getKey(), sdf.format(date));
        } else if (valueClass == Boolean.class) {
            objectBuild.add(f.getKey(), Boolean.parseBoolean(String.valueOf(value)));
        } else {
            objectBuild.add(f.getKey(), String.valueOf(value));
        }
    }

    private void processJSONForArray(Object value, JsonArrayBuilder arrayBuilder) throws MapperParseJSONException {
        int length = Array.getLength(value);
        for (int i = 0; i < length; i++) {
            Object getValue = Array.get(value, i);
            parseValueJSONForArray(getValue, arrayBuilder);
        }
    }

    private void parseValueJSONForArray(Object value, JsonArrayBuilder arrayBuilder) throws MapperParseJSONException {
        Class<?> valueClass = value.getClass();
        if (!isPrimitiveOrJavaPlatform(valueClass)) {
            JsonObjectBuilder job = innerParseObjectToJSON(value);
            arrayBuilder.add(job);
        } else if (valueClass.getSuperclass() == Number.class) {
            arrayBuilder.add(new BigDecimal(String.valueOf(value)));
        } else if (valueClass == Date.class) {
            Date date = (Date) value;
            final SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHAS_SALIDA);
            arrayBuilder.add(sdf.format(date));
        } else if (valueClass == Boolean.class) {
            arrayBuilder.add(Boolean.parseBoolean(String.valueOf(value)));
        } else {
            arrayBuilder.add(String.valueOf(value));
        }
    }

    private void parseValueFromObject(Entry<String, Object> f, JsonObjectBuilder objectBuild,
            JsonBuilderFactory factory) throws MapperParseJSONException {
        Object value = f.getValue();
        if (value != null) {
            Class<?> valueClass = value.getClass();
            if (valueClass.isArray()) {
                JsonArrayBuilder createArrayBuilder = factory.createArrayBuilder();
                processJSONForArray(f.getValue(), createArrayBuilder);
                objectBuild.add(f.getKey(), createArrayBuilder);
            } else if (value instanceof List<?>) {
                List<?> list = (List<?>) value;
                JsonArrayBuilder arrayBuild = factory.createArrayBuilder();
                for (Object o : list) {
                    JsonObjectBuilder job = innerParseObjectToJSON(o);
                    arrayBuild.add(job);
                }

                objectBuild.add(f.getKey(), arrayBuild);
            } else {
                parseValueInnerFromObjectJSON(f, objectBuild);
            }
        }
    }

    /**
     * Genera un <code>Object</code> apartir de un JSON. La desearilizacion es
     * de acuerdo al JSR 353.
     *
     * @param <T>
     * @param clazz Clase base que es hom?logo al JSON Data.
     * @param json JSON Data que contiene la informaci?n.
     * @return Objeto generado apartir del <code>Class</code>.
     * @throws com.mx.dmlink.toolbox.persistence.mapper.MapperParseJSONException
     */
    public <T> T parseJSONToObject(Class<T> clazz, String json) throws MapperParseJSONException {
        T o;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            LOGGER.log(Level.INFO, bundle.getString("mapper.error.init.instance"), ex.getMessage());
            throw new MapperParseJSONException(ex);
        }
        if (json != null && !json.isEmpty() && o != null) {
            try (StringReader sreader = new StringReader(json)) {
                JsonReader reader = Json.createReader(sreader);
                final List<Field> fields = getFields(o);

                JsonObject jsonObject = reader.readObject();
                for (Field f : fields) {
                    parseValueFromJSON(f, jsonObject, o);
                }

            } catch (SecurityException | IllegalArgumentException ex) {
                LOGGER.log(Level.INFO, bundle.getString("mapper.error.json.parse"), ex.getMessage());
                throw new MapperParseJSONException(ex);
            }
        }
        return o;
    }

    private void parseValueFromJSON(Field f, JsonObject jsonObject, Object out) throws MapperParseJSONException {
        String fieldName = f.getName();
        if (jsonObject.containsKey(fieldName) && !jsonObject.isNull(fieldName)) {
            try {
                String camelCase = generateCamelCaseNameMethod(f.getName(), METHOD_SET);
                Method methodSet = out.getClass().getMethod(camelCase, f.getType());

                if (methodSet != null) {
                    setValueFromJSON(methodSet, jsonObject, f, out);
                }
            } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
                throw new MapperParseJSONException(ex);
            }
        }
    }

    private void setValueFromJSON(Method methodSet, JsonObject jsonObject, Field f, Object out) throws MapperParseJSONException {
        JsonValue jsonValue = jsonObject.get(f.getName());
        ValueType vtype = jsonValue.getValueType();
        Object value = null;

        switch (vtype) {
            case STRING:
                value = parseJSONString(f, jsonObject);
                break;
            case NUMBER:
                value = parseJSONNumber(f, jsonObject);
                break;
            case ARRAY:
                if (f.getType() == List.class) {
                    value = parseJSONArray(f, jsonObject);
                }
                break;
            case OBJECT:
                JsonObject jObject = jsonObject.getJsonObject(f.getName());
                value = parseJSONToObject(f.getType(), jObject.toString());
                break;
            case FALSE:
            case TRUE:
                value = jsonObject.getBoolean(f.getName());
                break;
            case NULL:
                break;
            default:
                LOGGER.info("No type found");
                break;
        }
        try {
            methodSet.invoke(out, value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new MapperParseJSONException(ex);
        }
    }

    private Object parseJSONArray(Field f, JsonObject jsonObject) throws MapperParseJSONException {
        final ParameterizedType generic = (ParameterizedType) f.getGenericType();
        Class<?> classGeneric = (Class<?>) generic.getActualTypeArguments()[0];
        JsonArray array = jsonObject.getJsonArray(f.getName());
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            list.add(parseJSONToObject(classGeneric, array.get(i).toString()));
        }
        return list;
    }

    private Object parseJSONString(Field f, JsonObject jsonObject) throws MapperParseJSONException {
        if (f.getType() == Date.class) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHAS_ENTRADA, UtilFacade.LOCALE_MX);
                return sdf.parse(jsonObject.getString(f.getName()));
            } catch (ParseException ex) {
                throw new MapperParseJSONException(ex);
            }
        } else {
            return jsonObject.getString(f.getName());
        }
    }

    private Object parseJSONNumber(Field f, JsonObject jsonObject) {
        Object svalue = jsonObject.get(f.getName());
        BigDecimal decimal = new BigDecimal(String.valueOf(svalue));
        if (f.getType() == Integer.class) {
            return decimal.intValue();
        }
        return decimal;
    }

    /**
     * Returns whether the given {@code type} is a primitive or primitive
     * wrapper ({@link Boolean}, {@link Byte}, {@link Character},
     * {@link Short}, {@link Integer}, {@link Long}, {@link Double}, {@link Float}).
     *
     * @param type The class to query or null.
     * @return true if the given {@code type} is a primitive or primitive
     * wrapper ({@link Boolean}, {@link Byte}, {@link Character},
     *         {@link Short}, {@link Integer}, {@link Long}, {@link Double}, {@link Float}).
     * @since 3.1
     */
    private boolean isPrimitiveOrWrapper(final Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isPrimitive() || isPrimitiveWrapper(type);
    }

    /**
     * Returns whether the given {@code type} is a primitive wrapper ({@link Boolean}, {@link Byte}, {@link Character}, {@link Short},
     * {@link Integer}, {@link Long}, {@link Double}, {@link Float}).
     *
     * @param type The class to query or null.
     * @return true if the given {@code type} is a primitive wrapper ({@link Boolean}, {@link Byte}, {@link Character}, {@link Short},
     *         {@link Integer}, {@link Long}, {@link Double}, {@link Float}).
     * @since 3.1
     */
    private boolean isPrimitiveWrapper(final Class<?> type) {
        return WRAPPER_PRIMITIVE_MAP.containsKey(type);
    }

    private boolean isPrimitiveOrJavaPlatform(final Class<?> type) {
        return isPrimitiveOrWrapper(type) || isJavaPlatform(type);
    }

    private boolean isJavaPlatform(final Class<?> type) {
        if (type != null && type.getPackage() != null) {
            String spec = type.getPackage().getSpecificationTitle();
            return spec == null ? false : spec.contains("Java");
        }
        return false;
    }

    /**
     * Utilza el log del sistema para mostrar una excepcion.
     *
     * @param throwable Excepcion a mostrar en log.
     * @param message Mensaje de referencia a la excepcion
     */
    private void printLogException(Throwable throwable, String message) {
        LOGGER.log(Level.INFO, message, throwable.getMessage());
    }
}
