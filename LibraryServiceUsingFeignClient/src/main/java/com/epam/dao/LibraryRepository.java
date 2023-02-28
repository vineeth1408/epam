package com.epam.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer>{

	Optional<LibraryEntity> findByUsernameAndBookId(String username, String bookId);
		
}
