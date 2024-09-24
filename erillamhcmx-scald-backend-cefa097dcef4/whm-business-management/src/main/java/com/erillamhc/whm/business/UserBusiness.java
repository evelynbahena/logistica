package com.erillamhc.whm.business;

import java.util.List;

import javax.ejb.Local;

import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.UsersDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;

@Local
public interface UserBusiness {

	UserDTO addNewUser(AddUserDTO user) throws ManagementBusinessException;
	
	void updateUser(AddUserDTO user) throws ManagementBusinessException;
	
	void deleteUser(UserDTO user) throws ManagementBusinessException;
	
	List<AddUserDTO> getAllUser() throws ManagementBusinessException;

	UsersDTO auth(UsersDTO user) throws ManagementBusinessException;
	
}
