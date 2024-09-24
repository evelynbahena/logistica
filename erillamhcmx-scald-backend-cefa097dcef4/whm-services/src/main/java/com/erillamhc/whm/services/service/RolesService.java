package com.erillamhc.whm.services.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.erillamhc.whm.business.RolesBusiness;
import com.erillamhc.whm.business.UserBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddRoleDTO;
import com.erillamhc.whm.persistence.dto.user.AddUserDTO;
import com.erillamhc.whm.persistence.dto.user.RoleDTO;
import com.erillamhc.whm.persistence.dto.user.UserDTO;
import com.erillamhc.whm.services.app.aop.ValidationInterceptor;
import com.erillamhc.whm.services.util.UtilService;
import com.erillamhc.whm.services.util.constant.ConstantService;

@Path(ConstantService.ROLE_MNG_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ConstantService.APPLICATION_JSON_UTF8)
@ValidationInterceptor
public class RolesService {

	@EJB
	private RolesBusiness roleManagementBusiness;


	@POST
	@Path(ConstantService.ROLE_MNG_ADD)
	public Response addRole(AddRoleDTO role) throws ManagementBusinessException{
		RoleDTO idRole = roleManagementBusiness.addNewRole(role);
		return UtilService.getResponseOKOutcomeDTO(idRole.getId_role());
	}
	
	@POST
	@Path(ConstantService.ROLE_MNG_UPD)
	public Response updateRole(AddRoleDTO role) throws ManagementBusinessException{
		roleManagementBusiness.updateRole(role);
		return UtilService.getResponseOKOutcomeDTO("un update");
	}
	
	@POST
	@Path(ConstantService.ROLE_MNG_DEL)
	public Response deleteRole(RoleDTO role) throws ManagementBusinessException{
		roleManagementBusiness.deleteRole(role);
		return UtilService.getResponseOKOutcomeDTO("se elimino");
	}
	
	@POST
	@Path(ConstantService.ROLE_MNG_GET)
	public Response getAll() throws ManagementBusinessException{
		List<AddRoleDTO> roles = roleManagementBusiness.getAllRoles();
		return UtilService.getResponseOKOutcomeDTO(roles);
	}
	
}
