package com.epam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.InstructorRepo;
import com.epam.entities.InstructorEntity;

@Service
public class LoginService {
	
	@Autowired
	private InstructorRepo instructorRepo;

	public boolean login(String username, String password) {
		boolean status = false;
		if(instructorRepo.existsById(username) ) {
			InstructorEntity instructorEntity = instructorRepo.getById(username);
			if(instructorEntity.getUsername().equals(username) && instructorEntity.getPassword().equals(password)) {
				status = true;
			}
		}
		return status;
	}
}
