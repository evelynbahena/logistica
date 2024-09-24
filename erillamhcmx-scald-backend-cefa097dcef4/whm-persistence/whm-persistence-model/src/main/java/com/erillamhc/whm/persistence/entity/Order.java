package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_order")
	private Integer idOrder;

	private Timestamp dateout;

	private Integer status;

	//bi-directional many-to-one association to OrdersStock
	@OneToMany(mappedBy="order")
	private List<OrdersStock> ordersStocks;

	//bi-directional many-to-one association to Shipment
	@OneToMany(mappedBy="order")
	private List<Shipment> shipments;

	public Order() {
	}

	public Integer getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Timestamp getDateout() {
		return this.dateout;
	}

	public void setDateout(Timestamp dateout) {
		this.dateout = dateout;
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
		ordersStock.setOrder(this);

		return ordersStock;
	}

	public OrdersStock removeOrdersStock(OrdersStock ordersStock) {
		getOrdersStocks().remove(ordersStock);
		ordersStock.setOrder(null);

		return ordersStock;
	}

	public List<Shipment> getShipments() {
		return this.shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public Shipment addShipment(Shipment shipment) {
		getShipments().add(shipment);
		shipment.setOrder(this);

		return shipment;
	}

	public Shipment removeShipment(Shipment shipment) {
		getShipments().remove(shipment);
		shipment.setOrder(null);

		return shipment;
	}

}