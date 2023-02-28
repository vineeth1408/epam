package com.epam.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.epam.dto.BookDto;
import com.epam.service.BooksService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/books")
public class BookRestController {

	@Autowired
	private BooksService bookService;
	@Autowired
	Environment env;
	
	@GetMapping
	@Operation(description = "It fetches all the books from the books catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })

	public ResponseEntity<List<BookDto>> getAllBooks() {
		System.out.println(env.getProperty("local.server.port"));
		return ResponseEntity.ok().body(bookService.viewAllBooks());
	}

	@PostMapping
	@Operation(description = "It adds new book into the books catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Successfull") })

	public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
		return new ResponseEntity<>(bookService.add(bookDto), HttpStatus.CREATED);
	}

	@GetMapping("/{bookId}")
	@Operation(description = "It fetches the book from the books catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })

	public ResponseEntity<BookDto> fetchBook(@PathVariable("bookId") String bookId) {
		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
	}

	@DeleteMapping("/{bookId}")
	@Operation(description = "It deletes the book from the books catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable() String bookId) {
		bookService.delete(bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{bookId}")
	@Operation(description = "It updates the book from the books catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	
	public ResponseEntity<BookDto> updateUser(@PathVariable() String bookId, @RequestBody @Valid BookDto bookDto) {
		return new ResponseEntity<>(bookService.update(bookId, bookDto), HttpStatus.OK);
	}
}
