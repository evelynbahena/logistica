package com.erillamhc.whm.services.app.exception.mapper;

import java.util.MissingResourceException;
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
public class MissingResourceExceptionMapper implements ExceptionMapper<MissingResourceException> {

    @Override
    public Response toResponse(MissingResourceException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(-9000);
        response.setStatus("Resource not found");
        response.setData(exception.getMessage());

        return Response.ok(new GenericEntity<ResponseVO<String>>(response) {
        }).build();
    }

}
