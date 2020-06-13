package com.saraha.demo.service;

import java.util.List;

import com.saraha.demo.dto.BackendResponseRoleDTO;

public interface RoleService {

	public List<BackendResponseRoleDTO> findAllExceptID(long id);
}