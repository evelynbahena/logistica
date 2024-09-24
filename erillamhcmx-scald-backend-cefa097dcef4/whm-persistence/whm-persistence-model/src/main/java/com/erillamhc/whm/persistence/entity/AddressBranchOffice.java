package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address_branch_office database table.
 * 
 */
@Entity
@Table(name="address_branch_office")
@NamedQuery(name="AddressBranchOffice.findAll", query="SELECT a FROM AddressBranchOffice a")
public class AddressBranchOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_address")
	private Integer idAddress;

	private String fulladdress;

	//bi-directional many-to-one association to BranchOffice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_branchofficeid")
	private BranchOffice branchOffice;

	public AddressBranchOffice() {
	}

	public Integer getIdAddress() {
		return this.idAddress;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	public String getFulladdress() {
		return this.fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public BranchOffice getBranchOffice() {
		return this.branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

}