package com.erillamhc.whm.services.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.erillamhc.whm.business.PermissionsBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.AddPermissionDTO;
import com.erillamhc.whm.persistence.dto.user.PermissionDTO;
import com.erillamhc.whm.services.app.aop.ValidationInterceptor;
import com.erillamhc.whm.services.util.UtilService;
import com.erillamhc.whm.services.util.constant.ConstantService;


@Path(ConstantService.PERM_MNG_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ConstantService.APPLICATION_JSON_UTF8)
@ValidationInterceptor
public class PermissionsService {
	
//	@EJB
//	private PermissionsBusiness permissionManagementBusiness;
//
//
//	@POST
//	@Path(ConstantService.PERM_MNG_ADD)
//	public Response addPermission(AddPermissionDTO perm) throws ManagementBusinessException{
//		PermissionDTO idPerm = permissionManagementBusiness.addNewPermission(perm);
//		return UtilService.getResponseOKOutcomeDTO(idPerm.getId_permission());
//	}
//	
//	@POST
//	@Path(ConstantService.PERM_MNG_UPD)
//	public Response updateRole(AddPermissionDTO role) throws ManagementBusinessException{
//		permissionManagementBusiness.updatePermission(role);
//		return UtilService.getResponseOKOutcomeDTO("un update");
//	}
//	
//	@POST
//	@Path(ConstantService.PERM_MNG_DEL)
//	public Response deleteRole(PermissionDTO perm) throws ManagementBusinessException{
//		permissionManagementBusiness.deletePermission(perm);
//		return UtilService.getResponseOKOutcomeDTO("se elimino");
//	}
//	
//	@POST
//	@Path(ConstantService.PERM_MNG_GET)
//	public Response getAll() throws ManagementBusinessException{
//		List<AddPermissionDTO> roles = permissionManagementBusiness.getAllPermission();
//		return UtilService.getResponseOKOutcomeDTO(roles);
//	}

}
