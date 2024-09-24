package com.erillamhc.whm.services.app.aop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.erillamhc.whm.persistence.util.validator.ValidatorCommonBean;
import com.erillamhc.whm.persistence.util.validator.exception.ValidatorException;

/**
 * Interceptor para mostrar en el log la entrada y salida entre metodos.
 *
 * @author Ivo Danic G.
 * @company Erillam Health Care
 * @version 1.0
 * @since 1.0
 */
@Interceptor
@ValidationInterceptor
public class ValidationInterceptorImpl {

    private static final Logger LOGGER = Logger.getLogger(ValidationInterceptorImpl.class.getSimpleName());

    @EJB
    private ValidatorCommonBean validatorCommonBean;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws ValidatorException {
        LOGGER.log(Level.INFO, "Validation BEFORE calling method : {0}", context.getMethod().getName());
        Object[] parameters = context.getParameters();
        validateParameters(parameters);
        try {
            Object result = context.proceed();
            LOGGER.log(Level.INFO, "Validation AFTER calling method : {0}", context.getMethod().getName());
            return result;
        } catch (Exception ex) {
            throw new ValidatorException(ex);
        }
    }

    private void validateParameters(Object[] parameters) throws ValidatorException {
        if (parameters != null) {
            for (Object parameter : parameters) {
                validatorCommonBean.validateBean(parameter);
            }
        }
    }
}
