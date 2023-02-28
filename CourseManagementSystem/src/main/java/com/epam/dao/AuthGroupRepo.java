package com.epam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entities.AuthGroup;

public interface AuthGroupRepo extends JpaRepository<AuthGroup, Long>{
	List<AuthGroup> findByUsername(String username);
}
