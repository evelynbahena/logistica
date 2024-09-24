package com.erillamhc.whm.services.app;

import com.erillamhc.whm.persistence.mapper.Mapper;
import com.erillamhc.whm.persistence.mapper.MapperParseJSONException;
import com.erillamhc.whm.services.util.constant.ConstantService;
import com.erillamhc.whm.services.vo.commons.ResponseVO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ivo Danic G.
 * @company Erillam Health Care
 * @version 1.0
 * @since 1.0
 */
@Provider
@Produces(ConstantService.APPLICATION_JSON_UTF8)
public class CustomMarshaller implements MessageBodyWriter<ResponseVO<?>> {
    
    private static final Logger LOGGER = Logger.getLogger(CustomMarshaller.class.getName());
    
    private static final String[][] CHARS_LOWER = {
        {"\u00E1", "\u00E9", "\u00ED", "\u00F3", "\u00FA", "\u00F1", "\u00FC"},
        {"&aacute;", "&eacute;", "&iacute;", "&oacute;", "&uacute;", "&ntilde;", "&uuml;"}
    };

    private static final String[][] CHARS_UPPER = {
        {"\u00C1", "\u00C9", "\u00CD", "\u00D3", "\u00DA", "\u00D1", "\u00DC"},
        {"&Aacute;", "&Eacute;", "&Iacute;", "&Oacute;", "&Uacute;", "&Ntilde;", "&Uuml;"}
    };
    
    @Inject
    private Mapper mapper;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(ResponseVO<?> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(ResponseVO<?> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try (BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(entityStream))) {
            String json = null;
            try {
                json = mapper.parseObjectToJSON(t);
            } catch (MapperParseJSONException e) {
                LOGGER.log(Level.INFO, "writeTo CustomMarshaller JSON: {0}",e.getMessage());
                throw new IOException(e);
            }
            buffer.write(processSpecialCharacters(json));
        }
    }

    private String processSpecialCharacters(String message) {
        String process = message;
        if (process != null) {
            int len = CHARS_LOWER[0].length;
            int pos = 0;            
            while (pos < len) {
                process = process.replace(CHARS_LOWER[0][pos], CHARS_LOWER[1][pos]);
                process = process.replace(CHARS_UPPER[0][pos], CHARS_UPPER[1][pos]);
                pos++;
            }
        }
        return process;
    }
}
