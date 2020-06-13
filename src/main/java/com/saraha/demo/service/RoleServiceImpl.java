package com.saraha.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.RoleDAO;
import com.saraha.demo.dto.BackendResponseRoleDTO;
import com.saraha.demo.mapper.RoleMapper;
import com.saraha.demo.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private RoleMapper roleMapper;

	private List<Role> roleList;

	@Override
	public List<BackendResponseRoleDTO> findAllExceptID(long id) {

		roleList = new ArrayList<Role>();

		roleList = roleDAO.findAllExceptID(id);

		if (roleList.isEmpty())
			return null;
		else
			return roleMapper.convertRoleListToBackendResponseRoleDTOList(roleList);
	}
}