package com.erillamhc.whm.persistence.mapper.facade.exception;

/**
 *
 * @author Ivo Danic
 */
public class FacadeException extends Exception {
    
	private static final long serialVersionUID = 1125442070391271558L;
	private final String message;

    public FacadeException(String message) {
        super(message);
        this.message = message;
    }

    public FacadeException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
