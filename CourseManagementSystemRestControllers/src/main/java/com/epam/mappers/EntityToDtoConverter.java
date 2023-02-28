package com.epam.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.epam.dto.AssignmentDto;
import com.epam.dto.CourseDto;
import com.epam.dto.InstructorDto;
import com.epam.entities.AssignmentEntity;
import com.epam.entities.CourseEntity;
import com.epam.entities.InstructorEntity;

@Component
public class EntityToDtoConverter {
	ModelMapper mapper = new ModelMapper();
	
	public List<CourseDto> getListOfCourseDtos(List<CourseEntity> courses) {
		return courses.stream().map(course -> mapper.map(course, CourseDto.class)).collect(Collectors.toList());
	}
	
	public List<AssignmentDto> getListOfAssignmentDtos(List<AssignmentEntity> assignments) {
		return assignments.stream().map( assignment -> mapper.map(assignment, AssignmentDto.class)).collect(Collectors.toList());
	}
	public CourseDto getCourseDto(CourseEntity courseEntity) {
		return mapper.map(courseEntity, CourseDto.class);
	}
	
	public AssignmentDto getAssignmentDto(AssignmentEntity assignmentEntity) {
		return mapper.map(assignmentEntity, AssignmentDto.class);
	}
	public InstructorDto getInstructorDto(InstructorEntity instructor) {
		return mapper.map(instructor, InstructorDto.class);
	}
}
