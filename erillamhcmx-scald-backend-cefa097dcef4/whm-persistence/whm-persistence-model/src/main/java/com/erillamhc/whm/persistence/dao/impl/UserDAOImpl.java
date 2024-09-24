package com.erillamhc.whm.persistence.dao.impl;


import java.util.HashMap;
import java.util.Map;

import com.erillamhc.whm.persistence.dao.UserDAO;
import com.erillamhc.whm.persistence.entity.User;
import com.erillamhc.whm.persistence.exception.SimpleDAOException;


public class UserDAOImpl extends SimpleDAOImpl<User, Integer> implements UserDAO{

	@Override
	public User auth(String email, String password) throws SimpleDAOException {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		return uniqueResult("User.findByEmailAndPassword", params);
	}

}