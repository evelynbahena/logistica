package com.erillamhc.whm.persistence.dto.user;

public class ProductsRequisitionDTO {
	
	private Integer id_productrequisition;
	private Integer quantity;
	private Integer fk_productid;
	private Integer fk_requisitionsid;
	
	
	public Integer getId_productrequisition() {
		return id_productrequisition;
	}
	public void setId_productrequisition(Integer id_productrequisition) {
		this.id_productrequisition = id_productrequisition;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFk_productid() {
		return fk_productid;
	}
	public void setFk_productid(Integer fk_productid) {
		this.fk_productid = fk_productid;
	}
	public Integer getFk_requisitionsid() {
		return fk_requisitionsid;
	}
	public void setFk_requisitionsid(Integer fk_requisitionsid) {
		this.fk_requisitionsid = fk_requisitionsid;
	}
	
	


}
