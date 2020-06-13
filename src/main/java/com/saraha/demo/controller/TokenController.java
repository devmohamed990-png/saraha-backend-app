package com.saraha.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/extract-token", produces = "application/json")
	@PreAuthorize("#oauth2.hasScope('read') || #oauth2.hasScope('write')")
	public Map<String, Object> save(@RequestBody String token) {

		return tokenService.extractToken(token);
	}
}