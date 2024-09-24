package com.erillamhc.whm.persistence.facade;

import java.util.List;


import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;
import com.erillamhc.whm.persistence.dto.user.PermissionDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public interface PermissionFacade {

	PermissionDTO addNewPermission(AddPermissionDTO permission) throws FacadeException;
	
	void updatePermission(AddPermissionDTO permission) throws FacadeException;
	
	void deletePermission(PermissionDTO permission) throws FacadeException;
	
	List<AddPermissionDTO> getAllPermission() throws FacadeException;
	
}
