package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.services.AppUserDetailsService;

@WebMvcTest(InstructorDashboardController.class)
class InstructorDashBoardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	AppUserDetailsService appUserDetailsService; 

	@Test
	@WithMockUser(username="pavan", roles= {"USER"})
	void testViewInstructorDashBoardPage() throws Exception {
		mockMvc.perform(get("/dashboard")).andExpect(view().name("dashboard")).andExpect(status().isOk());
	}

}
