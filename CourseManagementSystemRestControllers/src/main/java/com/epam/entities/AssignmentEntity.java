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
	private int numberOfQuestions;

	@Column(name = "assignmentDeadLine")
	private int deadlineInHours;

	@Column(name = "marks")
	private int marks;

	@ManyToOne
	private CourseEntity course;

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

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getDeadlineInHours() {
		return deadlineInHours;
	}

	public void setDeadlineInHours(int deadlineInHours) {
		this.deadlineInHours = deadlineInHours;
	}
}