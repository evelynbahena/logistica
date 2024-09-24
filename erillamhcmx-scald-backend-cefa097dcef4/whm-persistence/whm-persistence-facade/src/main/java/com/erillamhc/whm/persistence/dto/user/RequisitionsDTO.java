package com.erillamhc.whm.persistence.dto.user;

import java.util.Date;

public class RequisitionsDTO {
	
	private Integer id_requisition;
	private Integer status;
	private Date date;
	private Integer fk_destinationid;
	
	
	public Integer getId_requisition() {
		return id_requisition;
	}
	public void setId_requisition(Integer id_requisition) {
		this.id_requisition = id_requisition;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getFk_destinationid() {
		return fk_destinationid;
	}
	public void setFk_destinationid(Integer fk_destinationid) {
		this.fk_destinationid = fk_destinationid;
	}
	
	

}
