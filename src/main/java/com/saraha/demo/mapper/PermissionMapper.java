package com.saraha.demo.mapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saraha.demo.dto.BackendResponsePermissionDTO;
import com.saraha.demo.model.Permission;

@Component
public class PermissionMapper {

	@Autowired
	private ModelMapper modelMapper;

	private List<BackendResponsePermissionDTO> backendResponsePermissionDTOList;

	public List<BackendResponsePermissionDTO> convertPermissionListToBackendResponsePermissionDTOList(
			List<Permission> sourceList) {

		if (sourceList.isEmpty()) {

			return null;

		} else {

			backendResponsePermissionDTOList = new ArrayList<BackendResponsePermissionDTO>();

			Type listType = new TypeToken<List<BackendResponsePermissionDTO>>() {
			}.getType();

			backendResponsePermissionDTOList = modelMapper.map(sourceList, listType);

			return backendResponsePermissionDTOList;
		}
	}
}