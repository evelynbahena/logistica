package com.erillamhc.whm.business.exception;

/**
 *
 * @author Ivo Danic G.
 */
public class LoginBusinessException extends Exception {

    private static final long serialVersionUID = -4730754348047133707L;
    private final String message;

    public LoginBusinessException(String message) {
        super(message);
        this.message = message;
    }

    public LoginBusinessException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
