package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entities.AssignmentEntity;

public interface AssignmentRepo extends JpaRepository<AssignmentEntity, String> {

}