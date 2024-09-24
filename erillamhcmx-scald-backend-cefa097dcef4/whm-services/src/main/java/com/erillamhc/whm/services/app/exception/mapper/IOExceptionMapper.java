package com.erillamhc.whm.services.app.exception.mapper;

import com.erillamhc.whm.persistence.util.UtilFacade;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import java.io.IOException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
public class IOExceptionMapper implements ExceptionMapper<IOException> {

    private static final String UNRECOGNIZED = "Unrecognized field";

    @Override
    public Response toResponse(IOException exception) {
        ResponseVO<String> response = new ResponseVO<>();
        response.setCode(Response.Status.BAD_REQUEST.getStatusCode());
        response.setStatus(Response.Status.BAD_REQUEST.name());

        String messageException = exception.getMessage();
        response.setData(messageException);
        if (UtilFacade.nonEmpty(messageException)) {
            if (messageException.contains(UNRECOGNIZED)) {
                response.setData(processUnrecognizedMessage(messageException));
            }
        }

        return Response.ok(new GenericEntity<ResponseVO<String>>(response) {
        }).build();
    }

    private String processUnrecognizedMessage(String message) {
        int field1 = message.indexOf('"');
        if (field1 > -1) {
            int field2 = message.indexOf('"', field1 + 1);
            return "Campo no reconocido " + message.substring(field1 + 1, field2);
        }
        return message;
    }
}
