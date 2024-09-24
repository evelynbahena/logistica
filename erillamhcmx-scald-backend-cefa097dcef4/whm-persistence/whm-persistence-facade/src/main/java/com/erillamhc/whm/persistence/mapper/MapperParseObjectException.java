package com.erillamhc.whm.persistence.mapper;

/**
 *
 * @author Ivo Danic G.
 */
public class MapperParseObjectException extends Exception {

	private static final long serialVersionUID = 2194049515712420422L;
	private final String messageMapper;

    public MapperParseObjectException(String message) {
        super(message);
        this.messageMapper = message;
    }

    public MapperParseObjectException(Throwable cause) {
        super(cause);
        this.messageMapper = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return messageMapper;
    }
}
