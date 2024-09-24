package com.erillamhc.whm.persistence.dto;

import java.util.Date;

public class ShipmentsDTO {
	
	private Integer id_shipment;
	private String driver;
	private String tag;
	private Integer status;
	private Date dateshipment;
	private Integer fk_destinationid;
	private Integer fk_orderid;
	
	
	public Integer getId_shipment() {
		return id_shipment;
	}
	public void setId_shipment(Integer id_shipment) {
		this.id_shipment = id_shipment;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDateshipment() {
		return dateshipment;
	}
	public void setDateshipment(Date dateshipment) {
		this.dateshipment = dateshipment;
	}
	public Integer getFk_destinationid() {
		return fk_destinationid;
	}
	public void setFk_destinationid(Integer fk_destinationid) {
		this.fk_destinationid = fk_destinationid;
	}
	public Integer getFk_orderid() {
		return fk_orderid;
	}
	public void setFk_orderid(Integer fk_orderid) {
		this.fk_orderid = fk_orderid;
	}
	
	

}
