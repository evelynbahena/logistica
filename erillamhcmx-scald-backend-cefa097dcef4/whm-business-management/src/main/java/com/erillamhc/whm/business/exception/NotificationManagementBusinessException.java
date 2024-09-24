package com.erillamhc.whm.business.exception;

public class NotificationManagementBusinessException extends Exception{

	private static final long serialVersionUID = -3014263875818093057L;
	private final String detailMessage;

    public NotificationManagementBusinessException(String message) {
        super(message);
        this.detailMessage = message;
    }

    public NotificationManagementBusinessException(Throwable cause) {
        super(cause);
        detailMessage = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return detailMessage;
    }
}
