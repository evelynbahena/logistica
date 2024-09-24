package com.erillamhc.whm.persistence.dto.user;

public class Users {
	
	private Integer id_user;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private Boolean available;
	private Integer id_role;
	
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Integer getId_role() {
		return id_role;
	}
	public void setId_role(Integer id_role) {
		this.id_role = id_role;
	}
	
	
	

}
