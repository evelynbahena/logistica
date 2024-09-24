package com.erillamhc.whm.persistence.dto;

import java.util.Date;


public class ProductsRemmisionDTO {
	
	private Integer id_productremmison;
	private String lot;
	private String unitprice;
	private Integer quantity;
	private Date expirationdate;
	private Integer fk_remissionid;
	private Integer fk_idproduct;
	
	
	public Integer getId_productremmison() {
		return id_productremmison;
	}
	public void setId_productremmison(Integer id_productremmison) {
		this.id_productremmison = id_productremmison;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getExpirationdate() {
		return expirationdate;
	}
	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	public Integer getFk_remissionid() {
		return fk_remissionid;
	}
	public void setFk_remissionid(Integer fk_remissionid) {
		this.fk_remissionid = fk_remissionid;
	}
	public Integer getFk_idproduct() {
		return fk_idproduct;
	}
	public void setFk_idproduct(Integer fk_idproduct) {
		this.fk_idproduct = fk_idproduct;
	}
	
	


}
