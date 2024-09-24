package com.erillamhc.whm.business.management.i18n;

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
public class MessageManagementProvider {
    
    private ResourceBundle messageManagementBundle;
    
    public MessageManagementProvider() {
        init();
    }
    
    private void init() {
        if( messageManagementBundle == null) {
            messageManagementBundle = ResourceBundle.getBundle("message.MessageManagement", new Locale("es", "MX"));
        }
    }
    
    public String getString(String key) {
        return messageManagementBundle.getString(key);
    }
    
    public String getString(String key, Object... params) {
        return MessageFormat.format(messageManagementBundle.getString(key), params);
    }
}
