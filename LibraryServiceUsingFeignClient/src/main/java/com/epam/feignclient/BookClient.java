package com.epam.feignclient;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.epam.dto.BookDto;

@FeignClient(name="BOOKS-APP")
@LoadBalancerClient(name="BOOKS-APP")
public interface BookClient {
	
	
	@GetMapping("/books")
	public List<BookDto> getAllBooks();
	
	@PostMapping("/bokks")
	public BookDto addBook(BookDto bookDto); 
	
	@GetMapping("books/{bookId}")
	public BookDto getBookByBookId(@PathVariable String bookId);
	
	@PutMapping("books/{bookId}")
	public BookDto updateBook(@PathVariable String bookId,BookDto bookDto);
	
	@DeleteMapping("books/{bookId}")
	public void deleteBook(@PathVariable String bookId);
}
