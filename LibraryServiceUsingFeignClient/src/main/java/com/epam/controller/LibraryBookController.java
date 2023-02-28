package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.BookDto;
import com.epam.feignclient.BookClient;

@RestController
@RequestMapping("libraryclient/book")
public class LibraryBookController {
	
	@Autowired
	BookClient bookClient;

	
	@GetMapping()
	public List<BookDto> getAllBooks() {
		return bookClient.getAllBooks();
	}
	
	@PostMapping()
	public BookDto addNewBook(@RequestBody BookDto bookDto) {
		return bookClient.addBook(bookDto);
	}
	
	@GetMapping("/{bookId}")
	public BookDto getBook(@PathVariable String bookId) {
		return bookClient.getBookByBookId(bookId);
	}
	
	@PutMapping("/{bookId}")
	public BookDto updateBook(@PathVariable String bookId, @RequestBody BookDto bookDto) {
		return bookClient.updateBook(bookId, bookDto);
	}
	
	@DeleteMapping("/{bookId}")
	public String deleteUser(@PathVariable String bookId) {
		bookClient.deleteBook(bookId);
		return "Deleted Successfully";
	}
}
