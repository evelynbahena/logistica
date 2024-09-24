package com.erillamhc.whm.persistence.dto;

/**
 *
 * @author FERNANDO-FH
 */
public class CategoriesDTO {

    private Integer id_category;
    private String name;
    private String description;
    private Integer fk_parent;

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
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

    public Integer getFk_parent() {
        return fk_parent;
    }

    public void setFk_parent(Integer fk_parent) {
        this.fk_parent = fk_parent;
    }

}
