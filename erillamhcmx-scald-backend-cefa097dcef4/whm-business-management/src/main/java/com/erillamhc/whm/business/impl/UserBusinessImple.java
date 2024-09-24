package com.erillamhc.whm.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;


import com.erillamhc.whm.business.UserBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.UsersDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
import com.erillamhc.whm.persistence.facade.UserFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserBusinessImple implements UserBusiness {
	
	@Inject
	private UserFacade userFacade;

	@Override
	public UserDTO addNewUser(AddUserDTO user) throws ManagementBusinessException {
		UserDTO idUser = new UserDTO();
		try {
			idUser = userFacade.addNewUser(user);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		return idUser;
	}

	@Override
	public void updateUser(AddUserDTO user) throws ManagementBusinessException {
		
		try {
			userFacade.updateUser(user);
		}catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		
	}

	@Override
	public void deleteUser(UserDTO user) throws ManagementBusinessException {
		
		try {
		 userFacade.deleteUser(user);
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
		
	}

	@Override
	public List<AddUserDTO> getAllUser() throws ManagementBusinessException {
		try {
			 return userFacade.getAllUser();
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}

	@Override
	public UsersDTO auth(UsersDTO user) throws ManagementBusinessException{
		try {
			 return userFacade.auth(user);
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}



}
