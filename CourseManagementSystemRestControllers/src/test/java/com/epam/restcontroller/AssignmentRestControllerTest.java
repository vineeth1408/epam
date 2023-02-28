package com.epam.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.AssignmentDto;
import com.epam.services.AppUserDetailsService;
import com.epam.services.AssignmentService;
import com.epam.utility.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AssignmentRestController.class)
class AssignmentRestControllerTest {

	@MockBean
	AssignmentService assignmentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	AppUserDetailsService appUserDetailsService;
	
	@MockBean
	JwtUtil jwtUtil;
	
	List<AssignmentDto> assignments;
	AssignmentDto a1;
	
	@BeforeEach
	public void setUp() {
		a1 = new AssignmentDto();
		a1.setAssignmentId("a1");
		a1.setAssignmentName("java 8 assignment");
		a1.setDeadlineInHours(20);
		a1.setMarks(20);
		a1.setNumberOfQuestions(5);
		assignments = new ArrayList<AssignmentDto>();
		assignments.add(a1);
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void getAllAssignmentsTest() throws Exception {
		
		
		when(assignmentService.viewAllAssignments()).thenReturn(assignments);
	MvcResult mvcResult	= mockMvc.perform(get("/assignments").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void addAssignmentTest() throws Exception {
		
		when(assignmentService.add(any(),any())).thenReturn(a1);
	MvcResult mvcResult	= mockMvc.perform(post("/assignments/c1").contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(objectMapper.writeValueAsString(a1))).andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void findAssignmentTest() throws Exception {
		
		
		when(assignmentService.findAssignmentById("a1")).thenReturn(a1);
	MvcResult mvcResult	= mockMvc.perform(get("/assignments/a1").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void deleteAssignmentTest() throws JsonProcessingException, Exception {
	
		MvcResult mvcResult = mockMvc.perform(delete("/assignments/a1"))
								.andExpect(status().isOk())
								.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		verify(assignmentService, times(1)).delete("a1");
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void getAssignmnetsForAcourse() throws Exception {
		
		when(assignmentService.viewAssignmentsForAcourse("c1")).thenReturn(assignments);
		MvcResult mvcResult = mockMvc.perform(get("/assignments/course/c1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());		
	}
}
