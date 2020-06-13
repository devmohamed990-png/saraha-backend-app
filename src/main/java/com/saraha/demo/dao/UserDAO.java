package com.saraha.demo.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.User;

public interface UserDAO extends JpaRepository<User, Long> {

	@Query("SELECT U FROM User U JOIN FETCH U.country WHERE U.id != ?1")
	public List<User> findAllWithoutID(long id);

	@Query("SELECT U FROM User U JOIN FETCH U.role JOIN FETCH U.country WHERE U.username = ?1")
	public User findByUsername(String username);
	
	public User findByUserKey(long key);

	@Transactional
	@Modifying
	@Query("UPDATE User U SET U.firstName = ?1, U.lastName = ?2, U.phone = ?3, U.isMale = ?4, U.birthDay = ?5, U.email = ?6, U.username = ?7, U.password =?8, U.enabled = ?9, U.accountNonLocked = ?10, U.accountNonExpired = ?11, U.credentialsNonExpired = ?12,U.country.id = ?13 WHERE U.id = ?14")
	public void update(String firstName, String lastName, String phone, Boolean gender, Date birthDay, String email,
			String username, String password, Boolean enabled, Boolean accountNonLocked, Boolean accountNonExpired,
			Boolean credentialsNonExpired, long countryID, long id);

	@Transactional
	@Modifying
	@Query("UPDATE User U SET U.firstName = ?1, U.lastName = ?2, U.phone = ?3, U.isMale = ?4, U.birthDay = ?5, U.email = ?6, U.username = ?7, U.country.id = ?8, U.role.id = ?9 WHERE U.id = ?10")
	public void update(String userFirstName, String userLastName, String userPhone, Boolean userGeneder,
			Date userBirthDay, String userEmail, String userUsername, long countryID, long roleID, long userID);
}