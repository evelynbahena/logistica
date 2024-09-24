package com.erillamhc.whm.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import com.erillamhc.whm.persistence.dao.PermissionDAO;
import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;

import com.erillamhc.whm.persistence.dto.user.PermissionDTO;

import com.erillamhc.whm.persistence.entity.Permission;

import com.erillamhc.whm.persistence.exception.SimpleDAOException;
import com.erillamhc.whm.persistence.facade.PermissionFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public class PermissionFacadeImpl implements PermissionFacade {

//	@Inject
//    private PermissionDAO permissionDAO;
//	
	@Override
	public PermissionDTO addNewPermission(AddPermissionDTO permission) throws FacadeException {
//		
//		PermissionDTO idPermission = new PermissionDTO();
//		try {
//			
//			Permission pEntity = new Permission();
//			
//			pEntity.setName(permission.getName());
//			pEntity.setDescription(permission.getDescription());
//			
//			
//			permissionDAO.save(pEntity);
//			idPermission.setId_permission(pEntity.getIdPermission());
//		
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
//		
		return null;
	}

	@Override
	public void updatePermission(AddPermissionDTO permission) throws FacadeException {
//		try {
//			
//
//			Permission pEntity = permissionDAO.findByID(permission.getId_permission());
//			
//			pEntity.setName(permission.getName());
//			pEntity.setDescription(permission.getDescription());
//			
//			
//			permissionDAO.update(pEntity);
//
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
//		return null;
		
	}

	@Override
	public void deletePermission(PermissionDTO user) throws FacadeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AddPermissionDTO> getAllPermission() throws FacadeException {
		return null;
//		List<AddPermissionDTO> permissionList = new ArrayList<>();
//		List<Permission> permissionEntity = permissionDAO.findAll();
//		
//		for (Permission perm : permissionEntity) {
//			
//			AddPermissionDTO p = new AddPermissionDTO();
//			p.setName(perm.getName());
//			p.setDescription(perm.getDescription());
//			
//			permissionList.add(p);
//			
//		}
//		
//		return permissionList;
	}

}
