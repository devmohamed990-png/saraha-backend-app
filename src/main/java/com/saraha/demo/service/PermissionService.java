package com.saraha.demo.service;

import java.util.List;

import com.saraha.demo.dto.BackendResponsePermissionDTO;

public interface PermissionService {

	public List<BackendResponsePermissionDTO> findAllWithoutThisUserPermission(String username);
}