package com.erillamhc.whm.services.app.exception.mapper;


import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.erillamhc.whm.business.exception.LoginBusinessException;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
public class LoginBusinessExceptionMapper implements ExceptionMapper<LoginBusinessException> {

    @Override
    public Response toResponse(LoginBusinessException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-5000);
        response.setStatus("Login Operation Fail");
        response.setData(exception.getMessage());

        return Response.ok(new GenericEntity<ResponseVO<String>>(response) {
        }).build();
    }

}