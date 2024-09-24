package com.erillamhc.whm.persistence.facade;

import com.erillamhc.whm.persistence.dto.user.StockDTO;
import com.erillamhc.whm.persistence.mapper.facade.exception.FacadeException;

public interface OrderFacade {
	
	void addOrder (StockDTO stock) throws FacadeException;

}
