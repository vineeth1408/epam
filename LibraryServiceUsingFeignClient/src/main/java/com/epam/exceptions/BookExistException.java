package com.epam.exceptions;

public class BookExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BookExistException(String msg) {
		super(msg);
	}

}
