package com.erillamhc.whm.persistence.i18n;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;

import javax.ejb.Singleton;

/**
 *
 * @author Ivo Danic G.
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MessageProviderFacade implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient ResourceBundle messageFacadeBundle;
    
    public MessageProviderFacade() {
        init();
    }
    
    private void init() {
        if( messageFacadeBundle == null) {
            messageFacadeBundle = ResourceBundle.getBundle("message.MessageFacade", new Locale("es", "MX"));
        }
    }
    
    public String getString(String key) {
        return messageFacadeBundle.getString(key);
    }
    
    public String getString(String key, Object... params) {
        return MessageFormat.format(messageFacadeBundle.getString(key), params);
    }
}
