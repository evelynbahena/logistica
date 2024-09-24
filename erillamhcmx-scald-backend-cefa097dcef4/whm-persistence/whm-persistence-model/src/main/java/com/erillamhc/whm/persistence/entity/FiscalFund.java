package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fiscal_fund database table.
 * 
 */
@Entity
@Table(name="fiscal_fund")
@NamedQuery(name="FiscalFund.findAll", query="SELECT f FROM FiscalFund f")
public class FiscalFund implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fiscalfund")
	private Integer idFiscalfund;

	private String key;

	private String name;

	//bi-directional many-to-one association to Remmision
	@OneToMany(mappedBy="fiscalFund")
	private List<Remmision> remmisions;

	public FiscalFund() {
	}

	public Integer getIdFiscalfund() {
		return this.idFiscalfund;
	}

	public void setIdFiscalfund(Integer idFiscalfund) {
		this.idFiscalfund = idFiscalfund;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Remmision> getRemmisions() {
		return this.remmisions;
	}

	public void setRemmisions(List<Remmision> remmisions) {
		this.remmisions = remmisions;
	}

	public Remmision addRemmision(Remmision remmision) {
		getRemmisions().add(remmision);
		remmision.setFiscalFund(this);

		return remmision;
	}

	public Remmision removeRemmision(Remmision remmision) {
		getRemmisions().remove(remmision);
		remmision.setFiscalFund(null);

		return remmision;
	}

}