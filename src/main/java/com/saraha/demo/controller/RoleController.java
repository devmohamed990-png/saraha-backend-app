package com.saraha.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendResponseRoleDTO;
import com.saraha.demo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/findAllExceptID", produces = "application/json")
	public List<BackendResponseRoleDTO> getRolesExceptID(@RequestParam("id") long id) {

		return roleService.findAllExceptID(id);
	}
}