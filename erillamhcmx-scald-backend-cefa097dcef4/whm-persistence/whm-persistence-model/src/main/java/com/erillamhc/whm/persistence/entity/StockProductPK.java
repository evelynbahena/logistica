package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the stock_products database table.
 * 
 */
@Embeddable
public class StockProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="fk_idproduct", insertable=false, updatable=false)
	private Integer fkIdproduct;

	@Column(name="fk_idbranchoffice", insertable=false, updatable=false)
	private Integer fkIdbranchoffice;

	public StockProductPK() {
	}
	public Integer getFkIdproduct() {
		return this.fkIdproduct;
	}
	public void setFkIdproduct(Integer fkIdproduct) {
		this.fkIdproduct = fkIdproduct;
	}
	public Integer getFkIdbranchoffice() {
		return this.fkIdbranchoffice;
	}
	public void setFkIdbranchoffice(Integer fkIdbranchoffice) {
		this.fkIdbranchoffice = fkIdbranchoffice;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StockProductPK)) {
			return false;
		}
		StockProductPK castOther = (StockProductPK)other;
		return 
			this.fkIdproduct.equals(castOther.fkIdproduct)
			&& this.fkIdbranchoffice.equals(castOther.fkIdbranchoffice);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fkIdproduct.hashCode();
		hash = hash * prime + this.fkIdbranchoffice.hashCode();
		
		return hash;
	}
}