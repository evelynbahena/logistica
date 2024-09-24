package com.erillamhc.whm.services.app.exception.mapper;

import com.erillamhc.whm.business.exception.ManagementBusinessException;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G.
 * 
 */
@Provider
public class ManagementBusinessExceptionMapper implements ExceptionMapper<ManagementBusinessException> {

    @Override
    public Response toResponse(ManagementBusinessException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-6000);
        response.setStatus("Management Operation Fail");
        response.setData(exception.getMessage());
        
        return Response.ok(new GenericEntity<ResponseVO<String>>(response){}).build();
    }

}
