package com.erillamhc.whm.services.service;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.erillamhc.whm.business.OrderBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.StockDTO;
import com.erillamhc.whm.services.app.aop.ValidationInterceptor;
import com.erillamhc.whm.services.util.UtilService;
import com.erillamhc.whm.services.util.constant.ConstantService;

@Path(ConstantService.STOCK_MNG_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ConstantService.APPLICATION_JSON_UTF8)
@ValidationInterceptor
public class OrderService {
	
	
	@EJB
	private OrderBusiness orderBusinessManagementBusiness;
	
	@POST
	@Path(ConstantService.STOCK_MNG_ADD)
	public Response addUser(StockDTO stock) throws ManagementBusinessException{
		orderBusinessManagementBusiness.addOrder(stock);
		return UtilService.getResponseOKOutcomeDTO("Insert");
	}

}
