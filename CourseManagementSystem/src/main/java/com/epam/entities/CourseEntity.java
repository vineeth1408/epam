package com.epam.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_COURSE")
public class CourseEntity {
	
	@Id
	@Column(name= "COURSE_ID", unique=true, columnDefinition="VARCHAR(40)")
	private String courseId;
	
	@Column(name="COURSE_NAME")
	private String courseName;
	
	@Column(name="COURSE_DESCRIPTION")
	private String courseDescription;
	
	@OneToMany(mappedBy ="course", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY) // all operations
	private List<AssignmentEntity> assignments; 
	
	@ManyToOne
	private InstructorEntity instructor;
	
	public void setInstructor(InstructorEntity instructor) {
		this.instructor = instructor;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public InstructorEntity getInstructor() {
		return instructor;
	}
	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public List<AssignmentEntity> getAssignments() {
		return assignments;
	}
	
	public void setAssignments(List<AssignmentEntity> assignments) {
		assignments.forEach(assign -> assign.setCourse(this));
		this.assignments = assignments;
	}
}
