package com.erillamhc.whm.business.management.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.ejb.Singleton;

/**
 * 
 * @author Ivo Danic Garrido
 *
 */
@Singleton
public class MailNotificationProvider {
	
    private ResourceBundle messageManagementBundle;
    
    public MailNotificationProvider() {
        init();
    }
    
    private void init() {
        if( messageManagementBundle == null) {
            messageManagementBundle = ResourceBundle.getBundle("email.MailConfiguration");
        }
    }
    
    public String getString(String key) {
        return messageManagementBundle.getString(key);
    }
    
    public String getString(String key, Object... params) {
        return MessageFormat.format(messageManagementBundle.getString(key), params);
    }
}
