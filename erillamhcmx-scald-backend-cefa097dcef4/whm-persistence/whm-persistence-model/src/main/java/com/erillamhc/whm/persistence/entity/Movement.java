package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the movements database table.
 * 
 */
@Entity
@Table(name="movements")
@NamedQuery(name="Movement.findAll", query="SELECT m FROM Movement m")
public class Movement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idmovement;

	private Timestamp datemovement;

	private String entity;

	@Column(name="id_entity")
	private Integer idEntity;

	private String movementtype;

	private String originmovement;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_userid")
	private User user;

	public Movement() {
	}

	public Integer getIdmovement() {
		return this.idmovement;
	}

	public void setIdmovement(Integer idmovement) {
		this.idmovement = idmovement;
	}

	public Timestamp getDatemovement() {
		return this.datemovement;
	}

	public void setDatemovement(Timestamp datemovement) {
		this.datemovement = datemovement;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Integer getIdEntity() {
		return this.idEntity;
	}

	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	public String getMovementtype() {
		return this.movementtype;
	}

	public void setMovementtype(String movementtype) {
		this.movementtype = movementtype;
	}

	public String getOriginmovement() {
		return this.originmovement;
	}

	public void setOriginmovement(String originmovement) {
		this.originmovement = originmovement;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}