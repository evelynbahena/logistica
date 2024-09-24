package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the biddings database table.
 * 
 */
@Entity
@Table(name="biddings")
@NamedQuery(name="Bidding.findAll", query="SELECT b FROM Bidding b")
public class Bidding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bidding")
	private Integer idBidding;

	private String name;

	//bi-directional many-to-one association to Requisition
	@OneToMany(mappedBy="bidding")
	private List<Requisition> requisitions;

	//bi-directional many-to-many association to Supplier
	@ManyToMany(mappedBy="biddings")
	private List<Supplier> suppliers;

	public Bidding() {
	}

	public Integer getIdBidding() {
		return this.idBidding;
	}

	public void setIdBidding(Integer idBidding) {
		this.idBidding = idBidding;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Requisition> getRequisitions() {
		return this.requisitions;
	}

	public void setRequisitions(List<Requisition> requisitions) {
		this.requisitions = requisitions;
	}

	public Requisition addRequisition(Requisition requisition) {
		getRequisitions().add(requisition);
		requisition.setBidding(this);

		return requisition;
	}

	public Requisition removeRequisition(Requisition requisition) {
		getRequisitions().remove(requisition);
		requisition.setBidding(null);

		return requisition;
	}

	public List<Supplier> getSuppliers() {
		return this.suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

}