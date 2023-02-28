package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.LibraryRepository;
import com.epam.dto.LibraryDto;
import com.epam.entity.LibraryEntity;
import com.epam.exceptions.AlreadyBookIssuedException;
import com.epam.exceptions.BookNotIssuedException;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	public void addAssociation(LibraryDto libraryDto) throws AlreadyBookIssuedException {

		if (libraryRepository.findByUsernameAndBookId(libraryDto.getUsername(), libraryDto.getBookId()).isPresent()) {
			throw new AlreadyBookIssuedException(
					"Book with BookId: " + libraryDto.getBookId() + "is issued to User: " + libraryDto.getUsername());
		}
		libraryRepository.save(dtoToEntity(libraryDto));
	}

	public void delete(String username, String bookId) throws BookNotIssuedException {
		libraryRepository.delete(libraryRepository.findByUsernameAndBookId(username, bookId)
				.orElseThrow(() -> new BookNotIssuedException(
						"Book with BookId: " + bookId + "is not issued to the User: " + username)));
	}

	public LibraryEntity dtoToEntity(LibraryDto libraryDto) {
		return new ModelMapper().map(libraryDto, LibraryEntity.class);
	}
}
