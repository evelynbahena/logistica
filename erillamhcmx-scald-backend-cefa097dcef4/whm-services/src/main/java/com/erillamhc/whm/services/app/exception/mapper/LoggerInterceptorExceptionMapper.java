package com.erillamhc.whm.services.app.exception.mapper;

import com.erillamhc.whm.services.app.exception.LoggerInterceptorException;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
public class LoggerInterceptorExceptionMapper implements ExceptionMapper<LoggerInterceptorException> {

    @Override
    public Response toResponse(LoggerInterceptorException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-2000);
        response.setStatus("LoggerInterceptor Fail");
        response.setData(exception.getMessage());

        return Response.ok(new GenericEntity<ResponseVO<String>>(response) {
        }).build();
    }

}
