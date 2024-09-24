package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class AddressBranchOfficeDTO {

    private Integer id_address;
    private String fulladdress;
    private Integer fk_branchofficeid;
    
    public Integer getId_address() {
        return id_address;
    }

    public void setId_address(Integer id_address) {
        this.id_address = id_address;
    }

    public String getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(String fulladdress) {
        this.fulladdress = fulladdress;
    }

    public Integer getFk_branchofficeid() {
        return fk_branchofficeid;
    }

    public void setFk_branchofficeid(Integer fk_branchofficeid) {
        this.fk_branchofficeid = fk_branchofficeid;
    }
    
    
    
}
