package com.saraha.demo.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.CountryDAO;
import com.saraha.demo.dto.BackendReciveCountryDTO;
import com.saraha.demo.dto.BackendResponseCountryDTO;
import com.saraha.demo.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;

	@Autowired
	private ModelMapper modelMapper;

	private Country country;

	private BackendResponseCountryDTO backendResponseCountryDTO;

	private List<Country> countryList;

	private List<BackendResponseCountryDTO> backendResponseCountryDTOList;

	@Override
	public List<BackendResponseCountryDTO> findAll() {

		countryList = new ArrayList<Country>();

		countryList = countryDAO.findAll();

		if (countryList.isEmpty()) {

			return null;

		} else {

			backendResponseCountryDTOList = new ArrayList<BackendResponseCountryDTO>();

			Type listType = new TypeToken<List<BackendResponseCountryDTO>>() {
			}.getType();

			backendResponseCountryDTOList = modelMapper.map(countryList, listType);

			return backendResponseCountryDTOList;
		}
	}

	@Override
	public List<BackendResponseCountryDTO> findAllExceptID(long id) {

		countryList = new ArrayList<Country>();

		countryList = countryDAO.findAllExceptID(id);

		if (countryList.isEmpty()) {

			return null;

		} else {

			backendResponseCountryDTOList = new ArrayList<BackendResponseCountryDTO>();

			Type listType = new TypeToken<List<BackendResponseCountryDTO>>() {
			}.getType();

			backendResponseCountryDTOList = modelMapper.map(countryList, listType);

			return backendResponseCountryDTOList;
		}
	}

	@Override
	public BackendResponseCountryDTO findById(long id) {

		Optional<Country> countryOptional = countryDAO.findById(id);

		if (countryOptional.isPresent()) {

			backendResponseCountryDTO = new BackendResponseCountryDTO();

			backendResponseCountryDTO = modelMapper.map(countryOptional.get(), BackendResponseCountryDTO.class);

			return backendResponseCountryDTO;

		} else {

			return null;
		}
	}

	@Override
	public void save(BackendReciveCountryDTO backendReciveCountryDTO) {

		if (backendReciveCountryDTO != null) {

			if (findById(backendReciveCountryDTO.getCountryID()) == null) {

				country = new Country();

				country.setCountry(backendReciveCountryDTO.getCountry());

				countryDAO.save(country);
			}
		}
	}

	@Override
	public void update(BackendReciveCountryDTO backendReciveCountryDTO) {

		if (backendReciveCountryDTO != null) {
			if (findById(backendReciveCountryDTO.getCountryID()) != null) {

				countryDAO.upadte(backendReciveCountryDTO.getCountry(), backendReciveCountryDTO.getCountryID());
			}
		}
	}

	@Override
	public void delete(long id) {

		if (id > 0) {

			countryDAO.deleteById(id);
		}
	}
}