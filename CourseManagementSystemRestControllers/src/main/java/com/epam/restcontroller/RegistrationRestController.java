package com.epam.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.InstructorDto;
import com.epam.services.RegistrationService;

@RestController
@RequestMapping("/register")
public class RegistrationRestController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<InstructorDto> register(@RequestBody @Valid InstructorDto instructorDto) {
		return new ResponseEntity<>(registrationService.register(instructorDto), HttpStatus.CREATED);
	}
}