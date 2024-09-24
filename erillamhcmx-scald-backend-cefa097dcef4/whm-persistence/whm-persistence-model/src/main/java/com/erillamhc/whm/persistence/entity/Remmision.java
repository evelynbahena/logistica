package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the remmisions database table.
 * 
 */
@Entity
@Table(name="remmisions")
@NamedQuery(name="Remmision.findAll", query="SELECT r FROM Remmision r")
public class Remmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_remmision")
	private Integer idRemmision;

	private Timestamp dateinput;

	private String orderkey;

	private String remissionkey;

	//bi-directional many-to-one association to ProductsRemmision
	@OneToMany(mappedBy="remmision")
	private List<ProductsRemmision> productsRemmisions;

	//bi-directional many-to-one association to FiscalFund
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_fiscalfundid")
	private FiscalFund fiscalFund;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="remmision")
	private List<Stock> stocks;

	public Remmision() {
	}

	public Integer getIdRemmision() {
		return this.idRemmision;
	}

	public void setIdRemmision(Integer idRemmision) {
		this.idRemmision = idRemmision;
	}

	public Timestamp getDateinput() {
		return this.dateinput;
	}

	public void setDateinput(Timestamp dateinput) {
		this.dateinput = dateinput;
	}

	public String getOrderkey() {
		return this.orderkey;
	}

	public void setOrderkey(String orderkey) {
		this.orderkey = orderkey;
	}

	public String getRemissionkey() {
		return this.remissionkey;
	}

	public void setRemissionkey(String remissionkey) {
		this.remissionkey = remissionkey;
	}

	public List<ProductsRemmision> getProductsRemmisions() {
		return this.productsRemmisions;
	}

	public void setProductsRemmisions(List<ProductsRemmision> productsRemmisions) {
		this.productsRemmisions = productsRemmisions;
	}

	public ProductsRemmision addProductsRemmision(ProductsRemmision productsRemmision) {
		getProductsRemmisions().add(productsRemmision);
		productsRemmision.setRemmision(this);

		return productsRemmision;
	}

	public ProductsRemmision removeProductsRemmision(ProductsRemmision productsRemmision) {
		getProductsRemmisions().remove(productsRemmision);
		productsRemmision.setRemmision(null);

		return productsRemmision;
	}

	public FiscalFund getFiscalFund() {
		return this.fiscalFund;
	}

	public void setFiscalFund(FiscalFund fiscalFund) {
		this.fiscalFund = fiscalFund;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setRemmision(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setRemmision(null);

		return stock;
	}

}