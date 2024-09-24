package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the branch_office database table.
 * 
 */
@Entity
@Table(name="branch_office")
@NamedQuery(name="BranchOffice.findAll", query="SELECT b FROM BranchOffice b")
public class BranchOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_branchoffice")
	private Integer idBranchoffice;

	private String name;

	private Integer type;

	//bi-directional many-to-one association to AddressBranchOffice
	@OneToMany(mappedBy="branchOffice")
	private List<AddressBranchOffice> addressBranchOffices;

	//bi-directional many-to-one association to BranchOffice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private BranchOffice branchOffice;

	//bi-directional many-to-one association to BranchOffice
	@OneToMany(mappedBy="branchOffice")
	private List<BranchOffice> branchOffices;

	//bi-directional many-to-one association to Jurisdiction
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_jurisdictionid")
	private Jurisdiction jurisdiction;

	//bi-directional many-to-one association to Shipment
	@OneToMany(mappedBy="branchOffice")
	private List<Shipment> shipments;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="branchOffice")
	private List<Stock> stocks;

	//bi-directional many-to-one association to StockProduct
	@OneToMany(mappedBy="branchOffice")
	private List<StockProduct> stockProducts;

	public BranchOffice() {
	}

	public Integer getIdBranchoffice() {
		return this.idBranchoffice;
	}

	public void setIdBranchoffice(Integer idBranchoffice) {
		this.idBranchoffice = idBranchoffice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<AddressBranchOffice> getAddressBranchOffices() {
		return this.addressBranchOffices;
	}

	public void setAddressBranchOffices(List<AddressBranchOffice> addressBranchOffices) {
		this.addressBranchOffices = addressBranchOffices;
	}

	public AddressBranchOffice addAddressBranchOffice(AddressBranchOffice addressBranchOffice) {
		getAddressBranchOffices().add(addressBranchOffice);
		addressBranchOffice.setBranchOffice(this);

		return addressBranchOffice;
	}

	public AddressBranchOffice removeAddressBranchOffice(AddressBranchOffice addressBranchOffice) {
		getAddressBranchOffices().remove(addressBranchOffice);
		addressBranchOffice.setBranchOffice(null);

		return addressBranchOffice;
	}

	public BranchOffice getBranchOffice() {
		return this.branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public List<BranchOffice> getBranchOffices() {
		return this.branchOffices;
	}

	public void setBranchOffices(List<BranchOffice> branchOffices) {
		this.branchOffices = branchOffices;
	}

	public BranchOffice addBranchOffice(BranchOffice branchOffice) {
		getBranchOffices().add(branchOffice);
		branchOffice.setBranchOffice(this);

		return branchOffice;
	}

	public BranchOffice removeBranchOffice(BranchOffice branchOffice) {
		getBranchOffices().remove(branchOffice);
		branchOffice.setBranchOffice(null);

		return branchOffice;
	}

	public Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public List<Shipment> getShipments() {
		return this.shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public Shipment addShipment(Shipment shipment) {
		getShipments().add(shipment);
		shipment.setBranchOffice(this);

		return shipment;
	}

	public Shipment removeShipment(Shipment shipment) {
		getShipments().remove(shipment);
		shipment.setBranchOffice(null);

		return shipment;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setBranchOffice(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setBranchOffice(null);

		return stock;
	}

	public List<StockProduct> getStockProducts() {
		return this.stockProducts;
	}

	public void setStockProducts(List<StockProduct> stockProducts) {
		this.stockProducts = stockProducts;
	}

	public StockProduct addStockProduct(StockProduct stockProduct) {
		getStockProducts().add(stockProduct);
		stockProduct.setBranchOffice(this);

		return stockProduct;
	}

	public StockProduct removeStockProduct(StockProduct stockProduct) {
		getStockProducts().remove(stockProduct);
		stockProduct.setBranchOffice(null);

		return stockProduct;
	}

}