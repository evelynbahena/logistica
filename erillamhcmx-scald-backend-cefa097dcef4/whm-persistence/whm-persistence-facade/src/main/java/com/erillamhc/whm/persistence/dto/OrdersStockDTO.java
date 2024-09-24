package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */

public class OrdersStockDTO {
    private Integer fk_idorder;
    private Integer fk_idstock;
    private Integer quantity;

    public Integer getFk_idorder() {
        return fk_idorder;
    }

    public void setFk_idorder(Integer fk_idorder) {
        this.fk_idorder = fk_idorder;
    }

    public Integer getFk_idstock() {
        return fk_idstock;
    }

    public void setFk_idstock(Integer fk_idstock) {
        this.fk_idstock = fk_idstock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
