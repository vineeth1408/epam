package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.InstructorDto;
import com.epam.exceptions.InstructorExistException;
import com.epam.services.AppUserDetailsService;
import com.epam.services.RegistrationService;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppUserDetailsService appUserDetailsService;
	
	@MockBean
	RegistrationService registrationService;
	InstructorDto instructorDto;
	
	@BeforeEach
	public void setUp() {
		instructorDto = new InstructorDto(); 
		instructorDto.setUsername("p10");
		instructorDto.setInstructorName("Shyam");
		instructorDto.setPassword("rgukt123");
	}

	@AfterEach
	public void tearDown() {
	
	}

	@Test
	void testRegistrationPageView() throws Exception {
		mockMvc.perform(get("/registration")).andExpect(view().name("registration"))
				.andExpect(status().is2xxSuccessful());
		mockMvc.perform(get("/registration")).andExpect(view().name("registration")).andExpect(status().isOk());
	}
	
	@Test

	void testRegisterNewInstructor() throws Exception {
		
		when(registrationService.register(any())).thenReturn(instructorDto);

		mockMvc.perform(post("/registrationForm")
				.param("instructorName", "shyam")
				.param("username","p10")
				.param("password", "rgukt123"))
				.andExpect(view().name("redirect:/login"));
		
		verify(registrationService,times(1)).register(any());
	}

	@Test
	
	void testRegisterExistingInstructor() throws Exception {

		when(registrationService.register(any())).thenThrow(InstructorExistException.class);

		mockMvc.perform(post("/registrationForm").param("instructorName", "shyam")
				.param("username", "pavan")
				.param("password", "123"))
				.andExpect(view().name("error"));
		
		assertThrows(InstructorExistException.class, ()-> {
			registrationService.register(any());
			});
		
		verify(registrationService,times(2)).register(any());
	}
	
	@Test
	void testRegisterInstructorWithUsernameBlankField() throws Exception {
		
		mockMvc.perform(post("/registrationForm")
				.param("username", "")
				.param("password", "rgukt123")
				.param("instructorName", "shyam"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/registration"));
		
	}
	
	@Test
	void testRegisterInstructorWithpasswordBlankField() throws Exception {
		
		mockMvc.perform(post("/registrationForm")
				.param("username", "p1")
				.param("password", "")
				.param("instructorName", "shyam"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/registration"));
		
	}
	@Test
	void testRegisterInstructorWithNameBlankField() throws Exception {
		
		mockMvc.perform(post("/registrationForm")
				.param("username", "p1")
				.param("password", "rgukt123")
				.param("instructorName", ""))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/registration"));
		
	}
}
