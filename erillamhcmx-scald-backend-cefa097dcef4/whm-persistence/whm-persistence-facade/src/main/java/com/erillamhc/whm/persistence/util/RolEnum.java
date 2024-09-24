package com.erillamhc.whm.persistence.util;

/**
 *
 * @author Ivo Danic G.
 */
public enum RolEnum {
    TOOLBOX(1), SUPPLIER(2), CLIENT(3), SUPLIER_CLIENT(4);
    
    private final Integer rol;
    
    private RolEnum(Integer rol) {
        this.rol = rol;
    }

    public Integer getRol() {
        return rol;
    }
}
