package com.erillamhc.whm.persistence.facade;

import java.util.List;


import com.erillamhc.whm.persistence.dto.user.AddRoleDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public interface RolesFacade {

	RoleDTO addNewRole(AddRoleDTO role) throws FacadeException;
	
	void updateRole(AddRoleDTO role) throws FacadeException;
	
	void deleteRole(RoleDTO user) throws FacadeException;
	
	List<AddRoleDTO> getAllRoles() throws FacadeException;
	
}
