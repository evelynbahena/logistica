package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class PermissionsDTO {
    private Integer id_permission;
    private String name;
    private String description;

    public Integer getId_permission() {
        return id_permission;
    }

    public void setId_permission(Integer id_permission) {
        this.id_permission = id_permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
