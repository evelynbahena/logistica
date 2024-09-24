package com.erillamhc.whm.persistence.mapper;

/**
 *
 * @author Ivo Danic G.
 */
public class MapperParseJSONException extends Exception {

	private static final long serialVersionUID = 7985867733716194477L;
	private final String messageParse;

    public MapperParseJSONException(String messageParse) {
        super(messageParse);
        this.messageParse = messageParse;
    }

    public MapperParseJSONException(Throwable cause) {
        super(cause);
        this.messageParse = cause.getMessage();
    }
    
    @Override
    public String getMessage() {
        return messageParse;
    }
}
