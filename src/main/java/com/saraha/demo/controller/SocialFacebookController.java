package com.saraha.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.saraha.demo.service.FacebookService;

@RestController
@RequestMapping("/facebook")
public class SocialFacebookController {

	@Autowired
	private FacebookService facebookService;

	@PostMapping(value = "/facebook-token", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> producer(@RequestBody String message,
			@RequestParam("randomNumber") long randomNumber, @RequestParam("key") long key) {

		return facebookService.producer(message, randomNumber, key);
	}

	@GetMapping(value = "/facebook-test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test() {

		return new ResponseEntity<String>("Mohamed Arafa", HttpStatus.ACCEPTED);
	}
}
