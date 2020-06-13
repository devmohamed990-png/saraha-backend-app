package com.saraha.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person extends BaseIdEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "is_male", columnDefinition = "BIT DEFAULT 1")
	private Boolean isMale;

	@Column(name = "pic")
	private String profilePictureUrl;

	@Column(name = "person_key", unique = true)
	public long personKey;

	@Column(name = "facebook_code", columnDefinition = "TEXT", unique = true)
	private String facebookCode;
}