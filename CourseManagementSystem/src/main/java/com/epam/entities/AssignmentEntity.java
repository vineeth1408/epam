package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ASSIGNMENT")
public class AssignmentEntity {

	@Id
	@Column(name = "AssignmentId", unique = true, columnDefinition = "VARCHAR(30)")
	private String assignmentId;

	@Column(name = "name")
	private String assignmentName;

	@Column(name = "noOfQuestions")
	private Long numberOfQuestions;

	@Column(name = "assignmentDeadLine")
	private Long deadlineInHours;

	@Column(name = "marks")
	private Long marks;

	@ManyToOne
	private CourseEntity course;

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	
	public Long getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(Long numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public Long getDeadlineInHours() {
		return deadlineInHours;
	}

	public void setDeadlineInHours(Long deadlineInHours) {
		this.deadlineInHours = deadlineInHours;
	}

	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}
}