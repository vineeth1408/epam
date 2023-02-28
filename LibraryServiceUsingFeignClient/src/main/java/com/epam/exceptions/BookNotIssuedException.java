package com.epam.exceptions;

public class BookNotIssuedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotIssuedException(String msg) {
		super(msg);
	}
}
