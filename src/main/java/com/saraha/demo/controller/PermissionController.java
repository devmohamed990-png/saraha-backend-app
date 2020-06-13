package com.saraha.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendResponsePermissionDTO;
import com.saraha.demo.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@GetMapping(value = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BackendResponsePermissionDTO> findAllWithoutThisUserPermission(
			@RequestParam("username") String username) {

		return permissionService.findAllWithoutThisUserPermission(username);
	}
}