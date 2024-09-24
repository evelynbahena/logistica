package com.erillamhc.whm.persistence.dto;

import java.util.Date;

/**
 *
 * @author FERNANDO-FH
 */
public class MovementsDTO {

    private Integer idmovement;
    private Integer fk_userid;
    private String movementtype;
    private String originmovement;
    private Date datemovement;
    private Integer id_entity;
    private String entity;

    public Integer getIdmovement() {
        return idmovement;
    }

    public void setIdmovement(Integer idmovement) {
        this.idmovement = idmovement;
    }

    public Integer getFk_userid() {
        return fk_userid;
    }

    public void setFk_userid(Integer fk_userid) {
        this.fk_userid = fk_userid;
    }

    public String getMovementtype() {
        return movementtype;
    }

    public void setMovementtype(String movementtype) {
        this.movementtype = movementtype;
    }

    public String getOriginmovement() {
        return originmovement;
    }

    public void setOriginmovement(String originmovement) {
        this.originmovement = originmovement;
    }

    public Date getDatemovement() {
        return datemovement;
    }

    public void setDatemovement(Date datemovement) {
        this.datemovement = datemovement;
    }

    public Integer getId_entity() {
        return id_entity;
    }

    public void setId_entity(Integer id_entity) {
        this.id_entity = id_entity;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
    
    
    

}
