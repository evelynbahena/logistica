package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the orders_stock database table.
 * 
 */
@Embeddable
public class OrdersStockPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="fk_idorder", insertable=false, updatable=false)
	private Integer fkIdorder;

	@Column(name="fk_idstock", insertable=false, updatable=false)
	private Integer fkIdstock;

	public OrdersStockPK() {
	}
	public Integer getFkIdorder() {
		return this.fkIdorder;
	}
	public void setFkIdorder(Integer fkIdorder) {
		this.fkIdorder = fkIdorder;
	}
	public Integer getFkIdstock() {
		return this.fkIdstock;
	}
	public void setFkIdstock(Integer fkIdstock) {
		this.fkIdstock = fkIdstock;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdersStockPK)) {
			return false;
		}
		OrdersStockPK castOther = (OrdersStockPK)other;
		return 
			this.fkIdorder.equals(castOther.fkIdorder)
			&& this.fkIdstock.equals(castOther.fkIdstock);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkIdorder.hashCode();
		hash = hash * prime + this.fkIdstock.hashCode();
		
		return hash;
	}
}