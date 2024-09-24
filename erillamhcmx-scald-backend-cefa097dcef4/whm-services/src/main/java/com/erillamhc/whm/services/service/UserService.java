package com.erillamhc.whm.services.service;

import com.erillamhc.whm.services.app.aop.ValidationInterceptor;
import com.erillamhc.whm.business.UserBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
import com.erillamhc.whm.services.util.UtilService;
import com.erillamhc.whm.services.util.constant.ConstantService;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path(ConstantService.USERS_MNG_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ConstantService.APPLICATION_JSON_UTF8)
@ValidationInterceptor
public class UserService {
	
	
	@EJB
	private UserBusiness userManagementBusiness;


	@POST
	@Path(ConstantService.USERS_MNG_ADD)
	public Response addUser(AddUserDTO user) throws ManagementBusinessException{
		userManagementBusiness.addNewUser(user);
		return UtilService.getResponseOKOutcomeDTO("Insert user");
	}
	
	@POST
	@Path(ConstantService.USERS_MNG_UPD)
	public Response updateUser(AddUserDTO user) throws ManagementBusinessException{
		userManagementBusiness.updateUser(user);
		return UtilService.getResponseOKOutcomeDTO("Update");
	}
	
	@POST
	@Path(ConstantService.USERS_MNG_DEL)
	public Response deleteUser(UserDTO user) throws ManagementBusinessException{
		userManagementBusiness.deleteUser(user);
		return UtilService.getResponseOKOutcomeDTO("Delete");
	}
	
	@POST
	@Path(ConstantService.USERS_MNG_GET)
	public Response getAll(AddUserDTO user) throws ManagementBusinessException{
		List<AddUserDTO> listUser = userManagementBusiness.getAllUser();
		return UtilService.getResponseOKOutcomeDTO(listUser);
	}
	


}

