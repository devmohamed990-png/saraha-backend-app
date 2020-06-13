package com.saraha.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BackendResponseUserDTO implements Serializable {

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("userID")
	private int id;

	@JsonProperty("userFirstName")
	private String firstName;

	@JsonProperty("userLastName")
	private String lastName;

	@JsonProperty("userPhone")
	private String phone;

	@JsonProperty("userBirthDay")
	private Date birthDay;

	@JsonProperty("userEmail")
	private String email;

	@JsonProperty("userUsername")
	private String username;

	@JsonProperty("userPassword")
	private String password;

	@JsonProperty("userGeneder")
	private Boolean isMale;

	@JsonProperty("url")
	private String url;

	@JsonProperty("userCountry")
	private BackendResponseCountryDTO country;

	@JsonProperty("userMessages")
	private List<BackendResponseMessageDTO> messages;

	@JsonProperty("userRole")
	private BackendResponseRoleDTO role;
}