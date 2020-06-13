package com.saraha.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Long> {

	@Query("SELECT P FROM Permission P WHERE P.id NOT IN (?1)")
	public List<Permission> findAllWithoutThisUserPermission(List<Long> idIgnoreList);
}