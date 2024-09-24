package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the suppliers database table.
 * 
 */
@Entity
@Table(name="suppliers")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_supplier")
	private Integer idSupplier;

	private String name;

	//bi-directional many-to-many association to Bidding
	@ManyToMany
	@JoinTable(
		name="suppliers_biddings"
		, joinColumns={
			@JoinColumn(name="id_supplier")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_bidding")
			}
		)
	private List<Bidding> biddings;

	public Supplier() {
	}

	public Integer getIdSupplier() {
		return this.idSupplier;
	}

	public void setIdSupplier(Integer idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bidding> getBiddings() {
		return this.biddings;
	}

	public void setBiddings(List<Bidding> biddings) {
		this.biddings = biddings;
	}

}