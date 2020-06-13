package com.saraha.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/check-random-number", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(@RequestParam("randomNumber") long randomNumber) {

		return personService.checkIfNumberInDatabaseOrNot(randomNumber);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> updatePersonAndMessage(@RequestParam("randomNumber") long randomNumberMessage,
			@RequestParam("key") long key, @RequestBody String code) {

		return personService.updatePersonAndMessage(randomNumberMessage, key, code);
	}
}