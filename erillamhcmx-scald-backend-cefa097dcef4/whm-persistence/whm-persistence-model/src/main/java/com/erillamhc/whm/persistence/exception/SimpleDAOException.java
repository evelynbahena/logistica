package com.erillamhc.whm.persistence.exception;

/**
*
* @author Ivo Danic G.
* @since 1.3
* @version 1.0
*/
public class SimpleDAOException extends Exception {

   private static final long serialVersionUID = 5018927015633554943L;
   private final String detailMessage;

   public SimpleDAOException(String message) {
       super(message);
       detailMessage = message;
   }

   public SimpleDAOException(String message, Throwable cause) {
       super(message, cause);
       detailMessage = message;
   }

   public SimpleDAOException(Throwable cause) {
       super(cause);
       detailMessage = cause.getMessage();
   }

   @Override
   public String getMessage() {
       return detailMessage;
   }

}
