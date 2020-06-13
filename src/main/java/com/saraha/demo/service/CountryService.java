package com.saraha.demo.service;

import java.util.List;

import com.saraha.demo.dto.BackendReciveCountryDTO;
import com.saraha.demo.dto.BackendResponseCountryDTO;

public interface CountryService {

	public List<BackendResponseCountryDTO> findAll();

	public List<BackendResponseCountryDTO> findAllExceptID(long id);
	
	public BackendResponseCountryDTO findById(long id);

	public void save(BackendReciveCountryDTO backendReciveCountryDTO);

	public void update(BackendReciveCountryDTO backendReciveCountryDTO);

	public void delete(long id);
}