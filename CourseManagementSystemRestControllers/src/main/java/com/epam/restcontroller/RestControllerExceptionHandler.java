package com.epam.restcontroller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.dto.ExceptionResponse;
import com.epam.exceptions.CourseExistException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.exceptions.InstructorExistException;
import com.epam.exceptions.InstructorNotFoundException;
import com.epam.exceptions.UnAuthorizedException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
	@ExceptionHandler(CourseExistException.class)
	public ResponseEntity<ExceptionResponse> handleCourseExistException(CourseExistException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ExceptionResponse> handleUnAuthorizedException(UnAuthorizedException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InstructorExistException.class)
	public ResponseEntity<ExceptionResponse> handleInstructorExistException(InstructorExistException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCourseNotFoundException(CourseNotFoundException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InstructorNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleInstructorNotFoundException(InstructorNotFoundException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}

