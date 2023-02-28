package com.epam.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssignmentDto {

	@NotBlank(message="please enter Assignment name")
	private String assignmentName;
	
	@NotBlank(message="please enter Assignment Id")
	private String assignmentId;
	
	@NotNull(message="please enter no.of Questions")
	private Long numberOfQuestions;
	
	@NotNull(message="please enter deadline")
	private Long deadlineInHours;
	
	@NotNull(message="please enter marks")
	private Long marks;

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

	@Override
	public String toString() {
		return "AssignmentDto [assignmentName=" + assignmentName + ", assignmentId=" + assignmentId
				+ ", numberOfQuestions=" + numberOfQuestions + ", deadlineInHours=" + deadlineInHours + ", marks="
				+ marks + "]";
	}

}
