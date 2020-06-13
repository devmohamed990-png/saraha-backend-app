package com.saraha.demo.service;

import java.util.Map;

public interface FacebookService {

	public Map<String, String> producer(String message, long randomNumber, long key);
}