package com.saraha.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MY_DATABASE")
@Data
public class Tables extends BaseIdEntity{


	@Column(name = "TABLE_NAME")
	private String table;	
}