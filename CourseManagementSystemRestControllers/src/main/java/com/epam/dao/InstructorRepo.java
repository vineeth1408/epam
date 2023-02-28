package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.epam.entities.InstructorEntity;

public interface InstructorRepo extends JpaRepository<InstructorEntity, String> {
	InstructorEntity findByUsername(String username);
}