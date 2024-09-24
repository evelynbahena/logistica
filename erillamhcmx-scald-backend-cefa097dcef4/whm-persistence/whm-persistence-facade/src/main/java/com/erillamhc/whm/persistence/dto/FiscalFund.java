package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class FiscalFund {
    private Integer id_fiscalfund;
    private String key;
    private String name;

    public Integer getId_fiscalfund() {
        return id_fiscalfund;
    }

    public void setId_fiscalfund(Integer id_fiscalfund) {
        this.id_fiscalfund = id_fiscalfund;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
