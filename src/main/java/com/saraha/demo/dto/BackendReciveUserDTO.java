package com.saraha.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BackendReciveUserDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userID;

	private String userFirstName;

	private String userLastName;

	private String userPhone;

	private Date userBirthDay;

	private BackendReciveCountryDTO userCountry;

	private String userEmail;

	private String userUsername;

	private String userPassword;

	private Boolean userEnabled;

	private Boolean userAccountNonLocked;

	private Boolean userAccountNonExpired;

	private Boolean userCredentialsNonExpired;

	private Boolean userGeneder;

	private List<BackendReciveMessageDTO> userMessages;

	private BackendReciveRoleDTO userRole;
}