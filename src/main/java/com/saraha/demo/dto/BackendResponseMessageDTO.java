package com.saraha.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BackendResponseMessageDTO implements Serializable {

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("messageID")
	private long id;

	@JsonProperty("message")
	private String message;

	@JsonProperty("user")
	private BackendResponseUserDTO backendResponseUserDTO;

	@JsonProperty("person")
	private BackendResponsePersonDTO backendResponsePersonDTO;
}