package com.saraha.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BackendReciveCountryDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long countryID;

	private String country;
}