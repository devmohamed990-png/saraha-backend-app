package com.saraha.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

	@Query("SELECT R FROM Role R WHERE R.id != ?1")
	public List<Role> findAllExceptID(long id);
}