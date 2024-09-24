package com.erillamhc.whm.business.exception;

/**
 *
 * @author Ivo Danic G.
 */
public class ManagementBusinessException extends Exception {

	private static final long serialVersionUID = -3014263875818093057L;
	private final String detailMessage;

    public ManagementBusinessException(String message) {
        super(message);
        this.detailMessage = message;
    }

    public ManagementBusinessException(Throwable cause) {
        super(cause);
        detailMessage = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return detailMessage;
    }
}
