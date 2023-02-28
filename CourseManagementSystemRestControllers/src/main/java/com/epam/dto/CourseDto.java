package com.epam.dto;

import javax.validation.constraints.NotBlank;

public class CourseDto {
	
	@NotBlank(message="please enter course Id")
	private String courseId;
	
	@NotBlank(message="Course name")
	private String courseName;

	@NotBlank(message="please enter course description")
	private String courseDescription;
		

	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	@Override
	public String toString() {
		return "CourseDto [courseId=" + courseId + ", courseName=" + courseName + ", courseDiscription="
				+ courseDescription + "]";
	}
}
