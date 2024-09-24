package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the jurisdictions database table.
 * 
 */
@Entity
@Table(name="jurisdictions")
@NamedQuery(name="Jurisdiction.findAll", query="SELECT j FROM Jurisdiction j")
public class Jurisdiction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_jurisdiction")
	private Integer idJurisdiction;

	private String jurisdictionname;

	//bi-directional many-to-one association to BranchOffice
	@OneToMany(mappedBy="jurisdiction")
	private List<BranchOffice> branchOffices;

	public Jurisdiction() {
	}

	public Integer getIdJurisdiction() {
		return this.idJurisdiction;
	}

	public void setIdJurisdiction(Integer idJurisdiction) {
		this.idJurisdiction = idJurisdiction;
	}

	public String getJurisdictionname() {
		return this.jurisdictionname;
	}

	public void setJurisdictionname(String jurisdictionname) {
		this.jurisdictionname = jurisdictionname;
	}

	public List<BranchOffice> getBranchOffices() {
		return this.branchOffices;
	}

	public void setBranchOffices(List<BranchOffice> branchOffices) {
		this.branchOffices = branchOffices;
	}

	public BranchOffice addBranchOffice(BranchOffice branchOffice) {
		getBranchOffices().add(branchOffice);
		branchOffice.setJurisdiction(this);

		return branchOffice;
	}

	public BranchOffice removeBranchOffice(BranchOffice branchOffice) {
		getBranchOffices().remove(branchOffice);
		branchOffice.setJurisdiction(null);

		return branchOffice;
	}

}