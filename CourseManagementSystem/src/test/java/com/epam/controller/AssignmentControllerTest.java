package com.epam.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.AssignmentDto;
import com.epam.exceptions.AssignmentExistException;
import com.epam.exceptions.AssignmentNotFoundException;
import com.epam.services.AppUserDetailsService;
import com.epam.services.AssignmentService;
import com.epam.utility.Principle;

@WebMvcTest(AssignmentController.class)
class AssignmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AssignmentService assignmentService;

	@MockBean
	AppUserDetailsService appUserDetailsService;
	AssignmentDto assignmentDto;
	List<AssignmentDto> mockAssignments;

	@BeforeEach
	public void setUp() {
		assignmentDto = new AssignmentDto();
		assignmentDto.setAssignmentId("a1");
		assignmentDto.setAssignmentName("java");
		assignmentDto.setDeadlineInHours((long) 25.0);
		assignmentDto.setMarks((long) 30);

		mockAssignments = new ArrayList<>();
		mockAssignments.add(assignmentDto);
	}

	@AfterEach
	public void tearDown() {

	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void testViewAssignmentDashboard() throws Exception {
		mockMvc.perform(get("/assignmentDashboard")).andExpect(view().name("assignmentDashboard"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addAssignmentViewTest() throws Exception {
		mockMvc.perform(get("/addAssignment/c12")).andExpect(view().name("addAssignment")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addAssignmentForAcourseTest() throws Exception {

		when(assignmentService.add(any(), any())).thenReturn(assignmentDto);

		mockMvc.perform(post("/assignmentInputForm/c12").param("assignmentId", "a1").param("assignmentName", "java")
				.param("numberOfQuestions", "2").param("deadlineInHours", "3").param("marks", "30"))
				.andExpect(view().name("assignmentStatus"));

		verify(assignmentService, times(1)).add(any(), any());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addExistingAssignmentForAcourseTest() throws Exception {

		when(assignmentService.add(any(), any())).thenThrow(AssignmentExistException.class);

		mockMvc.perform(post("/assignmentInputForm/c12").param("assignmentId", "a1").param("assignmentName", "java")
				.param("numberOfQuestions", "2").param("deadlineInHours", "3").param("marks", "30"))
				.andExpect(view().name("assignmentError"));

		verify(assignmentService, times(1)).add(any(), any());
		assertThrows(AssignmentExistException.class, () -> assignmentService.add(any(), any()));
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void displayAssignmentsBasedOnCourseIdViewTest() throws Exception {
		mockMvc.perform(get("/displayAssignmentsBasedOnCourseId"))
				.andExpect(view().name("displayAssignmentsBasedOnCourseId")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void assignmentNotFoundExceptionTest() throws Exception {

		when(assignmentService.findAssignmentById("a10")).thenThrow(AssignmentNotFoundException.class);

		mockMvc.perform(post("/assignmentDashboard/find").param("assignmentId", "a10"))
				.andExpect(view().name("assignmentError"));

		verify(assignmentService, times(1)).findAssignmentById("a10");

		assertThrows(AssignmentNotFoundException.class, () -> {
			assignmentService.findAssignmentById("a10");
		});
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void testDisplayAssignmentsForAcourse() throws Exception {

		when(assignmentService.viewAssignmentsForAcourse("c1")).thenReturn(mockAssignments);
		mockMvc.perform(post("/viewAssignmentByCourseId").param("courseId", "c1"))
				.andExpect(view().name("viewAssignments")).andExpect(status().isOk())
				.andExpect(model().attribute("assignments", hasSize(1)));

		verify(assignmentService, times(1)).viewAssignmentsForAcourse("c1");
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void findAssignmentByIdViewTest() throws Exception {
		mockMvc.perform(get("/findAssignmentById")).andExpect(view().name("findAssignmentById"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void findAssignmentByIdTest() throws Exception {
		mockMvc.perform(post("/assignmentDashboard/find").param("assignmentId", "a1"))
				.andExpect(view().name("viewAssignment")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void deleteAssignmentTest() throws Exception {
		mockMvc.perform(get("/assignment/delete/a1")).andExpect(view().name("assignmentStatus"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateAssignmentViewTest() throws Exception {
		mockMvc.perform(get("/assignment/updateview/a1")).andExpect(view().name("updateAssignment"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateAssignmentTest() throws Exception {
		when(mock(Principle.class).getUsername()).thenReturn("pavan");

		when(assignmentService.update(any())).thenReturn(assignmentDto);
		mockMvc.perform(post("/assignment/update/a1")
				.param("assignmentId", "a1")
				.param("assignmentName", "java")
				.param("numberOfQuestions", "2")
				.param("deadlineInHours", "3")
				.param("marks", "30"))
				.andExpect(view().name("assignmentStatus"));
		verify(assignmentService, times(1)).update(any());
	}
	
	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateAssignmentWithBlankNameTest() throws Exception {
		
		mockMvc.perform(post("/assignment/update/a1")
				.param("assignmentId", "a1")
				.param("assignmentName", "")
				.param("numberOfQuestions", "2")
				.param("deadlineInHours", "3")
				.param("marks", "30"))
				.andExpect(view().name("updateAssignment"));
		verify(assignmentService, times(0)).update(any());
	}
}
