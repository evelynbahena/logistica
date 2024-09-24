package com.erillamhc.whm.services.app.filter;

//import com.erillamhc.whm.business.LoginBusiness;
import com.erillamhc.whm.services.util.constant.ConstantService;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
@PreMatching
public class OperationRequestFilter implements ContainerRequestFilter {
    
    //@EJB
    //private LoginBusiness business;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if (path.startsWith("/sc/")) {
            //String serviceKey = requestContext.getHeaderString(ConstantService.HEADER_SERVICE_KEY);
            //String authToken = requestContext.getHeaderString(ConstantService.HEADER_AUTH_TOKEN);
        	/*if (!business.isAuthTokenValid(serviceKey, authToken)) {
                ResponseVO<String> response = new ResponseVO<>();
                response.setCode(Response.Status.UNAUTHORIZED.getStatusCode());
                response.setStatus(Response.Status.UNAUTHORIZED.name());
                response.setData("No tiene autorizaci\u00F3n");
                requestContext.abortWith(Response
                        .ok(new GenericEntity<ResponseVO<String>>(response) {
                        })
                        .type(ConstantService.APPLICATION_JSON_UTF8).build());
            }*/
        }
    }

}
