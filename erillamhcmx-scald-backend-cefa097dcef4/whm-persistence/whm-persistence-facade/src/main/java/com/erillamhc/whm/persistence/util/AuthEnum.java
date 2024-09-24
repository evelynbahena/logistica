package com.erillamhc.whm.persistence.util;

/**
*
* @author Ivo Danic G.
*/
public enum AuthEnum {
   ADMIN(1), EJECUTIVO(2), USUARIO(3);
   
   private final Integer auth;
   
   private AuthEnum(Integer auth) {
       this.auth = auth;
   }

   public Integer getAuth() {
       return auth;
   }
}
