package com.epam.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entity.UserEntity;
import com.epam.exceptions.UserExistException;
import com.epam.exceptions.UserNotFoundException;

@Service
public class UserService {

	ModelMapper modelMapper = new ModelMapper();
	String message = "User Not Found";

	@Autowired
	private UserRepository userRepository;

	public UserDto add(UserDto userDto) {

		if ( userRepository.existsById(userDto.getUsername() )) {
			throw new UserExistException("User Already Exist");
		}
		return convertToDto(userRepository.save(convertToEntity(userDto)));
	}

	public UserDto getUserByUsername(String username) {
		return convertToDto(userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(message)));
	}

	public void delete(String username) {
		userRepository.delete(userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(message)));
	}

	public UserDto update(String username, UserDto userDto) {
		if ( !userRepository.existsById(username)) {
			throw new UserNotFoundException(message);
		}
		return convertToDto(userRepository.save(convertToEntity(userDto)));
	}

	public List<UserDto> viewAllUsers() {
		Type listType = new TypeToken<List<UserDto>>() {}.getType();
		return modelMapper.map(userRepository.findAll(), listType);
	}

	public UserEntity convertToEntity(UserDto userDto) {
		return modelMapper.map(userDto, UserEntity.class);
	}

	public UserDto convertToDto(UserEntity userEntity) {
		return modelMapper.map(userEntity, UserDto.class);
	}
}
