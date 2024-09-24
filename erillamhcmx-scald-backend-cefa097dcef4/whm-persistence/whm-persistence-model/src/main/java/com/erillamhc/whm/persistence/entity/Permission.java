package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permission")
	private Integer idPermission;

	private String description;

	private String name;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="permissions")
	private List<Role> roles;

	public Permission() {
	}

	public Integer getIdPermission() {
		return this.idPermission;
	}

	public void setIdPermission(Integer idPermission) {
		this.idPermission = idPermission;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}