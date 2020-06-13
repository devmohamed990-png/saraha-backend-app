package com.saraha.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.UserDAO;
import com.saraha.demo.model.User;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private UserDAO userDAO;

	private Map<String, Object> tokenInfo;

	@Override
	public Map<String, Object> extractToken(String token) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User userModel = userDAO.findByUsername(authentication.getName());

		tokenInfo = new HashMap<String, Object>();

		tokenInfo.put("FirstName", userModel.getFirstName());
		tokenInfo.put("LastName", userModel.getLastName());
		tokenInfo.put("Phone", userModel.getPhone());
		tokenInfo.put("BirthDay", userModel.getBirthDay());
		tokenInfo.put("Country", userModel.getCountry());
		tokenInfo.put("Email", userModel.getEmail());
		tokenInfo.put("Username", userModel.getUsername());
		tokenInfo.put("Authorities", userModel.getUserAuthorities());

		if (tokenInfo.isEmpty())
			return null;
		else
			return tokenInfo;
	}
}