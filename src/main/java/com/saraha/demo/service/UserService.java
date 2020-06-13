package com.saraha.demo.service;

import java.util.List;

import com.saraha.demo.dto.BackendReciveUserDTO;
import com.saraha.demo.dto.BackendResponseUserDTO;
import com.saraha.demo.model.User;

public interface UserService {

	List<BackendResponseUserDTO> findAll();

	BackendResponseUserDTO findById(long id);

	User findByKey(long key);

	BackendResponseUserDTO findByUsername(String username);

	void save(BackendReciveUserDTO backendReciveUserDTO);

	void update(BackendReciveUserDTO backendReciveUserDTO);

	void delete(long id);

	List<BackendResponseUserDTO> findAllWithoutThisUser(long id);
}