package com.epam.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.UserDto;

@FeignClient(name="USERS-APP")
public interface UserClient {

	@GetMapping("/users")
	public List<UserDto> getAllUsers();
	
	@PostMapping("/users")
	public UserDto addUser(UserDto userDto); 
	
	@GetMapping("users/{username}")
	public UserDto getUserByUsername(@PathVariable("username") String username);
	
	@PutMapping("users/{username}")
	public UserDto updateUser(@PathVariable("username") String username, UserDto userDto);
	
	@DeleteMapping("users/{username}")
	public void deleteUser(@PathVariable("username") String username);
}
