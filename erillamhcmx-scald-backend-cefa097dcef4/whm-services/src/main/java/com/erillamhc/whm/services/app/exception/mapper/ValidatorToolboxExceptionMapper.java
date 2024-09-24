package com.erillamhc.whm.services.app.exception.mapper;

import com.erillamhc.whm.persistence.util.validator.exception.ValidatorException;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G
 */
@Provider
public class ValidatorToolboxExceptionMapper implements ExceptionMapper<ValidatorException> {

    @Override
    public Response toResponse(ValidatorException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-10000);
        response.setStatus("Validator Fail");
        response.setData(exception.getMessage());

        return Response.ok(new GenericEntity<ResponseVO<String>>(response) {
        }).build();
    }

}
