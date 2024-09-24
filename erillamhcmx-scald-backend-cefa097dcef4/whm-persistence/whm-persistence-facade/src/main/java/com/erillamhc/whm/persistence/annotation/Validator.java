package com.erillamhc.whm.persistence.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.erillamhc.whm.persistence.util.ValidationTypeEnum;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Validator {
	
    boolean required() default true;

    long length() default 0;

    ValidationTypeEnum type() default ValidationTypeEnum.ALPHANUMERIC;

    boolean onlyValue() default false;

    String value() default "";

    String format() default "";
    
    boolean optional() default false;
    
    String fieldName() default "";

}
