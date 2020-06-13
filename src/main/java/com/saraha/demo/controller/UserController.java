package com.saraha.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendReciveUserDTO;
import com.saraha.demo.dto.BackendResponseUserDTO;
import com.saraha.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/find-all", produces = "application/json")
	public List<BackendResponseUserDTO> getAllUsers() {

		return userService.findAll();
	}

	@GetMapping(value = "/find-all-without-user", produces = "application/json")
	public List<BackendResponseUserDTO> getAllUsersWithoutThisUser(@RequestParam("id") long id) {

		return userService.findAllWithoutThisUser(id);
	}

	@PostMapping(value = "/find-by-id", produces = "application/json")
	public BackendResponseUserDTO getUserByID(@RequestParam("id") long id) {

		return userService.findById(id);
	}

	@PostMapping(value = "/find-by-username", produces = "application/json")
	public BackendResponseUserDTO getUserByUsername(@RequestParam("username") String username) {

		return userService.findByUsername(username);
	}

	@PostMapping(value = "/save", consumes = "application/json")
	public void save(@RequestBody BackendReciveUserDTO backendReciveUserDTO) {

		userService.save(backendReciveUserDTO);
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public void update(@RequestBody BackendReciveUserDTO backendReciveUserDTO) {

		userService.update(backendReciveUserDTO);
	}

	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam("id") long id) {

		userService.delete(id);
	}
}