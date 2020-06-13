package com.saraha.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendResponseTableDTO;
import com.saraha.demo.service.TableService;

@RestController
@RequestMapping("/table")
public class TableController {

	@Autowired
	private TableService tableService;

	@GetMapping(value = "/find-all", produces = "application/json")
	public List<BackendResponseTableDTO> findAll() {

		return tableService.findAll();
	}
}