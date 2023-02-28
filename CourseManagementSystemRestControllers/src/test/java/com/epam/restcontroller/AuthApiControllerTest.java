package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.AuthenticationRequest;
import com.epam.services.AppUserDetailsService;
import com.epam.utility.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthApiController.class)
class AuthApiControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AuthenticationRequest authenticationRequest;

	@MockBean
	JwtUtil jwtUtil;

	@MockBean
	UsernamePasswordAuthenticationToken authToken;

	@MockBean
	UserDetails userDetails;

	@MockBean
	AuthenticationManager authenticationManager;

	@MockBean
	AppUserDetailsService userDetailsService;


	
	@Test
	void createTokenTest() throws Exception{
		ObjectMapper mapper=new ObjectMapper();
		AuthenticationRequest authRequest=new AuthenticationRequest("hi","hello");
		when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
		when(jwtUtil.generateToken(any())).thenReturn("gfgf");
		mockMvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(authRequest)))
				.andExpect(status().isOk());
	}
	@Test
	void exceptionTest() throws Exception{
		AuthenticationRequest authRequest=new AuthenticationRequest("hi","hello");
		when(userDetailsService.loadUserByUsername(any())).thenThrow(BadCredentialsException.class);
		
	MvcResult	result = mockMvc.perform(post("/authenticate")).andExpect(status().isBadRequest()).andReturn();
	assertEquals(400, result.getResponse().getStatus());
	}
}
