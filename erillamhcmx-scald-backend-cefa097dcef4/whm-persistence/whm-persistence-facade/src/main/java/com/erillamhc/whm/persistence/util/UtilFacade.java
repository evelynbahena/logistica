package com.erillamhc.whm.persistence.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/**
 *
 * @author Ivo Danic Garrido
 */
public class UtilFacade {

    public static final Logger LOGGER = Logger.getLogger(UtilFacade.class.getName());
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static final String RS_PATH_IMG = File.separator + "resources" + File.separator + "image";
    public static final String LOCALE_LANGUAGE_ES = "es";
    public static final String LOCALE_COUNTRY_MX = "MX";
    public static final Locale LOCALE_MX = new Locale(LOCALE_LANGUAGE_ES, LOCALE_COUNTRY_MX);
    public static final String PATH_IMG_DEFAULT = "/default.jpg";

    private UtilFacade() {
        //No instance a static class.
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean nonNull(Object object) {
        return !isNull(object);
    }

    public static boolean nonEmpty(String value) {
        return nonNull(value) && !value.isEmpty() && !"null".equals(value);
    }

    public static boolean nonEmptyList(List<?> list) {
        return nonNull(list) && !list.isEmpty();
    }

    public static boolean isAlphabetic(String cadena) {
        Pattern pat = Pattern.compile("[A-Za-z]");
        return pat.matcher(cadena).find();
    }

    public static boolean isAlphaNumeric(String cadena) {
        Pattern pat = Pattern.compile("[A-Za-z0-9]");
        return pat.matcher(cadena).find();
    }

    public static boolean isEmailValid(String email) {
        Pattern pat = Pattern
                .compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9\u002D]+(\\.[A-Za-z0-9\u002D]+)*(\\.[A-Za-z]{2,})$");
        return pat.matcher(email).find();
    }

    public static boolean isNumber(String numero) {
        final Pattern pat = Pattern.compile("^\\d+|\\d+(\\.\\d{1,2})?$");
        return pat.matcher(numero).find();
    }

    public static Date getDateByPattern(String dateStr, String pattern) {
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isRfcValido(String value) {
        final String regex = "/^([A-Z\\u00F1&]{3,4}) ?(?:- ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:- ?)?([A-Z\\d]{2})([A\\d])$/";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).find();
    }

    public static boolean isAlphaNumericWithDot(String cadena) {
        Pattern pattern = Pattern.compile("^[\\w\\.\\s\\-]+$");
        return pattern.matcher(cadena).find();
    }

    public static boolean isNotValidLength(String value, long maxlength) {
        return nonEmpty(value) && value.length() > maxlength;
    }

    public static String nextSessionId() {
        return new BigInteger(130, SECURE_RANDOM).toString(32);
    }

    public static String getPathtImageFromByteArray(byte[] bimage, String path, String name) {
        
        if( isNull(bimage) || bimage.length == 0 || isNull(name)) {
            return PATH_IMG_DEFAULT;
        }
        
        name = name.replaceAll("/", "");
        name = name.replaceAll("\\\\", "");
        
        StringBuilder builder = new StringBuilder(File.separator);
        builder.append(name).append(".jpg");
        
        File file = new File(path + builder.toString());

        if (isNull(file)) {
            return PATH_IMG_DEFAULT;
        }

        if (file.exists() && file.canWrite()) {
            if (file.delete()) {
                LOGGER.log(Level.INFO, "File {0} deleted.", file.getName());
            }
        }
        
        try (InputStream is = new ByteArrayInputStream(bimage)) {
            BufferedImage bufferedImage = ImageIO.read(is);
            ImageIO.write(bufferedImage, "jpg", file);
            LOGGER.log(Level.INFO, "File created: {0}", file.getPath());
            is.close();
        } catch (IOException ex) {
            LOGGER.log(Level.INFO, null, ex);
        }

        return builder.toString();
    }
}
