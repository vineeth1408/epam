package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	
}
