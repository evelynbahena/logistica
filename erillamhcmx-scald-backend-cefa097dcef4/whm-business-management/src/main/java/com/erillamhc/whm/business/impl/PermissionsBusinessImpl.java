package com.erillamhc.whm.business.impl;

import java.util.List;

import javax.inject.Inject;

import com.erillamhc.whm.business.PermissionsBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;
import com.erillamhc.whm.persistence.dto.user.PermissionDTO;
import com.erillamhc.whm.persistence.facade.PermissionFacade;

import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public class PermissionsBusinessImpl implements PermissionsBusiness {
	
	@Inject
	private PermissionFacade permissionFacade;

	@Override
	public PermissionDTO addNewPermission(AddPermissionDTO perm) throws ManagementBusinessException {
		PermissionDTO idPer = new PermissionDTO();
		try {
			idPer = permissionFacade.addNewPermission(perm);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		return idPer;
	}

	@Override
	public void updatePermission(AddPermissionDTO perm) throws ManagementBusinessException {
		try {
			permissionFacade.updatePermission(perm);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		
	}

	@Override
	public void deletePermission(PermissionDTO user) throws ManagementBusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AddPermissionDTO> getAllPermission() throws ManagementBusinessException {
		try {
			 return permissionFacade.getAllPermission();
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}

}
