package com.erillamhc.whm.business.impl;


import javax.inject.Inject;

import com.erillamhc.whm.business.OrderBusiness;
import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.StockDTO;
import com.erillamhc.whm.persistence.facade.OrderFacade;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public class OrderBusinessImpl implements OrderBusiness{

	@Inject
	private OrderFacade orderFacade;
	
	
	@Override
	public void addOrder(StockDTO stock) throws ManagementBusinessException {
		try {
			orderFacade.addOrder(stock);
		} catch (FacadeException e) {
			throw new ManagementBusinessException(e);
		}
	}
	
	
	

}
