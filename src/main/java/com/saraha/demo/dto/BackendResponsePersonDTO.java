package com.saraha.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BackendResponsePersonDTO implements Serializable {

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("personID")
	private long id;

	@JsonProperty("personName")
	private String name;

	@JsonProperty("personEmail")
	private String email;

	@JsonProperty("personGeneder")
	private boolean isMale;

	@JsonProperty("personImage")
	private String profilePictureUrl;
}