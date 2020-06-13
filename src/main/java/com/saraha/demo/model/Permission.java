package com.saraha.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "permission")
@Data
public class Permission extends BaseIdEntity {

	@Column(name = "name")
	private String name;
}