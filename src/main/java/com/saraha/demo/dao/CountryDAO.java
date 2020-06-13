package com.saraha.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.Country;

public interface CountryDAO extends JpaRepository<Country, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Country C SET C.country = ?1 WHERE C.id = ?2")
	public void upadte(String country, long id);

	@Query("SELECT C FROM Country C WHERE C.id != ?1")
	public List<Country> findAllExceptID(long id);
}