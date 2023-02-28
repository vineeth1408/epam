package com.epam.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDto>> allUsers() {
		return ResponseEntity.ok().body(userService.viewAllUsers());
	}

	@PostMapping
	public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(userService.add(userDto), HttpStatus.CREATED);
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> fetchUser(@PathVariable() String username) {
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable() String username) {
		userService.delete(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable() String username, @RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(userService.update(username, userDto), HttpStatus.OK);
	}
}
