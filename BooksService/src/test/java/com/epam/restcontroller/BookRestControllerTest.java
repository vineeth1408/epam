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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.BookDto;
import com.epam.entity.BookEntity;
import com.epam.exceptions.BookExistException;
import com.epam.exceptions.BookNotFoundException;
import com.epam.service.BooksService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerTest {
	
	@MockBean
	BooksService booksService;
	
	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
	
	BookDto bookDto;
	BookEntity bookEntity;
	List<BookDto> mockbooks;
	
	@BeforeEach
	public void setUp() {
		bookDto = new BookDto();
		bookDto.setBookId("b1");
		bookDto.setAuthor("vineeth");
		bookDto.setBookName("java 8");
		bookDto.setPublisher("SR PUBLISHERS");
		
		bookEntity = new BookEntity();
		bookEntity.setBookId("b1");
		bookEntity.setBookName("java 8");
		bookEntity.setPublisher("SR PUBLISHERS");
		bookEntity.setAuthor("vineeth");
		
		mockbooks = new ArrayList<BookDto>();
		mockbooks.add(bookDto);
		
	}
	
	@Test
	void viewAllBooksTest() throws Exception {
		when(booksService.viewAllBooks()).thenReturn(mockbooks);
		
		MvcResult mvcResult = mockMvc.perform(get("/books")).andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void addNewBookTest() throws Exception {
		when(booksService.add(any())).thenReturn(bookDto);
		MvcResult mvcResult = mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(bookDto))).andReturn();
		assertEquals(201, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void addExistingBookTest() throws Exception {
		when(booksService.add(any())).thenThrow(BookExistException.class);
		MvcResult mvcResult = mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(bookDto)))
				.andExpect(status().isBadRequest())
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void fetchBookTest() throws Exception {
		when(booksService.getBookById(any())).thenReturn(bookDto);
		
		MvcResult mvcResult = mockMvc.perform(get("/books/p1")).andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void bookNotFoundAtFetchBookTest() throws Exception {
		when(booksService.getBookById(any())).thenThrow(BookNotFoundException.class);
		
		MvcResult mvcResult = mockMvc.perform(get("/books/p1")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void deleteBookTest() throws Exception {
	
		MvcResult mvcResult = mockMvc.perform(delete("/books/b1")).andExpect(status().isNoContent()).andReturn();
		assertEquals(204, mvcResult.getResponse().getStatus());
		verify(booksService, times(1)).delete("b1");
	}
	
	@Test
	void bookNotFoundAtDeleteBookTest() throws Exception {
		doThrow(BookNotFoundException.class).when(booksService).delete(any());		
		MvcResult mvcResult = mockMvc.perform(delete("/books/b1")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void updateBookTest() throws Exception {
		when(booksService.update(any(), any())).thenReturn(bookDto);
		MvcResult mvcResult = mockMvc.perform(put("/books/b1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(bookDto))).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	void bookNotFoundAtUpdateBookTest() throws Exception {
		when(booksService.update(any(), any())).thenThrow(BookNotFoundException.class);
		
		MvcResult mvcResult = mockMvc.perform(put("/books/p1")).andExpect(status().isBadRequest()).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}
	

}
