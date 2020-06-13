package com.saraha.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BackendResponseTableDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("tableID")
	private long id;

	@JsonProperty("table")
	private String table;
}