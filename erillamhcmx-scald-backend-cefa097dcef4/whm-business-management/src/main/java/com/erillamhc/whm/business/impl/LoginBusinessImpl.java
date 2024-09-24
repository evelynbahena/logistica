package com.erillamhc.whm.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.erillamhc.whm.business.LoginBusiness;

/***
 * 
 * @author Ivo Danic G.
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LoginBusinessImpl implements LoginBusiness{

}
