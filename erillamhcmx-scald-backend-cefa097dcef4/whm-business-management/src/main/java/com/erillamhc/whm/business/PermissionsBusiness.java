package com.erillamhc.whm.business;

import java.util.List;

import javax.ejb.Local;

import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;
import com.erillamhc.whm.persistence.dto.user.PermissionDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;

@Local
public interface PermissionsBusiness {
	
	PermissionDTO addNewPermission(AddPermissionDTO perm) throws ManagementBusinessException;
	
	void updatePermission(AddPermissionDTO perm) throws ManagementBusinessException;
	
	void deletePermission(PermissionDTO perm) throws ManagementBusinessException;
	
	List<AddPermissionDTO> getAllPermission() throws ManagementBusinessException;
}
