package com.erillamhc.whm.persistence.dto;

public class StockDTO {
	
	private Integer id_stock;
	private Integer quantity;
	private String locationkey;
	private Integer status;
	private Integer fk_branchofficeid;
	private Integer fk_remmisionid;
	
	public Integer getId_stock() {
		return id_stock;
	}
	public void setId_stock(Integer id_stock) {
		this.id_stock = id_stock;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getLocationkey() {
		return locationkey;
	}
	public void setLocationkey(String locationkey) {
		this.locationkey = locationkey;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFk_branchofficeid() {
		return fk_branchofficeid;
	}
	public void setFk_branchofficeid(Integer fk_branchofficeid) {
		this.fk_branchofficeid = fk_branchofficeid;
	}
	public Integer getFk_remmisionid() {
		return fk_remmisionid;
	}
	public void setFk_remmisionid(Integer fk_remmisionid) {
		this.fk_remmisionid = fk_remmisionid;
	}
	
	

}
