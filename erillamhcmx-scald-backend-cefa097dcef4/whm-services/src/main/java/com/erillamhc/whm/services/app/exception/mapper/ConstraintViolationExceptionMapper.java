package com.erillamhc.whm.services.app.exception.mapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.erillamhc.whm.services.vo.commons.ResponseVO;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
         ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-9000);
        response.setStatus("Constraint Violation Operation Fail");
        response.setData(exception.getMessage());
        
        return Response.ok(new GenericEntity<ResponseVO<String>>(response){}).build();
    }
    
}
