package com.saraha.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BackendResponseRoleDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("roleID")
	private long id;

	@JsonProperty("role")
	private String role;

	@JsonProperty("permissions")
	private List<BackendResponsePermissionDTO> permissions;
}