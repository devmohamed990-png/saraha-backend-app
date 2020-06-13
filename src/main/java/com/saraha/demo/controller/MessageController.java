package com.saraha.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendResponseMessageDTO;
import com.saraha.demo.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BackendResponseMessageDTO> findAll() {

		return messageService.findAll();
	}

	@GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public BackendResponseMessageDTO findById(@RequestParam("id") long id) {

		return messageService.findById(id);
	}

	@GetMapping(value = "/find-by-username", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BackendResponseMessageDTO> findById() {

		return messageService.findByUsername();
	}
	
	@DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteById(@RequestParam("message") long id) {

		messageService.deleteById(id);
	}

	@GetMapping(value = "/check-random-number", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(@RequestParam("randomNumber") long key) {

		return messageService.checkIfNumberInDatabaseOrNot(key);
	}
}