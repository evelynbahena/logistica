package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stock_products database table.
 * 
 */
@Entity
@Table(name="stock_products")
@NamedQuery(name="StockProduct.findAll", query="SELECT s FROM StockProduct s")
public class StockProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StockProductPK id;

	private Integer max;

	private Integer min;

	//bi-directional many-to-one association to BranchOffice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_idbranchoffice", insertable = false, updatable = false)
	private BranchOffice branchOffice;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_idproduct", insertable = false, updatable = false)
	private Product product;

	public StockProduct() {
	}

	public StockProductPK getId() {
		return this.id;
	}

	public void setId(StockProductPK id) {
		this.id = id;
	}

	public Integer getMax() {
		return this.max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return this.min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public BranchOffice getBranchOffice() {
		return this.branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}