package com.saraha.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.PermissionDAO;
import com.saraha.demo.dto.BackendResponsePermissionDTO;
import com.saraha.demo.mapper.PermissionMapper;
import com.saraha.demo.model.Permission;
import com.saraha.demo.pattern.PermissionUserPattern;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private PermissionUserPattern permissionUserPattern;

	private List<Permission> permissionList;

	private List<BackendResponsePermissionDTO> backendResponsePermissionDTOList;

	private List<Long> permissionIdList;

	@Override
	public List<BackendResponsePermissionDTO> findAllWithoutThisUserPermission(String username) {

		permissionList = new ArrayList<Permission>();

		backendResponsePermissionDTOList = new ArrayList<BackendResponsePermissionDTO>();

		permissionIdList = new ArrayList<Long>();

		backendResponsePermissionDTOList = permissionUserPattern.findByUsername(username).getRole().getPermissions();

		for (BackendResponsePermissionDTO backendResponsePermissionDTO : backendResponsePermissionDTOList) {

			permissionIdList.add(backendResponsePermissionDTO.getId());
		}

		permissionList = permissionDAO.findAllWithoutThisUserPermission(permissionIdList);

		if (permissionList.isEmpty())
			return null;
		else
			return permissionMapper.convertPermissionListToBackendResponsePermissionDTOList(permissionList);
	}
}