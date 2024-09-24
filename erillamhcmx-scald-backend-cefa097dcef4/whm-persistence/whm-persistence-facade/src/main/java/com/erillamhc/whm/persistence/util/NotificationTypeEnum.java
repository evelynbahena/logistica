package com.erillamhc.whm.persistence.util;

/**
 *
 * @author Ivo Danic G.
 */
public enum NotificationTypeEnum {
    
    NORMAL(0), TOOLBOX(1), ORDERS(2), CLIENT(3), SUPPLIER(4);
    
    private final Integer type;

    private NotificationTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
    
}
