package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stock")
	private Integer idStock;

	private String locationkey;

	private Integer quantity;

	private Integer status;

	//bi-directional many-to-one association to OrdersStock
	@OneToMany(mappedBy="stock")
	private List<OrdersStock> ordersStocks;

	//bi-directional many-to-one association to BranchOffice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_branchofficeid")
	private BranchOffice branchOffice;

	//bi-directional many-to-one association to Remmision
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_remmisionid")
	private Remmision remmision;

	public Stock() {
	}

	public Integer getIdStock() {
		return this.idStock;
	}

	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}

	public String getLocationkey() {
		return this.locationkey;
	}

	public void setLocationkey(String locationkey) {
		this.locationkey = locationkey;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<OrdersStock> getOrdersStocks() {
		return this.ordersStocks;
	}

	public void setOrdersStocks(List<OrdersStock> ordersStocks) {
		this.ordersStocks = ordersStocks;
	}

	public OrdersStock addOrdersStock(OrdersStock ordersStock) {
		getOrdersStocks().add(ordersStock);
		ordersStock.setStock(this);

		return ordersStock;
	}

	public OrdersStock removeOrdersStock(OrdersStock ordersStock) {
		getOrdersStocks().remove(ordersStock);
		ordersStock.setStock(null);

		return ordersStock;
	}

	public BranchOffice getBranchOffice() {
		return this.branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public Remmision getRemmision() {
		return this.remmision;
	}

	public void setRemmision(Remmision remmision) {
		this.remmision = remmision;
	}

}