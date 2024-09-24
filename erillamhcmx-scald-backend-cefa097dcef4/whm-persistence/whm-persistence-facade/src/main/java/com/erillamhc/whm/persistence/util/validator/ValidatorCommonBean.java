package com.erillamhc.whm.persistence.util.validator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.ejb.Singleton;
import javax.inject.Inject;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;

import com.erillamhc.whm.persistence.annotation.Validator;
import com.erillamhc.whm.persistence.i18n.MessageProviderFacade;
import com.erillamhc.whm.persistence.mapper.Mapper;
import com.erillamhc.whm.persistence.util.UtilFacade;
import com.erillamhc.whm.persistence.util.ValidationTypeEnum;
import com.erillamhc.whm.persistence.util.validator.exception.ValidatorException;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ValidatorCommonBean implements Serializable {

    private static final long serialVersionUID = -1265020670064684265L;

    @Inject
    private Mapper mapper;

    @Inject
    private MessageProviderFacade bundle;

    private static final String VALID_PATTERN = "yyyy/MM/dd HH:mm:ss";
    private static final Logger LOGGER = Logger.getLogger(ValidatorCommonBean.class.getName());

    public Boolean validateBean(Object o) throws ValidatorException {
        Boolean isValid = Boolean.FALSE;

        if (UtilFacade.nonNull(o)) {
            isValid = true;
            validateBeanInner(o);
        }
        return isValid;
    }

    private boolean validateBeanInner(Object o) throws ValidatorException {
        boolean isValid = true;
        List<Field> fields = mapper.getFields(o);
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())
                    && field.isAnnotationPresent(Validator.class)) {
                Method findMethodGET = mapper.findMethodGET(field, o);

                if (null == findMethodGET) {
                    throw new ValidatorException(bundle.getString("validator.error.field.notfoud", field.getName()));
                }

                Object valueOrigin = null;
                Validator validator = field.getAnnotation(Validator.class);
                
                try {
                    valueOrigin = findMethodGET.invoke(o);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.log(Level.INFO, bundle.getString("validator.error.field.methodinvoke"), e);
                }
                String fieldName = validator.fieldName();
                if(!UtilFacade.nonEmpty(fieldName)) {
                    fieldName = field.getName();
                }
                LOGGER.info("fieldName: " + fieldName);
                isValid = applyValidationRules(validator, valueOrigin, fieldName);
            }
        }
        return isValid;
    }

    private Boolean applyValidationRulesList(List<?> list, String fieldName) throws ValidatorException {
        Boolean isListValid = Boolean.TRUE;
        if (null == list || list.isEmpty()) {
            throw new ValidatorException(bundle.getString("validator.error.field.list", fieldName));
        }
        for (Object item : list) {
            isListValid = isListValid && validateBean(item);
        }
        return isListValid;
    }

    private Boolean applyValidationRulesInner(Validator validator, String value, String fieldName) throws ValidatorException {
        Boolean isCorrect = validateType(value, validator);
        if (!isCorrect) {
            throw new ValidatorException(bundle.getString("validator.error.rules.data", fieldName, value));
        }

        if (validator.type() != ValidationTypeEnum.DATE
                && !validator.onlyValue()) {
            isCorrect = validateLength(value, validator);
            if (!isCorrect) {
                throw new ValidatorException(bundle.getString("validator.error.rules.length", fieldName, value));
            }
        }
        return isCorrect;
    }

    private Boolean validationInnerObject(Object valueOrigin, String fieldName) throws ValidatorException {
        LOGGER.info("Validating inner object");
        Boolean isValid;
        if (valueOrigin instanceof List) {
            List<?> list = (List<?>) valueOrigin;
            isValid = applyValidationRulesList(list, fieldName);
        } else {
            isValid = validateBean(valueOrigin);
        }
        return isValid;
    }

    private boolean processValueRequired(Validator validator, Object valueOrigin, String fieldName) throws ValidatorException {
        Boolean isValid = Boolean.TRUE;
        if (validator.type() == ValidationTypeEnum.OBJECT) {
            isValid = validationInnerObject(valueOrigin, fieldName);
        } else {
            String value = String.valueOf(valueOrigin);
            if (UtilFacade.nonEmpty(value)) {
                isValid = applyValidationRulesInner(validator, value, fieldName);
            }
        }
        return isValid;
    }

    private Boolean applyValidationRules(Validator validator, Object valueOrigin, String fieldName) throws ValidatorException {
        Boolean isValid = Boolean.TRUE;

        if (validator.required()) {
            if (UtilFacade.nonEmpty(String.valueOf(valueOrigin))) {
                isValid = processValueRequired(validator, valueOrigin, fieldName);
            } else {
                throw new ValidatorException(bundle.getString("validator.error.rules.field.req", fieldName));
            }
        } else if (validator.optional()) {
            if (UtilFacade.nonNull(valueOrigin)) {
                isValid = applyValidationRulesInner(validator, String.valueOf(valueOrigin), fieldName);
            }
        }
        return isValid;
    }

    private Boolean validateLength(String valueOrigin, Validator validator) {
        return !UtilFacade.isNotValidLength(valueOrigin, validator.length());
    }

    private boolean applyValidateNumber(String valueOrigin, String pattern) {
        if (UtilFacade.nonEmpty(pattern)) {
            Pattern p = Pattern.compile(pattern);
            return p.matcher(valueOrigin).find();
        }
        return UtilFacade.isNumber(valueOrigin);
    }

    private Boolean applyValidateDate(String value, String pattern) {
        if (!UtilFacade.nonEmpty(pattern)) {
            pattern = VALID_PATTERN;
        }
        Date date = UtilFacade.getDateByPattern(value, pattern);
        return UtilFacade.nonNull(date);
    }

    private Boolean applyValidatePassword(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(value).find();
    }

    public Boolean validateType(String value, Validator validator) {

        if (UtilFacade.isNull(validator) && UtilFacade.isNull(value)) {
            return Boolean.FALSE;
        }

        ValidationTypeEnum type = validator.type();

        Boolean isValid = Boolean.FALSE;
        switch (type) {
            case ALPHABETIC:
                isValid = UtilFacade.isAlphabetic(value);
                break;
            case ALPHANUMERIC:
                isValid = UtilFacade.isAlphaNumeric(value);
                break;
            case EMAIL:
                isValid = UtilFacade.isEmailValid(value);
                break;
            case NUMERIC:
                isValid = applyValidateNumber(value, validator.format());
                break;
            case DATE:
                isValid = applyValidateDate(value, validator.format());
                break;
            case RFC:
                isValid = UtilFacade.isRfcValido(value);
                break;
            case ALPHANUMERICDOT:
                isValid = UtilFacade.isAlphaNumericWithDot(value);
                break;
            case ANYCHARS:
                isValid = Boolean.TRUE;
                break;
            case PASSWORD:
                isValid = applyValidatePassword(value, validator.format());
                break;
            default:
                LOGGER.info("Option not valid in validateType");
                break;
        }
        return isValid;
    }
}
