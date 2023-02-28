package com.epam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.dao.AuthGroupRepo;
import com.epam.dao.InstructorRepo;
import com.epam.dto.InstructorDto;
import com.epam.entities.AuthGroup;
import com.epam.entities.InstructorEntity;
import com.epam.exceptions.InstructorExistException;
import com.epam.mappers.DtoToEntityConverter;
import com.epam.mappers.EntityToDtoConverter;

@Service
public class RegistrationService {

	@Autowired
	private InstructorRepo instructorRepo;
	
	@Autowired
	private AuthGroupRepo authGroupRepo;

	DtoToEntityConverter dtoToEntityConverter = new DtoToEntityConverter();

	EntityToDtoConverter entityToDtoConverter = new EntityToDtoConverter();
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public InstructorDto register(InstructorDto instructorDto) {
		
		InstructorEntity instructorEntity = dtoToEntityConverter.getInstructorEntity(instructorDto);
		
		if( !instructorRepo.existsById(instructorDto.getUsername()) ) {
			instructorEntity.setPassword(encoder.encode(instructorDto.getPassword()));
			instructorRepo.save(instructorEntity);
			
			AuthGroup authGroup = new AuthGroup();
			authGroup.setUsername("arjun");
			authGroup.setAuthenticGroup("ADMIN");
			authGroupRepo.save(authGroup);
		}
		else{
			throw new InstructorExistException("User Already Exist");
		}
		return instructorDto;
	}
}