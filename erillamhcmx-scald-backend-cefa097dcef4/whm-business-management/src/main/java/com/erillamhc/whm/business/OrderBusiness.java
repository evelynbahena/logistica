package com.erillamhc.whm.business;

import javax.ejb.Local;

import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.persistence.dto.user.StockDTO;

@Local
public interface OrderBusiness {
	
	void addOrder (StockDTO stock) throws ManagementBusinessException;
	

}
