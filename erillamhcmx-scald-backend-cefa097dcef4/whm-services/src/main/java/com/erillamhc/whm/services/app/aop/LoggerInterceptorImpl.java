package com.erillamhc.whm.services.app.aop;

import com.erillamhc.whm.services.app.exception.LoggerInterceptorException;
import com.erillamhc.whm.persistence.mapper.Mapper;
import com.erillamhc.whm.persistence.mapper.MapperParseJSONException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor para mostrar en el log la entrada y salida entre metodos.
 *
 * @author Ivo Danic G.
 * @company Erillam Health Care
 * @version 1.0
 * @since 1.0
 */
@Interceptor
@LoggerInterceptor
public class LoggerInterceptorImpl {

    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptorImpl.class.getSimpleName());
    
    @Inject
    private Mapper mapper;

    @AroundInvoke
    public Object intecept(InvocationContext context) throws LoggerInterceptorException {
        LOGGER.log(Level.INFO, "Logging BEFORE calling method : {0}", context.getMethod().getName());
        Object[] parameters = context.getParameters();
        showParameters(parameters);
        Object result;
        try {
            result = context.proceed();
        } catch (Exception ex) {
            throw new LoggerInterceptorException(ex);
        } finally {
            LOGGER.log(Level.INFO, "Logging AFTER calling method : {0}", context.getMethod().getName());
        }
        return result;
    }

    private void showParameters(Object[] parameters) {
        if (parameters != null) {
            for (Object parameter : parameters) {
                try {
                    LOGGER.log(Level.INFO, "Parameters: {0}", mapper.parseObjectToJSON(parameter));
                } catch (MapperParseJSONException ex) {
                    LOGGER.log(Level.INFO, "showParameters exception parse: {0}", ex);
                }
            }
        }
    }
}
