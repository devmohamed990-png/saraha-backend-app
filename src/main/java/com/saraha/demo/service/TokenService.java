package com.saraha.demo.service;

import java.util.Map;

public interface TokenService {

	public Map<String, Object> extractToken(String token);
}