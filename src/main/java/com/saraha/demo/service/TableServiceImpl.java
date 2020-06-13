package com.saraha.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.TableDAO;
import com.saraha.demo.dto.BackendResponseTableDTO;
import com.saraha.demo.mapper.TableMapper;
import com.saraha.demo.model.Tables;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableDAO tableDAO;

	@Autowired
	private TableMapper tableMapper;

	private List<Tables> tablesList;

	@Override
	public List<BackendResponseTableDTO> findAll() {

		tablesList = new ArrayList<Tables>();

		tablesList = tableDAO.findAll();

		if (tablesList.isEmpty())
			return null;
		else
			return tableMapper.convertTableListToBackendResponseTableDTOList(tablesList);
	}
}