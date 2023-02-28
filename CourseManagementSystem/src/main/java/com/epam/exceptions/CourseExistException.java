package com.epam.exceptions;

public class CourseExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CourseExistException(String message) {
		super(message);
	}

}
