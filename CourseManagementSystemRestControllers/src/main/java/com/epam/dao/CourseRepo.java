package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entities.CourseEntity;

public interface CourseRepo  extends JpaRepository<CourseEntity, String>{
}
