package com.epam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "TBL_INSTRUCTOR")
public class InstructorEntity {

	@Column(name = "INSTRUCTOR_NAME")
	private String instructorName;

	@Id
	@Column(name = "USERNAME", unique = true, columnDefinition = "VARCHAR(40)")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	private boolean enabled=true;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(mappedBy = "instructor", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CourseEntity> courses = new ArrayList<>();

	public List<CourseEntity> getCourses() {
		return courses;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
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

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
}