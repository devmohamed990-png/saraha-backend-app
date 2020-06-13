package com.saraha.demo.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saraha.demo.dto.BackendResponseUserDTO;
import com.saraha.demo.service.UserService;

@Component
public class PermissionUserPattern {

	@Autowired
	private UserService userService;

	public BackendResponseUserDTO findByUsername(String username) {

		return userService.findByUsername(username);
	}
}