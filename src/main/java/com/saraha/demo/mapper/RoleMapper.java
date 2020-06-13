package com.saraha.demo.mapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saraha.demo.dto.BackendResponseRoleDTO;
import com.saraha.demo.model.Role;

@Component
public class RoleMapper {

	@Autowired
	private ModelMapper modelMapper;

	private List<BackendResponseRoleDTO> backendResponseRoleDTOList;

	public List<BackendResponseRoleDTO> convertRoleListToBackendResponseRoleDTOList(List<Role> sourceList) {

		if (sourceList.isEmpty()) {

			return null;

		} else {

			backendResponseRoleDTOList = new ArrayList<BackendResponseRoleDTO>();

			Type backendResponseRoleDTOListType = new TypeToken<List<BackendResponseRoleDTO>>() {
			}.getType();

			backendResponseRoleDTOList = modelMapper.map(sourceList, backendResponseRoleDTOListType);

			System.out.println("Yes >>>>>>>>>>>>>>>>>>>>>>> " + backendResponseRoleDTOList);
			
			return backendResponseRoleDTOList;
		}
	}
}