package com.saraha.demo.mapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.saraha.demo.dto.BackendResponseTableDTO;
import com.saraha.demo.model.Tables;

@Component
public class TableMapper {

	@Autowired
	private ModelMapper modelMapper;

	private List<BackendResponseTableDTO> backendResponseTableDTOList;

	public List<BackendResponseTableDTO> convertTableListToBackendResponseTableDTOList(List<Tables> sourceList) {

		if (sourceList.isEmpty()) {

			return null;

		} else {

			Type backendResponseTableDTOListType = new TypeToken<List<BackendResponseTableDTO>>() {

				/**
				 * 
				 * 
				 * 
				 * 
				 */
				private static final long serialVersionUID = 1L;
			}.getType();

			backendResponseTableDTOList = modelMapper.map(sourceList, backendResponseTableDTOListType);

			return backendResponseTableDTOList;
		}
	}
}