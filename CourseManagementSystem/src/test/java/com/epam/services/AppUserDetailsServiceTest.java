package com.epam.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epam.dao.AuthGroupRepo;
import com.epam.dao.InstructorRepo;
import com.epam.entities.InstructorEntity;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {

	    @Mock
	    InstructorRepo instructorRepository;
	    @Mock
	    AuthGroupRepo authGroupRepository;
	    AppUserDetailsService appUserDetailsService;
	    
	    InstructorEntity instructor;
	    @BeforeEach
	    void setUp() {
	        instructorRepository= Mockito.mock(InstructorRepo.class);
	        authGroupRepository=Mockito.mock(AuthGroupRepo.class);
	        appUserDetailsService= new AppUserDetailsService(instructorRepository,authGroupRepository);
	        instructor=new InstructorEntity();
	        instructor.setInstructorName("vineeth gogu");
	        instructor.setUsername("vineeth");
	        instructor.setPassword("password");
	    }

	    @AfterEach
	    void tearDown() {
	        instructorRepository=null;
	        authGroupRepository=null;
	    }

	    @Test
	    void loadUserByUsername() {

	        when(instructorRepository.findByUsername(anyString())).thenReturn(instructor);
	        when(authGroupRepository.findByUsername(anyString())).thenReturn(new ArrayList<>());
	        assertEquals("vineeth",appUserDetailsService.loadUserByUsername("vineeth").getUsername());
	    }

	    @Test
	    void testLoadUserByUsernameForException() {
	        assertThrows(UsernameNotFoundException.class,()->appUserDetailsService.loadUserByUsername("vineeth"));
	    }
}
