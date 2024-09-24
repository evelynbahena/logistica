package com.erillamhc.whm.persistence.util;

/**
 * 
 * @author Ivo Danic G.
 *
 */
public enum ValidationTypeEnum {
    ALPHANUMERIC(1), NUMERIC(2),
    ALPHABETIC(3), EMAIL(4), DATE(5), OBJECT(6),
    RFC(7), ALPHANUMERICDOT(8), ANYCHARS(9),
    PASSWORD(8), BLOB(9), OPTIONAL(10);

    private final Integer value;

    private ValidationTypeEnum(Integer type) {
        this.value = type;
    }

    public Integer getValue() {
        return value;
    }
}
