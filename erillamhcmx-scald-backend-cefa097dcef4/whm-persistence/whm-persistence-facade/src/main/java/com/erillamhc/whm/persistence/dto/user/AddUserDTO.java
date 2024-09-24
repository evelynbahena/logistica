package com.erillamhc.whm.persistence.dto.user;

public class AddUserDTO extends AddNameDTO {
	
	private int id_user;

	private String email;
	
	private String password;
	
	private Boolean status;
	
	private int  fk_id_role;
	
	private int fk_id_branch_office;
	
	private int fk_id_name;
	

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getFk_id_role() {
		return fk_id_role;
	}

	public void setFk_id_role(int fk_id_role) {
		this.fk_id_role = fk_id_role;
	}

	public int getFk_id_branch_office() {
		return fk_id_branch_office;
	}

	public void setFk_id_branch_office(int fk_id_branch_office) {
		this.fk_id_branch_office = fk_id_branch_office;
	}

	public int getFk_id_name() {
		return fk_id_name;
	}

	public void setFk_id_name(int fk_id_name) {
		this.fk_id_name = fk_id_name;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	


	
	



	

}
