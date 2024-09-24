package com.erillamhc.whm.persistence.util.validator.exception;


public class ValidatorException extends Exception {

    private static final long serialVersionUID = 7673048443316588596L;
    private final String messageValidator;

    public ValidatorException(String message) {
        super(message);
        messageValidator = message;
    }

    public ValidatorException(Throwable cause) {
        super(cause);
        this.messageValidator = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return this.messageValidator;
    }
}
