package com.epam.controller;

import java.util.List;

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

import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.exceptions.AlreadyBookIssuedException;
import com.epam.exceptions.BookNotIssuedException;
import com.epam.feignclient.UserClient;
import com.epam.service.LibraryService;

@RestController
@RequestMapping("libraryclient/user")
public class LibraryUserController {

	@Autowired
	UserClient userClient;
	
	@Autowired
	LibraryService libraryService;
	
	
	@GetMapping()
	public List<UserDto> getAllUsers() {
		return userClient.getAllUsers();
	}

	@PostMapping()
	public UserDto addNewUer(@RequestBody UserDto userDto) {
		return userClient.addUser(userDto);
	}

	@GetMapping("/{username}")
	public UserDto getUer(@PathVariable("username") String username) {
		return userClient.getUserByUsername(username);
	}

	@PutMapping("/{username}")
	public UserDto updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
		return userClient.updateUser(username, userDto);
	}

	@DeleteMapping("/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		userClient.deleteUser(username);
		return "Deleted Successfully";
	}
	
	@PostMapping("/{username}/books/{bookId}")
	public ResponseEntity<String> issueBookToUser(@PathVariable String username, @PathVariable String bookId)
			throws AlreadyBookIssuedException {
		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setUsername(username);
		libraryDto.setBookId(bookId);
		libraryService.addAssociation(libraryDto);
		return new ResponseEntity<>("Book with BookId: "+bookId+" is issued to User: "+username, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{username}/books/{bookId}")
	public ResponseEntity<String> releaseBookFromUser(@PathVariable("username") String username,
			@PathVariable("bookId") String bookId) throws BookNotIssuedException {

		libraryService.delete(username, bookId);
		return new ResponseEntity<>("Book with BookId: "+bookId+" is released from User: "+username, HttpStatus.NO_CONTENT);
	}
}
