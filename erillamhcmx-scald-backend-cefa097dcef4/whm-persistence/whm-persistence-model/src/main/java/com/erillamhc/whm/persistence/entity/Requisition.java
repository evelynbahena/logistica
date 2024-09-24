package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the requisitions database table.
 * 
 */
@Entity
@Table(name="requisitions")
@NamedQuery(name="Requisition.findAll", query="SELECT r FROM Requisition r")
public class Requisition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_requisition")
	private Integer idRequisition;

	private Timestamp date;

	private Integer status;

	//bi-directional many-to-one association to ProductsRequisition
	@OneToMany(mappedBy="requisition")
	private List<ProductsRequisition> productsRequisitions;

	//bi-directional many-to-one association to Bidding
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_destinationid")
	private Bidding bidding;

	public Requisition() {
	}

	public Integer getIdRequisition() {
		return this.idRequisition;
	}

	public void setIdRequisition(Integer idRequisition) {
		this.idRequisition = idRequisition;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ProductsRequisition> getProductsRequisitions() {
		return this.productsRequisitions;
	}

	public void setProductsRequisitions(List<ProductsRequisition> productsRequisitions) {
		this.productsRequisitions = productsRequisitions;
	}

	public ProductsRequisition addProductsRequisition(ProductsRequisition productsRequisition) {
		getProductsRequisitions().add(productsRequisition);
		productsRequisition.setRequisition(this);

		return productsRequisition;
	}

	public ProductsRequisition removeProductsRequisition(ProductsRequisition productsRequisition) {
		getProductsRequisitions().remove(productsRequisition);
		productsRequisition.setRequisition(null);

		return productsRequisition;
	}

	public Bidding getBidding() {
		return this.bidding;
	}

	public void setBidding(Bidding bidding) {
		this.bidding = bidding;
	}

}