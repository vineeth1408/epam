package com.epam.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dao.InstructorRepo;
import com.epam.entities.InstructorEntity;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@Mock
	InstructorRepo instructorRepo;
	
	@InjectMocks
	LoginService loginService;
	
	InstructorEntity instructorEntity;
	
	@BeforeEach
	public void setUp() {
		instructorEntity = new InstructorEntity();
		instructorEntity.setUsername("p1");
		instructorEntity.setPassword("password");
	}
	
	@Test
	void loginValidTest() {
		when(instructorRepo.existsById("p1")).thenReturn(true);
		
		when(instructorRepo.getById("p1")).thenReturn(instructorEntity);
		
		assertTrue(loginService.login("p1", "password")); 
	}
	
	@Test
	void loginInValidTest() {
		when(instructorRepo.existsById("p1")).thenReturn(false);
		
		assertFalse(loginService.login("p1", "password")); 
	}

}
