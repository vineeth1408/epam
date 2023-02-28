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

import com.epam.dao.BookRepository;
import com.epam.dto.BookDto;
import com.epam.entity.BookEntity;
import com.epam.exceptions.BookExistException;
import com.epam.exceptions.BookNotFoundException;



@SpringBootTest
class BooksServiceTest {
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BooksService bookService;
	
	Optional<BookEntity> optionalBook;
	BookDto bookDto;
	BookEntity bookEntity;
	List<BookEntity> mockbooks;
	
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
		
		mockbooks = new ArrayList<BookEntity>();
		mockbooks.add(bookEntity);
		
		optionalBook = Optional.ofNullable(new BookEntity());
		optionalBook.get().setBookId("b1");
		optionalBook.get().setAuthor("vineeth");
		optionalBook.get().setBookName("java 8");
		optionalBook.get().setPublisher("SR PUBLISHERS");
		
		
	}
	
	@AfterEach
	public void tearDown() {
		bookEntity = null;
		mockbooks = null;
		optionalBook = null;
		bookDto = null;
	}
	@Test
	void addNewBookTest() {
		when(bookRepository.existsById(any())).thenReturn(false);
		when(bookRepository.save(any())).thenReturn(bookEntity);
		bookDto = bookService.add(bookDto);
		assertEquals(bookDto.getBookId(), bookEntity.getBookId());
		verify(bookRepository, times(1)).save(any());
	}
	
	@Test
	void addExistingBookTest() {
		when(bookRepository.existsById(any())).thenReturn(true);
		assertThrows(BookExistException.class, () -> bookService.add(bookDto));
	}
	
	@Test
	void getBookTest() {
		when(bookRepository.findById(any())).thenReturn(optionalBook);
		bookDto = bookService.getBookById("b1");
		assertEquals(bookDto.getBookId(), optionalBook.get().getBookId());
		verify(bookRepository, times(1)).findById("b1");
	}
	@Test
	void bookNotFoundExceptionTest() {
		when(bookRepository.findById(any())).thenReturn(Optional.empty());
		assertThrows(BookNotFoundException.class, ()-> bookService.getBookById("b1"));
		verify(bookRepository, times(0)).findById("b2");
	}
	@Test
	void deleteValidBookTest() {
		when(bookRepository.findById(any())).thenReturn(optionalBook);
		bookService.delete("b1");
		verify(bookRepository, times(1)).delete(optionalBook.get());
	}
	@Test
	void bookNotFoundExceptionAtDeleteTest() {
		when(bookRepository.findById(any())).thenReturn(Optional.empty());
		assertThrows(BookNotFoundException.class, ()-> bookService.delete("b1"));
	}
	
	@Test
	void updateBookTest() {
		when(bookRepository.existsById(any())).thenReturn(true);
		when(bookRepository.save(any())).thenReturn(bookEntity);
		bookDto = bookService.update("pavan", bookDto);
		assertEquals(bookDto.getBookId(), bookEntity.getBookId());
		verify(bookRepository, times(1)).save(any());
	}
	
	@Test
	void bookrNotFoundExceptionAtUpdateTest() {
		when(bookRepository.existsById(any())).thenReturn(false);
		assertThrows(BookNotFoundException.class, ()-> bookService.update("b1",bookDto));
	}
	
	@Test
	void viewAllBooksTest() {
		when(bookRepository.findAll()).thenReturn(mockbooks);
		assertEquals(1, bookService.viewAllBooks().size());
	}

}
