package com.epam.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dao.CourseRepo;
import com.epam.dto.CourseDto;
import com.epam.exceptions.CourseExistException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.exceptions.InstructorNotFoundException;
import com.epam.exceptions.UnAuthorizedException;
import com.epam.services.AppUserDetailsService;
import com.epam.services.CourseService;
import com.epam.utility.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseRestController.class)
class CourseRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private CourseService courseService;

	@Mock
	CourseRepo courseRepo;
	
	@MockBean
	AppUserDetailsService appUserDetailsService;

	@MockBean
	JwtUtil jwtUtil;
	
	List<CourseDto> mockCourses;
	CourseDto c1,c2;
	@BeforeEach
	public void setUp() {
		
		c1 = new CourseDto();
		c1.setCourseId("c1");
		c1.setCourseName("java");
		c1.setCourseDescription("java 9");

		c2 = new CourseDto();
		c2.setCourseId("c2");
		c2.setCourseName("java");
		c2.setCourseDescription("java 8");
		
		mockCourses = new ArrayList<CourseDto>();
		mockCourses.add(c1);
		mockCourses.add(c2);
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void viewAllCoursesTest() throws JsonProcessingException, Exception {

		when(courseService.viewAllCourses()).thenReturn(mockCourses);

		MvcResult mvcResult = mockMvc.perform(get("/courses")).andExpect(status().isOk()).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void viewInstructorCoursesTest() throws JsonProcessingException, Exception {

		when(courseService.viewCoursesByInstructor("p1")).thenReturn(mockCourses);
		MvcResult mvcResult = mockMvc.perform(get("/courses/of/p1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void instructorNotFoundExceptionTest() throws Exception {
	
		when(courseService.viewCoursesByInstructor(any())).thenThrow(InstructorNotFoundException.class);

		MvcResult mvcResult = mockMvc.perform(get("/courses/of/p1"))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void deleteCourseTest() throws JsonProcessingException, Exception {
	
		MvcResult mvcResult = mockMvc.perform(delete("/courses/c1?username=p1"))
								.andExpect(status().isOk())
								.andReturn();
		
		assertEquals("Deleted Successfully", mvcResult.getResponse().getContentAsString());
		verify(courseService, times(1)).delete(any(),any());
	}

	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void deleteCourseWithInvalidIdTest() throws JsonProcessingException, Exception {
			
		doThrow(CourseNotFoundException.class).when(courseService).delete(any(), any());

		MvcResult mvcResult = mockMvc.perform(delete("/courses/c55?username=p7"))
								.andExpect(status().isBadRequest())
								.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void addCourseTest() throws Exception {
	
		when(courseService.add("p1", c2)).thenReturn(c2);

		MvcResult mvcResult = mockMvc.perform(post("/courses/p1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(c2))).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void addExistingCourseTest() throws Exception {

		when(courseService.add(any(),any())).thenThrow(CourseExistException.class);
		
		MvcResult mvcResult	= mockMvc.perform(post("/courses/p1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(c2)))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertEquals(400, mvcResult.getResponse().getStatus());
		verify(courseService, times(1)).add(any(), any());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void unAuthorizedExceptionTest() throws Exception {
		
		doThrow(UnAuthorizedException.class).when(courseService).delete(any(), any());
		
		MvcResult mvcResult	= mockMvc.perform(delete("/courses/c01?username=vineeth"))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertEquals(400, mvcResult.getResponse().getStatus());
		verify(courseService, times(1)).delete(any(), any());
	}
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void fetchCourseTest() throws Exception {
		
		when(courseService.findCourseById(any())).thenReturn(c1);
		MvcResult mvcResult	= mockMvc.perform(get("/courses/c1"))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	@Test
	@WithMockUser(username="vineeth", roles= {"ADMIN"})
	void updateCourseTest() throws Exception {
	
		when(courseService.update(any())).thenReturn(c2);

		MvcResult mvcResult = mockMvc.perform(put("/courses").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(c2))).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
	}
}
