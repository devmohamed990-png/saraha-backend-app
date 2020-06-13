package com.saraha.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.saraha.demo.model.Message;
import com.saraha.demo.model.Person;
import com.saraha.demo.model.User;

public interface MessageDAO extends JpaRepository<Message, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Message M SET M.person = ?1 WHERE M.id = ?2")
	public void update(Person person, long id);

	@Transactional
	@Modifying
	@Query("UPDATE Message M SET M.active = ?1 WHERE M.id = ?2")
	public void updateStatus(boolean status, long id);

	public List<Message> findByUserUsernameAndActive(String username, boolean active);

	public Message findByMessageKey(long randomNumber);

	public Message findByUserAndMessageKey(User user, long key);
}