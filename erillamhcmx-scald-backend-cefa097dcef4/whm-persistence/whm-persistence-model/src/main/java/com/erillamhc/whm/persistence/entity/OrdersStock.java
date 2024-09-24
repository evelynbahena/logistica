package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orders_stock database table.
 * 
 */
@Entity
@Table(name="orders_stock")
@NamedQuery(name="OrdersStock.findAll", query="SELECT o FROM OrdersStock o")
public class OrdersStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdersStockPK id;

	private Integer quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_idorder", insertable = false, updatable = false)
	private Order order;

	//bi-directional many-to-one association to Stock
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_idstock", insertable = false, updatable = false)
	private Stock stock;

	public OrdersStock() {
	}

	public OrdersStockPK getId() {
		return this.id;
	}

	public void setId(OrdersStockPK id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}