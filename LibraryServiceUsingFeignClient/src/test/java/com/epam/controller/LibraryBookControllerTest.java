package com.epam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.dto.BookDto;
import com.epam.feignclient.BookClient;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryBookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	BookClient bookClient;
	
	List<BookDto> mockBooks;
	BookDto bookDto;
	@BeforeEach
	public void setUp() {
		bookDto = new BookDto();
		
		bookDto.setBookId("b1");
		bookDto.setAuthor("vineeth");
		bookDto.setBookName("java 8");
		bookDto.setPublisher("SR PUBLISHERS");
		mockBooks = new ArrayList<BookDto>();
		mockBooks.add(bookDto);
	}
	
	@AfterEach
	public void tearDown() {
		
	}
	@Test
	@Disabled
	void getAllBooksTest() throws Exception {
		when(bookClient.getAllBooks()).thenReturn(mockBooks);
		MvcResult mvcResult = mockMvc.perform(get("libraryclient/books")).andExpect(status().isOk()).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}

}
