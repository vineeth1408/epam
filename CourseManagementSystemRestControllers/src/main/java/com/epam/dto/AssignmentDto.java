package com.epam.dto;

public class AssignmentDto {

	private String assignmentName;

	private String assignmentId;

	private int numberOfQuestions;

	private int deadlineInHours;

	private int marks;

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

	public int getDeadlineInHours() {
		return deadlineInHours;
	}

	public void setDeadlineInHours(int deadlineInHours) {
		this.deadlineInHours = deadlineInHours;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "AssignmentDto [assignmentName=" + assignmentName + ", assignmentId=" + assignmentId
				+ ", numberOfQuestions=" + numberOfQuestions + ", deadlineInHours=" + deadlineInHours + ", marks="
				+ marks + "]";
	}
}
