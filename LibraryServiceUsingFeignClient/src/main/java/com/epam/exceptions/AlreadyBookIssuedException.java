package com.epam.exceptions;

public class AlreadyBookIssuedException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AlreadyBookIssuedException(String msg) {
		super(msg);
	}
	
}
