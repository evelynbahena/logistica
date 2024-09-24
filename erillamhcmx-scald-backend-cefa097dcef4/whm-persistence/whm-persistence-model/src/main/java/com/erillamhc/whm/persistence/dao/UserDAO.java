package com.erillamhc.whm.persistence.dao;

//import com.erillamhc.whm.persistence.entity.User;
import com.erillamhc.whm.persistence.entity.User;
import com.erillamhc.whm.persistence.exception.SimpleDAOException;


public interface UserDAO extends SimpleDAO<User, Integer> {

	User auth(String email, String password) throws SimpleDAOException;


}
