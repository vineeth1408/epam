package com.epam.exceptions;

public class BookNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BookNotFoundException(String msg) {
		super(msg);
	}
}
