package com.erillamhc.whm.persistence.facade;


import java.util.List;

import com.erillamhc.whm.persistence.dto.UsersDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public interface UserFacade {
	
	UserDTO addNewUser(AddUserDTO user) throws FacadeException;
	
	void updateUser(AddUserDTO user) throws FacadeException;
	
	void deleteUser(UserDTO user) throws FacadeException;
	
	List<AddUserDTO> getAllUser() throws FacadeException;
	
	UsersDTO auth (UsersDTO u) throws FacadeException;

}
