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
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.UserDto;
import com.epam.entity.UserEntity;
import com.epam.exceptions.UserExistException;
import com.epam.exceptions.UserNotFoundException;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	UserService userService;
	
	List<UserDto> mockusers;
	Optional<UserEntity> optionalUser;
	UserDto userDto;
	UserEntity userEntity;
	
	@BeforeEach
	public void setUp() {
		userDto = new UserDto();
		userEntity = new UserEntity();
		userEntity.setUsername("pavan");
		userEntity.setEmail("12@gmail.com");
		userEntity.setName("kumar");
		
		userDto.setUsername("pavan");
		userDto.setName("kumar");
		userDto.setEmail("123@gmail.com");
		
		optionalUser = Optional.ofNullable(new UserEntity());
		optionalUser.get().setUsername("pavan");
		optionalUser.get().setName("kumar");
		optionalUser.get().setEmail("123@gmail.com");
		
		mockusers = new ArrayList<>();
		mockusers.add(userDto);
	}
	
	@AfterEach
	public void tearDown() {
		userEntity = null;
		userDto = null;
		optionalUser = null;
		mockusers = null;
	}
	
	@Test
	void viewAllUsersTest() throws Exception {
		when(userService.viewAllUsers()).thenReturn(mockusers);
		
		MvcResult mvcResult = mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void addUserTest() throws Exception {
		when(userService.add(any())).thenReturn(userDto);
		MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userDto))).andReturn();
		assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void addExistingUserTest() throws Exception {
		when(userService.add(any())).thenThrow(UserExistException.class);
		MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isBadRequest())
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void fetchUserTest() throws Exception {
		when(userService.getUserByUsername(any())).thenReturn(userDto);
		
		MvcResult mvcResult = mockMvc.perform(get("/users/p1")).andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void userNotFoundAtFetchUserTest() throws Exception {
		when(userService.getUserByUsername(any())).thenThrow(UserNotFoundException.class);
		
		MvcResult mvcResult = mockMvc.perform(get("/users/p1")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void deleteUserTest() throws Exception {
	
		MvcResult mvcResult = mockMvc.perform(delete("/users/vineeth")).andExpect(status().isNoContent()).andReturn();
		assertEquals(204, mvcResult.getResponse().getStatus());
		verify(userService, times(1)).delete("vineeth");
	}
	
	@Test
	void userNotFoundAtDeleteUserTest() throws Exception {
		doThrow(UserNotFoundException.class).when(userService).delete(any());		
		MvcResult mvcResult = mockMvc.perform(delete("/users/p999")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void updateUserTest() throws Exception {
		when(userService.update(any(), any())).thenReturn(userDto);
		MvcResult mvcResult = mockMvc.perform(put("/users/pavan").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(userDto))).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void userNotFoundAtUpdateUserTest() throws Exception {
		when(userService.update(any(), any())).thenThrow(UserNotFoundException.class);
		
		MvcResult mvcResult = mockMvc.perform(put("/users/p1")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
}
