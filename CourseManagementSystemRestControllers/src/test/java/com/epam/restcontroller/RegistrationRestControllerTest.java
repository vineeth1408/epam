package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.InstructorDto;
import com.epam.exceptions.InstructorExistException;
import com.epam.services.AppUserDetailsService;
import com.epam.services.RegistrationService;
import com.epam.utility.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RegistrationRestController.class)
class RegistrationRestControllerTest {

	@MockBean
	RegistrationService registrationService;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	AppUserDetailsService appUserDetailsService;

	@MockBean
	JwtUtil jwtUtil;
	InstructorDto instructorDto;
	
	@BeforeEach
	public void setUp() {
		instructorDto = new InstructorDto();
		instructorDto.setUsername("p1");
		instructorDto.setPassword("password");
		instructorDto.setInstructorName("pavan");
	}
	
	@Test
	@WithMockUser(username = "anyuser", roles = { "USER" })
	void registerNewInstructorTest() throws Exception {
	
		when(registrationService.register(instructorDto)).thenReturn(instructorDto);

		MvcResult mvcResult = mockMvc.perform(post("/register")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(objectMapper.writeValueAsString(instructorDto)))
							.andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void registerExistingInstructorTest() throws Exception {
	
		when(registrationService.register(any())).thenThrow(InstructorExistException.class);

		MvcResult mvcResult = mockMvc.perform(post("/register")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(objectMapper.writeValueAsString(instructorDto)))
							.andReturn();

		assertEquals(400, mvcResult.getResponse().getStatus());
	}
}
