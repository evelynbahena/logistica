package com.erillamhc.whm.persistence.dto.user;

public class StockProductsDTO {
	private Integer fk_idbranchoffice;
	private Integer fk_idproduct;
	private Integer min;
	private Integer max;
	
	public Integer getFk_idbranchoffice() {
		return fk_idbranchoffice;
	}
	public void setFk_idbranchoffice(Integer fk_idbranchoffice) {
		this.fk_idbranchoffice = fk_idbranchoffice;
	}
	public Integer getFk_idproduct() {
		return fk_idproduct;
	}
	public void setFk_idproduct(Integer fk_idproduct) {
		this.fk_idproduct = fk_idproduct;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	
	
	
}
