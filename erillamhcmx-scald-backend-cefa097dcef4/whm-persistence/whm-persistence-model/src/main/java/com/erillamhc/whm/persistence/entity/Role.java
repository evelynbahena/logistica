package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private Integer idRole;

	private String description;

	private String name;

	//bi-directional many-to-many association to Permission
	@ManyToMany
	@JoinTable(
		name="roles_permissions"
		, joinColumns={
			@JoinColumn(name="id_role")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_permission")
			}
		)
	private List<Permission> permissions;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;

	public Role() {
	}

	public Integer getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
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

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}