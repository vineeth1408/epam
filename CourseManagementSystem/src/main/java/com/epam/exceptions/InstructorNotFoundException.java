package com.epam.exceptions;

public class InstructorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InstructorNotFoundException(String message) {
		super(message);
	}

}
