package com.erillamhc.whm.business;

import java.util.List;

import javax.ejb.Local;

import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddRoleDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;

@Local
public interface RolesBusiness {
	
	RoleDTO addNewRole(AddRoleDTO role) throws ManagementBusinessException;
	
	void updateRole(AddRoleDTO role) throws ManagementBusinessException;
	
	void deleteRole(RoleDTO user) throws ManagementBusinessException;
	
	List<AddRoleDTO> getAllRoles() throws ManagementBusinessException;

}
