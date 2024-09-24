package com.erillamhc.whm.services.app.exception;

/**
 *
 * @author Ivo Danic G
 * @company Erillam Healthcare
 */

public class LoggerInterceptorException extends Exception {
    
	private static final long serialVersionUID = -4429978488753868747L;
	private final String message;

    public LoggerInterceptorException(String message) {
        super(message);
        this.message = message;
    }

    public LoggerInterceptorException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
