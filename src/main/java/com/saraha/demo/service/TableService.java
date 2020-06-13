package com.saraha.demo.service;

import java.util.List;

import com.saraha.demo.dto.BackendResponseTableDTO;

public interface TableService {

	public List<BackendResponseTableDTO> findAll();
}