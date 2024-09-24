package com.erillamhc.whm.persistence.util;

/**
 *
 * @author Ivo Danic G.
 */
public enum StatusOrderEnum {
    SOLICITADO(1),
    COTIZANDO_PROVEEDOR(2),
    COTIZADO_PROVEEDOR(3),
    COTIZADO_TOOLBOX(4),
    ENVIO_COMPROBANTE(5),
    SOLICITA_ORDEN_COMPRA(6),
    SURTIENDO(7),
    ENVIO_ORDEN(8),
    CONFIRMACION(9);
    
    private final Integer status;
    
    private StatusOrderEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
