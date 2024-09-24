package com.erillamhc.whm.persistence.dto;

import java.util.Date;

/**
 *
 * @author FERNANDO-FH
 */
public class OrdersDTO {

    private Integer id_order;
    private Date dateout;
    private int status;

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
