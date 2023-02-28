package com.epam.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.BookRepository;
import com.epam.dto.BookDto;
import com.epam.entity.BookEntity;
import com.epam.exceptions.BookExistException;
import com.epam.exceptions.BookNotFoundException;

@Service
public class BooksService {

	ModelMapper modelMapper = new ModelMapper();

	String message = "Book Not Found";

	@Autowired
	private BookRepository bookRepository;

	public BookDto add(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getBookId())) {
			throw new BookExistException("Same Book Already Exist");
		}
		return convertToDto(bookRepository.save(convertToEntity(bookDto)));
	}

	public BookDto getBookById(String bookId) {
		return convertToDto(bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(message)));
	}

	public void delete(String bookId) {
		bookRepository.delete(bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(message)));
	}

	public BookDto update(String bookId, BookDto bookDto) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException(message);
		}
		return convertToDto(bookRepository.save(convertToEntity(bookDto)));
	}

	public List<BookDto> viewAllBooks() {

		Type listType = new TypeToken<List<BookDto>>() {
		}.getType();
		return modelMapper.map(bookRepository.findAll(), listType);
	}

	public BookEntity convertToEntity(BookDto bookDto) {
		return modelMapper.map(bookDto, BookEntity.class);
	}

	public BookDto convertToDto(BookEntity bookEntity) {
		return modelMapper.map(bookEntity, BookDto.class);
	}
}
