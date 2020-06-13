package com.saraha.demo.service;

import java.util.List;
import java.util.Map;

import com.saraha.demo.dto.BackendResponseMessageDTO;
import com.saraha.demo.model.Message;
import com.saraha.demo.model.Person;
import com.saraha.demo.model.User;

public interface MessageService {

	public List<BackendResponseMessageDTO> findAll();

	public BackendResponseMessageDTO findById(long id);

	public List<BackendResponseMessageDTO> findByUsername();
	
	public void deleteById(long id);

	public void save(String message, long randomNumber, long key);

	public void update(Person person, long id);

	public void delete(long id);

	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(long randomNumber);

	public Message findByUserIdAndKey(User user, long key);
}