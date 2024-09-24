package com.erillamhc.whm.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

//import com.erillamhc.whm.persistence.dao.NameDAO;
import com.erillamhc.whm.persistence.dao.UserDAO;
import com.erillamhc.whm.persistence.dto.UsersDTO;
import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.NameDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
//import com.erillamhc.whm.persistence.entity.Name;
import com.erillamhc.whm.persistence.entity.Permission;
import com.erillamhc.whm.persistence.entity.User;
//import com.erillamhc.whm.persistence.entity.Users;
import com.erillamhc.whm.persistence.exception.SimpleDAOException;
import com.erillamhc.whm.persistence.facade.UserFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public class UserFacadeImpl implements UserFacade {
	
	private static final Logger LOGGER = Logger.getLogger(UserFacadeImpl.class.getName());
	
	@Inject
    private UserDAO userDAO;
	
	@Override
	public UsersDTO auth(UsersDTO uIn) throws FacadeException {
		UsersDTO uOut = null;
		try {
			User userL =  userDAO.auth(uIn.getEmail(), uIn.getPassword());
			uOut = new UsersDTO();

		}catch(SimpleDAOException ex) {
			throw new FacadeException(ex);
		}
		
		return uOut;
	}
	
//	@Inject
//    private NameDAO nameDAO;

	@Override
	public UserDTO addNewUser(AddUserDTO user) throws FacadeException {
		UserDTO idUser = new UserDTO();
//		try {
//			
//			Name nameEntity = new Name();
//			
//			nameEntity.setName(user.getName());
//			nameEntity.setLastName(user.getLast_name());
//			
//			nameDAO.save(nameEntity);
//			
//			
//			User userEntity = new  User();
//			
//			userEntity.setEmail(user.getEmail());
//			userEntity.setPassword(user.getPassword());
//			userEntity.setStatus(user.getStatus());
//			userEntity.setFkIdRole(user.getFk_id_role());
//			userEntity.setFkIdBranchOffice(user.getFk_id_branch_office());
//			userEntity.setFkIdName(nameEntity.getIdName());
//			
//			
//			userDAO.save(userEntity);
//			idUser.setId_user(userEntity.getIdUser());
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
		
		return idUser;
	}

	@Override
	public void updateUser(AddUserDTO user) throws FacadeException {
//
//		try {
//			
////			Name nameEntity = nameDAO.findByID(user.getFk_id_name());
////			
////			nameEntity.setName(user.getName());
////			nameEntity.setLastName(user.getLast_name());
////			
////			nameDAO.update(nameEntity);
////			
////			
////			User userEntity = userDAO.findByID(user.getId_user());
////			
////			userEntity.setEmail(user.getEmail());
////			userEntity.setPassword(user.getPassword());
////			//userEntity.setStatus(user.getStatus());
////			userEntity.setFkIdRole(user.getFk_id_role());
////			userEntity.setFkIdBranchOffice(user.getFk_id_branch_office());
////			userEntity.setFkIdName(user.getFk_id_name());
////			
//			
//	//		userDAO.update(userEntity);
//
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
		

	}

	@Override
	public void deleteUser(UserDTO user) throws FacadeException {
//		try {
//
//			User userEntity = userDAO.findByID(user.getId_user());
//			
//			userEntity.setStatus(false);
//
//			userDAO.update(userEntity);
//
//			
//		}catch(SimpleDAOException ex) {
//			throw new FacadeException(ex);
//		}
//		
	}

	@Override
	public List<AddUserDTO> getAllUser() throws FacadeException {
		
		List<AddUserDTO> userList = new ArrayList<>();
//		List<User> userEntity = userDAO.findAll();
//		
//		for (User user : userEntity) {
//			
//			AddUserDTO u = new AddUserDTO();
//			u.setEmail(user.getEmail());
//			
//			
//			userList.add(u);
//			
//		}
		
		return userList;
		
	}

	

}
