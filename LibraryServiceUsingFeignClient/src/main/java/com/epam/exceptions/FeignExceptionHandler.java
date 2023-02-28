package com.epam.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.dto.ExceptionResponse;

import feign.FeignException;

@RestControllerAdvice
public class FeignExceptionHandler {
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Map<String,String>> handleFeignException(FeignException e) {
		Map<String, String> response = new HashMap<>();
		
		response.put("timestamp", new Date().toString());
		response.put("error", e.getMessage());
		response.put("status",String.valueOf(e.status()));
	
		return new ResponseEntity<>(response, HttpStatus.valueOf(e.status()));
	}
	
	@ExceptionHandler(AlreadyBookIssuedException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundExistException(AlreadyBookIssuedException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotIssuedException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundExistException(BookNotIssuedException exception,
			WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimeStamp(new Date().toString());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setErrors(exception.getMessage());
		exceptionResponse.setPath(request.getDescription(true));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
