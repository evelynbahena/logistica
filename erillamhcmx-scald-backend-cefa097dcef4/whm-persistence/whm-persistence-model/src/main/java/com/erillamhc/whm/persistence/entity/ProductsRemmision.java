package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the products_remmision database table.
 * 
 */
@Entity
@Table(name="products_remmision")
@NamedQuery(name="ProductsRemmision.findAll", query="SELECT p FROM ProductsRemmision p")
public class ProductsRemmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_productremmison")
	private Integer idProductremmison;

	@Temporal(TemporalType.DATE)
	private Date expirationdate;

	private String lot;

	private Integer quantity;

	private double unitprice;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_idproduct")
	private Product product;

	//bi-directional many-to-one association to Remmision
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_remissionid")
	private Remmision remmision;

	public ProductsRemmision() {
	}

	public Integer getIdProductremmison() {
		return this.idProductremmison;
	}

	public void setIdProductremmison(Integer idProductremmison) {
		this.idProductremmison = idProductremmison;
	}

	public Date getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getLot() {
		return this.lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Remmision getRemmision() {
		return this.remmision;
	}

	public void setRemmision(Remmision remmision) {
		this.remmision = remmision;
	}

}