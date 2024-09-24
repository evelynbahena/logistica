package com.erillamhc.whm.persistence.util;

/**
 *
 * @author Ivo Danic G.
 */
public enum NotificationStatusEnum {
    
    ENABLED(1),DISABLED(2), SUSPENDED(3) ,READ(5), NOT_READ(4);
    
    private final Integer status;

    private NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
