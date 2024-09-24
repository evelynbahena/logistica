package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_requisition database table.
 * 
 */
@Entity
@Table(name="products_requisition")
@NamedQuery(name="ProductsRequisition.findAll", query="SELECT p FROM ProductsRequisition p")
public class ProductsRequisition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_productrequisition")
	private Integer idProductrequisition;

	private Integer quantity;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_productid")
	private Product product;

	//bi-directional many-to-one association to Requisition
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_requisitionsid")
	private Requisition requisition;

	public ProductsRequisition() {
	}

	public Integer getIdProductrequisition() {
		return this.idProductrequisition;
	}

	public void setIdProductrequisition(Integer idProductrequisition) {
		this.idProductrequisition = idProductrequisition;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Requisition getRequisition() {
		return this.requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

}