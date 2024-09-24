package com.erillamhc.whm.services.util;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.erillamhc.whm.services.vo.commons.ResponseVO;

/**
 *
 * @author Ivo Danic G
 * @company Erillam Health Care
 */
public class UtilService {

    private UtilService() {
        // Instance Singleton
    }

    private static <T> Response createReponse(Integer code, String status, T data) {
        ResponseVO<T> response = new ResponseVO<>();
        response.setCode(code);
        response.setStatus(status);
        response.setData(data);
        return Response.ok(new GenericEntity<ResponseVO<T>>(response) {}
        					).type("application/json")
        						.build();
    }

    public static Response getResponseUnAuthorized(String message) {
        return createReponse(Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED.name(), message);
    }

    public static <S> Response getResponseOKOutcomeList(List<S> outcomeList) {
        return createReponse(Response.Status.OK.getStatusCode(), Response.Status.OK.name(), outcomeList);
    }

    public static <S> Response getResponseOKOutcomeDTO(S outcomeDTO) {
        return createReponse(Response.Status.OK.getStatusCode(), Response.Status.OK.name(), outcomeDTO);
    }

    public static Response getResponseOk(String message) {
        return createReponse(Response.Status.OK.getStatusCode(), Response.Status.OK.name(), message);
    }

    public static Response getPDFResponseOK(byte[] contentfile, String filename) {
        return Response.ok(new ByteArrayInputStream(contentfile))
                .type("application/pdf")
                .header("Content-Disposition", "filename=" + filename)
                .build();
    }
}
