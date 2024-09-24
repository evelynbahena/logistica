package com.erillamhc.whm.services.service;

import com.erillamhc.whm.services.app.aop.ValidationInterceptor;
import com.erillamhc.whm.business.UserBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.UsersDTO;
import com.erillamhc.whm.persistence.dto.user.AddNameDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
//import com.erillamhc.whm.business.management.i18n.MessageManagementProvider;

//import com.erillamhc.whm.persistence.mapper.dto.login.LoginOutDTO;
import com.erillamhc.whm.services.util.UtilService;
import com.erillamhc.whm.services.util.constant.ConstantService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 *
 * @author Fernando FH
 */
@Path(ConstantService.LOGIN_MNG_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ConstantService.APPLICATION_JSON_UTF8)
@ValidationInterceptor
public class LoginService {
	
	
	@EJB
	private UserBusiness userManagementBusiness;


	@POST
	@Path(ConstantService.LOGIN) /*Ruta:   app/sc/lsvc/ls01 */
	public Response auth(UsersDTO user) throws ManagementBusinessException{
		UsersDTO userL = userManagementBusiness.auth(user);
		if(userL == null) {
			return UtilService.getResponseUnAuthorized("User or password incorrect.");
		}
		return UtilService.getResponseOKOutcomeDTO(userL);
	}
	
}

