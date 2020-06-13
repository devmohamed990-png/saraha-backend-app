package com.saraha.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BackendReciveMessageDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long messageID;

	private String message;

	private BackendReciveUserDTO user;

	private BackendRecivePersonDTO person;
}