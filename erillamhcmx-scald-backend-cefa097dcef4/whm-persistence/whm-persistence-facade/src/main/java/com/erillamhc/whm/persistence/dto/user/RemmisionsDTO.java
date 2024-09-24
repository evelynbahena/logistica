package com.erillamhc.whm.persistence.dto.user;

import java.util.Date;

public class RemmisionsDTO {
	
	private Integer id_remmision;
	private String orderkey;
	private String remissionkey;
	private Date dateinput;
	private Integer fk_fiscalfundid;
	
	
	public Integer getId_remmision() {
		return id_remmision;
	}
	public void setId_remmision(Integer id_remmision) {
		this.id_remmision = id_remmision;
	}
	public String getOrderkey() {
		return orderkey;
	}
	public void setOrderkey(String orderkey) {
		this.orderkey = orderkey;
	}
	public String getRemissionkey() {
		return remissionkey;
	}
	public void setRemissionkey(String remissionkey) {
		this.remissionkey = remissionkey;
	}
	public Date getDateinput() {
		return dateinput;
	}
	public void setDateinput(Date dateinput) {
		this.dateinput = dateinput;
	}
	public Integer getFk_fiscalfundid() {
		return fk_fiscalfundid;
	}
	public void setFk_fiscalfundid(Integer fk_fiscalfundid) {
		this.fk_fiscalfundid = fk_fiscalfundid;
	}
	
	

	
	

}
