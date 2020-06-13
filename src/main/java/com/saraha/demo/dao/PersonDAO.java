package com.saraha.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.Person;

public interface PersonDAO extends JpaRepository<Person, Long> {

	public Person findByEmail(String email);

	public Person findByPersonKey(long randomNumber);

	public Person findByFacebookCode(String code);

	@Transactional
	@Modifying
	@Query("UPDATE Person P SET P.name = ?1, P.email = ?2, P.isMale = ?3, P.profilePictureUrl = ?4, P.facebookCode = ?5 WHERE P.id = ?6")
	public void update(String name, String email, boolean gender, String imageUrl, String facebookcode, long id);
}