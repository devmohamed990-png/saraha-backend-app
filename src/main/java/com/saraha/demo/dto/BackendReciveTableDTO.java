package com.saraha.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BackendReciveTableDTO implements Serializable{

	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long tableID;

	private String table;
}