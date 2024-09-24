package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class BranchOfficeDTO {
    
     private Integer id_branchoffice;
     private String name;
     private int type;
     private Integer fk_jurisdictionid;
     private  Integer parent;

    public Integer getId_branchoffice() {
        return id_branchoffice;
    }

    public void setId_branchoffice(Integer id_branchoffice) {
        this.id_branchoffice = id_branchoffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getFk_jurisdictionid() {
        return fk_jurisdictionid;
    }

    public void setFk_jurisdictionid(Integer fk_jurisdictionid) {
        this.fk_jurisdictionid = fk_jurisdictionid;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
     
     
     
}
