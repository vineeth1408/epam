package com.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.AssignmentRepo;
import com.epam.dao.CourseRepo;
import com.epam.dto.AssignmentDto;
import com.epam.entities.AssignmentEntity;
import com.epam.entities.CourseEntity;
import com.epam.exceptions.AssignmentExistException;
import com.epam.exceptions.AssignmentNotFoundException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.mappers.DtoToEntityConverter;
import com.epam.mappers.EntityToDtoConverter;

@Service
public class AssignmentService {

	@Autowired
	public AssignmentRepo assignmentRepo;

	@Autowired
	public CourseRepo courseRepo;

	DtoToEntityConverter dtoToEntityConverter = new DtoToEntityConverter();

	EntityToDtoConverter entityToDtoConverter = new EntityToDtoConverter();

	public AssignmentDto add(AssignmentDto assignmentDto, String courseId) {

		if (assignmentRepo.existsById(assignmentDto.getAssignmentId())) {
			throw new AssignmentExistException("Assignment Already Exist");
		}
		CourseEntity course = new CourseEntity();
		course.setCourseId(courseId);
		AssignmentEntity assignmentEntity = dtoToEntityConverter.getAssignmentEntity(assignmentDto);
		assignmentEntity.setCourse(course);

		return entityToDtoConverter.getAssignmentDto(assignmentRepo.save(assignmentEntity));
	}

	public List<AssignmentDto> viewAllAssignments() {
		return entityToDtoConverter.getListOfAssignmentDtos(assignmentRepo.findAll());
	}

	public List<AssignmentDto> viewAssignmentsForAcourse(String courseId) {
		return entityToDtoConverter.getListOfAssignmentDtos((courseRepo.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course Not Found")).getAssignments()));
	}

	public void delete(String assignmentId) {
		if (!assignmentRepo.existsById(assignmentId)) {
			throw new AssignmentNotFoundException("Assignment not found");
		}
		assignmentRepo.delete(assignmentRepo.getById(assignmentId));
	}

	public AssignmentDto findAssignmentById(String assignmentId) {
		return entityToDtoConverter.getAssignmentDto(assignmentRepo.findById(assignmentId)
				.orElseThrow(() -> new AssignmentNotFoundException("Assignment not found")));
	}
	
	public AssignmentDto update(AssignmentDto assignment) {
		if (!assignmentRepo.existsById(assignment.getAssignmentId())) {
			throw new AssignmentNotFoundException("assignment not found");
		}
		return entityToDtoConverter
				.getAssignmentDto(assignmentRepo.save(dtoToEntityConverter.getAssignmentEntity(assignment)));
	}
}