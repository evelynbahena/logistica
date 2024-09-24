package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class ProductsDTO {
    
    private Integer id_product;
    private String name;
    private String description;

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
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
