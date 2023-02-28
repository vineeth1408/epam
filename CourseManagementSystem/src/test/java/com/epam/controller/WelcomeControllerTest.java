package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.services.AppUserDetailsService;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppUserDetailsService appUserDetailsService; 
	
	@Test
	void welcomePageTest() throws Exception {
		mockMvc.perform(get("/")).andExpect(view().name("welcome")).andExpect(status().isOk());
	}
}