package com.erillamhc.whm.persistence.dto;

public class UsersDTO {

	private String name;
	private String lastname;
	private String email;
	private String password;
	private boolean available;
	private int id_role;
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getId_role() {
		return id_role;
	}
	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	
	
	
}
