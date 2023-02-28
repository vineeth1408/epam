package com.epam.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.epam.dto.AssignmentDto;
import com.epam.dto.CourseDto;
import com.epam.dto.InstructorDto;
import com.epam.entities.AssignmentEntity;
import com.epam.entities.CourseEntity;
import com.epam.entities.InstructorEntity;

@Component
public class DtoToEntityConverter {

	ModelMapper mapper = new ModelMapper();

	public InstructorEntity getInstructorEntity(InstructorDto instructorDto) {
		return mapper.map(instructorDto, InstructorEntity.class);
	}

	public CourseEntity getCourseEntity(CourseDto courseDto) {
		return mapper.map(courseDto, CourseEntity.class);
	}

	public AssignmentEntity getAssignmentEntity(AssignmentDto assignmentDto) {
		return mapper.map(assignmentDto, AssignmentEntity.class);
	}
}
