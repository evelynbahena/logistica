package com.erillamhc.whm.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.erillamhc.whm.business.RolesBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddRoleDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;
import com.erillamhc.whm.persistence.facade.RolesFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RoleBusinessImpl implements RolesBusiness {
	
	@Inject
	private RolesFacade rolesFacade;

	@Override
	public RoleDTO addNewRole(AddRoleDTO role) throws ManagementBusinessException {
		RoleDTO idRole = new RoleDTO();
		try {
			idRole = rolesFacade.addNewRole(role);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		return idRole;
	}

	@Override
	public void updateRole(AddRoleDTO role) throws ManagementBusinessException {
		try {
			rolesFacade.updateRole(role);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}

	@Override
	public void deleteRole(RoleDTO user) throws ManagementBusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AddRoleDTO> getAllRoles() throws ManagementBusinessException {
		try {
			 return rolesFacade.getAllRoles();
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}

}
