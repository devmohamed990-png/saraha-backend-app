package com.saraha.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BackendRecivePersonDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long personID;

	private String personName;

	private String personEmail;

	private Boolean personGeneder;

	private String personImage;
}