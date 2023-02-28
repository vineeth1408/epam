package com.epam.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dao.AuthGroupRepo;
import com.epam.dao.InstructorRepo;
import com.epam.dto.InstructorDto;
import com.epam.entities.InstructorEntity;
import com.epam.exceptions.InstructorExistException;
import com.epam.mappers.DtoToEntityConverter;
import com.epam.mappers.EntityToDtoConverter;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

	@InjectMocks
	RegistrationService registrationService;
	
	@Mock
	AuthGroupRepo authGroupRepo;

	@Mock
	InstructorRepo instructorRepo;
	
	@InjectMocks
	DtoToEntityConverter dtoToEntityConverter;
	
	@InjectMocks
	EntityToDtoConverter entityToDtoConverter;
	
	InstructorEntity instructorEntity;
	InstructorDto instructorDto;
	
	@BeforeEach
	public void setUp() {
		instructorEntity = new InstructorEntity();
		instructorEntity.setInstructorName("pavan");
		instructorEntity.setUsername("p10");
		instructorEntity.setPassword("password");
		instructorDto = entityToDtoConverter.getInstructorDto(instructorEntity);
	}

	@AfterEach
	public void tearDown() {
		instructorEntity = null;
		instructorDto = null;
	}

	@Test
	void forNewInstructorRegistrationTest() {
		
		when(instructorRepo.existsById(any())).thenReturn(false);
		when(instructorRepo.save(any())).thenReturn(instructorEntity);
		instructorDto = registrationService.register(instructorDto);
		assertEquals(instructorEntity.getUsername(), instructorDto.getUsername());
	}
	
	@Test
	void testForExistingInstructorTest() {
		
		when(instructorRepo.existsById(any())).thenReturn(true);
		
		InstructorExistException E = assertThrows(InstructorExistException.class, () -> registrationService.register(instructorDto));
		assertEquals("User Already Exist", E.getMessage());
		verify(instructorRepo, times(0)).save(any());
	}
}