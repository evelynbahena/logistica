package com.erillamhc.whm.services.app.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Anotación interceptor.
 * 
 * @author Ivo Danic G
 * @author Erillam Health Care
 * @since 1.0
 * @version 1.0 
 */
@InterceptorBinding
@Target({ TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationInterceptor {}