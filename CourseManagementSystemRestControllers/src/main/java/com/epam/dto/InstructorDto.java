package com.epam.dto;

import javax.validation.constraints.NotBlank;

public class InstructorDto {
	
	@NotBlank(message="Please enter name")
	private String instructorName;
	
	@NotBlank(message="please enter username")
	private String username;
	
	@NotBlank(message = "Please enter password")
	private String password;

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
