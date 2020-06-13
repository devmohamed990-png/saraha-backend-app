package com.saraha.demo.service;

import java.util.List;
import java.util.Map;

import com.saraha.demo.dto.BackendResponsePersonDTO;

public interface PersonService {

	public List<BackendResponsePersonDTO> findAll();

	public BackendResponsePersonDTO findById(long id);

	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(long randomNumber);

	public Map<String, String> updatePersonAndMessage(long randomNumberMessage, long key, String code);
}