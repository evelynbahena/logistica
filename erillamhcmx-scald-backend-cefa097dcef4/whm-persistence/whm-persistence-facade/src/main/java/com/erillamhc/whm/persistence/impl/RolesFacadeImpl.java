package com.erillamhc.whm.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import com.erillamhc.whm.persistence.dao.RolesDAO;
import com.erillamhc.whm.persistence.dto.user.AddRoleDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;
import com.erillamhc.whm.persistence.entity.Role;
import com.erillamhc.whm.persistence.exception.SimpleDAOException;
import com.erillamhc.whm.persistence.facade.RolesFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public class RolesFacadeImpl implements RolesFacade {
	
//	
//	@Inject
//    private RolesDAO roleDAO;

	@Override
	public RoleDTO addNewRole(AddRoleDTO role) throws FacadeException {
		RoleDTO idRole = new RoleDTO();
//		try {
//			
//			Role roleEntity = new Role();
//			
//			roleEntity.setName(role.getName());
//			roleEntity.setDescription(role.getDescription());
//			
//			
//			roleDAO.save(roleEntity);
//			idRole.setId_role(roleEntity.getIdRole());
//		
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
//		
		return idRole;
	}

	@Override
	public void updateRole(AddRoleDTO role) throws FacadeException {
		try {
//			
//
//			Role roleEntity = roleDAO.findByID(role.getId_role());
//			
//			roleEntity.setName(role.getName());
//			roleEntity.setDescription(role.getDescription());
//			
//			
//			roleDAO.update(roleEntity);

			
		}catch(Exception ex) {
			throw new FacadeException(ex);
		}
	}

	@Override
	public void deleteRole(RoleDTO user) throws FacadeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AddRoleDTO> getAllRoles() throws FacadeException {
		
		List<AddRoleDTO> roleList = new ArrayList<>();
		try {
			return roleList;
		}catch(Exception e) {
			throw new FacadeException(e);

		}
//		List<Role> roleEntity = roleDAO.findAll();
//		
//		for (Role role : roleEntity) {
//			
//			AddRoleDTO r = new AddRoleDTO();
//			r.setName(role.getName());
//			r.setDescription(role.getDescription());
//			
//			roleList.add(r);
//			
//		}
//		
//		return roleList;
	}

}
