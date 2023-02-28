package com.epam.utility;

public class Principle {
	
	private static Principle principle = null;
	private String username;

	
	public static Principle getInstance() {
		if(principle==null) {
			principle = new Principle();
		}
		return principle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
