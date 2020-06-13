package com.saraha.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saraha.demo.dto.BackendResponseCountryDTO;
import com.saraha.demo.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping(value = "/findAll", produces = "application/json")
	public List<BackendResponseCountryDTO> getCountries() {

		return countryService.findAll();
	}

	@GetMapping(value = "/findAllExceptID", produces = "application/json")
	public List<BackendResponseCountryDTO> getCountriesExceptID(@RequestParam("id") long id) {

		return countryService.findAllExceptID(id);
	}
}