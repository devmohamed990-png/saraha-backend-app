package com.saraha.demo.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.UserDAO;
import com.saraha.demo.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) {

		User user = null;

		try {

			user = userDAO.findByUsername(userName);

		} catch (Exception e) {

			throw new UsernameNotFoundException("User not found");
		}

		if (user != null) {

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
					true, true, true, user.getAuthorities());
		} else {

			throw new UsernameNotFoundException("Username or Password incorrect");
		}
	}
}