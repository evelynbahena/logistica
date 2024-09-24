package com.erillamhc.whm.services.app;

import com.erillamhc.whm.services.service.LoginService;
import com.erillamhc.whm.services.service.UserService;
import com.erillamhc.whm.services.util.constant.ConstantService;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.logging.Logger;

/**
 * Componente que contiene la configuración de los servicios REST.
 *
 * @author Ivo Danic G.
 * @company Erillam Health Care
 * @version 1.0
 * @since 1.0
 */
@ApplicationPath(ConstantService.APPLICATION_PATH)
public class RestApplication extends Application {
    
    private static final Logger LOGGER = Logger.getLogger(RestApplication.class.getName());

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> services = new HashSet<>();  
        services.add(LoginService.class);
        services.add(UserService.class);
        LOGGER.info("Services Up!");
        
        return services;
    }
}
