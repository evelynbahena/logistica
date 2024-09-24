package com.erillamhc.whm.services.app.filter;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.erillamhc.whm.services.util.constant.ConstantService;

/**
 *
 * @author Ivo Danic G.
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {
    
    private final static Logger LOGGER = Logger.getLogger(CORSFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        LOGGER.info("Filtering REST Response");
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST");
        responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
        responseContext.getHeaders().add("Content-Type", ConstantService.APPLICATION_JSON_UTF8);
    }

}
