package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the shipments database table.
 * 
 */
@Entity
@Table(name="shipments")
@NamedQuery(name="Shipment.findAll", query="SELECT s FROM Shipment s")
public class Shipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_shipment")
	private Integer idShipment;

	private Timestamp dateshipment;

	private String driver;

	private Integer status;

	private String tag;

	//bi-directional many-to-one association to BranchOffice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_destinationid")
	private BranchOffice branchOffice;

	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_orderid")
	private Order order;

	public Shipment() {
	}

	public Integer getIdShipment() {
		return this.idShipment;
	}

	public void setIdShipment(Integer idShipment) {
		this.idShipment = idShipment;
	}

	public Timestamp getDateshipment() {
		return this.dateshipment;
	}

	public void setDateshipment(Timestamp dateshipment) {
		this.dateshipment = dateshipment;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public BranchOffice getBranchOffice() {
		return this.branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}