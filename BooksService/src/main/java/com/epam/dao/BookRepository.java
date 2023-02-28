package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, String> {
	
}
