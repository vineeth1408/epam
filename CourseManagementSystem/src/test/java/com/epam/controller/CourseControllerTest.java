package com.epam.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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

import com.epam.dto.CourseDto;
import com.epam.exceptions.CourseExistException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.services.AppUserDetailsService;
import com.epam.services.CourseService;
import com.epam.utility.Principle;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CourseService courseService;

	@MockBean
	AppUserDetailsService appUserDetailsService;

	CourseDto courseDto;

	@BeforeEach
	public void setUp() {

		courseDto = new CourseDto();
		courseDto.setCourseName("java");
		courseDto.setCourseId("c55");
		courseDto.setCourseDescription("oops");
	}

	@AfterEach
	public void teaarDown() {
		courseDto = null;
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void testDisplayAddCoursePage() throws Exception {
		mockMvc.perform(get("/addcourse")).andExpect(view().name("addcourse")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addNewCourseTest() throws Exception {

		when(mock(Principle.class).getUsername()).thenReturn("pavan");
		when(courseService.add("pavan", courseDto)).thenReturn(courseDto);

		mockMvc.perform(post("/addCourseForm").param("courseId", "c55").param("courseName", "java basics")
				.param("courseDescription", "oops")).andExpect(view().name("success"));

		verify(courseService, times(1)).add(any(), any());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addExistingCourseTest() throws Exception {

		when(mock(Principle.class).getUsername()).thenReturn("pavan");
		when(courseService.add(any(), any())).thenThrow(CourseExistException.class);

		mockMvc.perform(post("/addCourseForm").param("courseId", "c55").param("courseName", "java")
				.param("courseDescription", "java oops")).andExpect(view().name("courseError"));

		assertThrows(CourseExistException.class, () -> {
			courseService.add(any(), any());
		});
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void addCourseWithBlankCourseIdTest() throws Exception {

		mockMvc.perform(post("/addCourseForm").param("courseId", "").param("courseName", "java")
				.param("courseDescription", "java oops")).andExpect(view().name("addcourse"));

		verifyNoMoreInteractions(courseService);
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void testAddCourseWithBlankCourseName() throws Exception {

		mockMvc.perform(post("/addCourseForm").param("courseId", "c1").param("courseName", "")
				.param("courseDescription", "java oops")).andExpect(view().name("addcourse"));

		verifyNoMoreInteractions(courseService);
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void findCourseByIdViewTest() throws Exception {
		mockMvc.perform(get("/findCourseById")).andExpect(view().name("findCourseById")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void loadCourseBasedOnIdTest() throws Exception {
		mockMvc.perform(post("/courses/findCourseById").param("courseId", "c1")).andExpect(view().name("displayCourse"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void loadCourseNotFoundExceptionTest() throws Exception {

		when(courseService.findCourseById(any())).thenThrow(CourseNotFoundException.class);

		mockMvc.perform(post("/courses/findCourseById").param("courseId", "c10")).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("courseError"));

		verify(courseService, times(1)).findCourseById("c10");

		assertThrows(CourseNotFoundException.class, () -> {
			courseService.findCourseById(any());
		});
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void loadCoursePageTest() throws Exception {

		CourseDto c1 = new CourseDto();
		c1.setCourseId("c1");
		c1.setCourseName("java");
		c1.setCourseDescription("java concepts");

		List<CourseDto> mockCourses = new ArrayList<>();
		mockCourses.add(c1);

		when(mock(Principle.class).getUsername()).thenReturn("pavan");
		when(courseService.viewCoursesByInstructor(any())).thenReturn(mockCourses);

		mockMvc.perform(get("/courses")).andExpect(status().isOk()).andExpect(view().name("displayCourses"))
				.andExpect(model().attribute("courses", hasSize(1)))
				.andExpect(model().attribute("courses",
						hasItem(allOf(hasProperty("courseId", is("c1")), hasProperty("courseName", is("java")),
								hasProperty("courseDescription", is("java concepts"))))));

		verify(courseService, times(1)).viewCoursesByInstructor(any());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void deleteCourseWithValidIdTest() throws Exception {

		when(mock(Principle.class).getUsername()).thenReturn("pavan");

		mockMvc.perform(get("/courses/deletecourse/c1")).andExpect(view().name("success"));

		verify(courseService, times(1)).delete(any(), any());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateCourseViewTest() throws Exception {
		mockMvc.perform(get("/courses/updateview/c1")).andExpect(view().name("updatecourse"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateCourseTest() throws Exception {
		when(mock(Principle.class).getUsername()).thenReturn("pavan");
		
		when(courseService.update(any())).thenReturn(courseDto);
		mockMvc.perform(post("/courses/update/c1")
				.param("courseId", "c55")
				.param("courseName", "java basics")
				.param("courseDescription", "oops"))
				.andExpect(view().name("success"));
		
		verify(courseService, times(1)).update(any());
	}
	
	@Test
	@WithMockUser(username = "vineeth", roles = { "ADMIN" })
	void updateCourseWithBlankCourseNameTest() throws Exception {
		
		mockMvc.perform(post("/courses/update/c1")
				.param("courseId", "c55")
				.param("courseName", "")
				.param("courseDescription", "oops"))
				.andExpect(view().name("updatecourse"));
		
		verify(courseService, times(0)).update(any());
	}
}