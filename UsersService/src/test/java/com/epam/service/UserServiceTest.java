package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entity.UserEntity;
import com.epam.exceptions.UserExistException;
import com.epam.exceptions.UserNotFoundException;

@SpringBootTest
class UserServiceTest {
	
	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;
	
	Optional<UserEntity> optionalUser;
	UserDto userDto;
	UserEntity userEntity;
	List<UserEntity> mockusers;
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
		mockusers.add(userEntity);
	}
	
	@AfterEach
	public void tearDown() {
		userEntity = null;
		userDto = null;
		optionalUser = null;
		mockusers = null;
		
	}
	
	@Test
	void addUserTest() {
		when(userRepository.existsById(any())).thenReturn(false);
		when(userRepository.save(any())).thenReturn(userEntity);
		userDto = userService.add(userDto);
		assertEquals(userDto.getUsername(), userEntity.getUsername());
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	void addExistingUserTest() {
		when(userRepository.existsById(any())).thenReturn(true);
		assertThrows(UserExistException.class, () -> userService.add(userDto));
	}
	
	@Test
	void getUserTest() {
		when(userRepository.findById(any())).thenReturn(optionalUser);
		userDto = userService.getUserByUsername("pavan");
		assertEquals(userDto.getUsername(), optionalUser.get().getUsername());
		verify(userRepository, times(1)).findById("pavan");
	}
	@Test
	void userNotFoundTest() {
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, ()-> userService.getUserByUsername("l"));
		verify(userRepository, times(0)).findById("pavan");
	}
	@Test
	void deleteValidUserTest() {
		when(userRepository.findById(any())).thenReturn(optionalUser);
		userService.delete("pavan");
		verify(userRepository, times(1)).delete(optionalUser.get());
	}
	@Test
	void userNotFoundExceptionAtDeleteTest() {
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, ()-> userService.delete("pavan1"));
	}
	
	@Test
	void updateUserTest() {
		when(userRepository.existsById(any())).thenReturn(true);
		when(userRepository.save(any())).thenReturn(userEntity);
		userDto = userService.update("pavan", userDto);
		assertEquals(userDto.getUsername(), userEntity.getUsername());
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	void userNotFoundExceptionAtUpdateTest() {
		when(userRepository.existsById(any())).thenReturn(false);
		assertThrows(UserNotFoundException.class, ()-> userService.update("pavan1",userDto));
	}
	
	@Test
	void viewAllUsersTest() {
		when(userRepository.findAll()).thenReturn(mockusers);
		assertEquals(1, userService.viewAllUsers().size());
	}
}
