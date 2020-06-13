package com.saraha.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saraha.demo.model.Tables;

public interface TableDAO extends JpaRepository<Tables, Long> {

}