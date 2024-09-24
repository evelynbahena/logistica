package com.erillamhc.whm.services.util.constant;

import javax.ws.rs.core.MediaType;

public class ConstantService {

    public static final String HEADER_SERVICE_KEY = "SRVC_KEY";
    public static final String HEADER_AUTH_TOKEN = "AUTH_TKN";
    public static final String APPLICATION_PATH = "app";
    public static final String APPLICATION_PATH_SEC = "sc/";
    public static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON + "; charset=UTF-8";

    /**
     * Patient Management Service
     
    public static final String PATIENT_MNG_SERVICE_PATH = "psvc";
    public static final String PATIENT_MNG_ADD_PATIENT = "ps01";
    public static final String HL7_TEST = "ps02";
    
    /**
     * Installation Service
     
    public static final String INSTALLATION_SERVICE_PATH = "insv";
    public static final String UPDATE_SYSTEM_INFO = "is01";
    
    /**
     * User Management Service 
     
    public static final String USER_MNG_SERVICE_PATH = APPLICATION_PATH_SEC + "ussvc";
    public static final String USER_MNG_ADD = "us01";
    public static final String USER_MNG_ALL_TYPE = "us02";
    
    /**
     * Product Management Service 
     
    public static final String PROD_MNG_SERVICE_PATH = APPLICATION_PATH_SEC + "prsvc";
    public static final String PROD_MNG_ADD = "pr01";
    public static final String PROD_MNG_ALL = "pr02";
    
    
    /**
     * Study Management Service 
     
    public static final String STUDY_MNG_SERVICE_PATH = APPLICATION_PATH_SEC + "stdsvc";
    public static final String STUDY_MNG_START = "std01";
    public static final String STUDY_MNG_STATUS = "std07";
    public static final String INTERP_MNG_REPORT_BIRADS = "int01";
    public static final String INTERP_MNG_DISCARD = "int07";
    
    /**
     * License Management Service
     
    public static final String LICENSE_MNG_PATH = APPLICATION_PATH_SEC + "lcnsvc";
    public static final String LICENSE_MNG_VERIFICATION = "lcns01";
    public static final String LICENSE_MNG_ALL = "lcns02";
    public static final String LICENSE_MNG_CREATE = "lcns03";
    
    
    /**
     * Server Management Service
     
    public static final String SERVER_MNG_SERVICE_PATH = APPLICATION_PATH_SEC + "srvsvc";
    public static final String SERVER_MNG_ADD = "srvs01";
    public static final String SERVER_MNG_ALL = "srvs02";
    
    /**
     * Client Management Service
     
    public static final String CLIENT_MNG_PATH = APPLICATION_PATH_SEC + "clnsvc";
    public static final String CLIENT_SUMM_ALL = "clnsv01";
    public static final String CLIENT_MNG_ALL = "clnsv02";
    public static final String CLIENT_MNG_ADD = "clnsv03";
   	*/
    
    
    public static final String LOGIN_MNG_PATH = APPLICATION_PATH_SEC + "lsvc";
    public static final String LOGIN = "ls01";
    
    public static final String STOCK_MNG_PATH = APPLICATION_PATH_SEC + "ssvc";
    public static final String STOCK_MNG_ADD = "srvs01";
    
    

    public static final String USERS_MNG_PATH = APPLICATION_PATH_SEC + "user";
    public static final String USERS_MNG_ADD = "addl";
    public static final String USERS_MNG_DEL = "del1";
    public static final String USERS_MNG_UPD = "upd1";
    public static final String USERS_MNG_GET = "get1";
    
    public static final String ROLE_MNG_PATH = APPLICATION_PATH_SEC + "role";
    public static final String ROLE_MNG_ADD = "addl";
    public static final String ROLE_MNG_DEL = "del1";
    public static final String ROLE_MNG_UPD = "upd1";
    public static final String ROLE_MNG_GET = "get1";
    
    public static final String PERM_MNG_PATH = APPLICATION_PATH_SEC + "PERM";
    public static final String PERM_MNG_ADD = "addl";
    public static final String PERM_MNG_DEL = "del1";
    public static final String PERM_MNG_UPD = "upd1";
    public static final String PERM_MNG_GET = "get1";
    
    
    
    private ConstantService() {}
}