package com.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.CourseRepo;
import com.epam.dao.InstructorRepo;
import com.epam.dto.CourseDto;
import com.epam.entities.CourseEntity;
import com.epam.entities.InstructorEntity;
import com.epam.exceptions.CourseExistException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.exceptions.InstructorNotFoundException;
import com.epam.exceptions.UnAuthorizedException;
import com.epam.mappers.DtoToEntityConverter;
import com.epam.mappers.EntityToDtoConverter;

@Service
public class CourseService {

	@Autowired
	public CourseRepo courseRepo;
	
	@Autowired
	private InstructorRepo instructorRepo;

	DtoToEntityConverter dtoToEntityConverter = new DtoToEntityConverter();
	EntityToDtoConverter entityToDtoConverter = new EntityToDtoConverter();

	public CourseDto add(String username, CourseDto courseDto)  throws CourseExistException {

		if (courseRepo.existsById(courseDto.getCourseId())) {
			throw new CourseExistException("Course Already Exist");
		}
		InstructorEntity instructor = new InstructorEntity();
		instructor.setUsername(username);
		CourseEntity courseEntity = dtoToEntityConverter.getCourseEntity(courseDto);
		courseEntity.setInstructor(instructor);
		return entityToDtoConverter.getCourseDto(courseRepo.save(courseEntity));
	}

	public List<CourseDto> viewAllCourses() {
		return entityToDtoConverter.getListOfCourseDtos(courseRepo.findAll());
	}

	public List<CourseDto> viewCoursesByInstructor(String username) {
		return entityToDtoConverter.getListOfCourseDtos((instructorRepo.findById(username)
				.orElseThrow(() -> new InstructorNotFoundException("Instructor not found"))).getCourses());
	}

	public void delete(String username, String courseId) {

		CourseEntity courseEntity = courseRepo.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not Found"));
		if (!courseEntity.getInstructor().getUsername().equals(username)) {
			throw new UnAuthorizedException("Un-authorized Access");
		}
		courseRepo.delete(courseEntity);
	}

	public CourseDto update(CourseDto courseDto) {

		if (!courseRepo.existsById(courseDto.getCourseId())) {
			throw new CourseNotFoundException("Course Not Found");
		}
		return entityToDtoConverter.getCourseDto(courseRepo.save(dtoToEntityConverter.getCourseEntity(courseDto)));
	}

	public CourseDto findCourseById(String courseId) {
		return entityToDtoConverter.getCourseDto(
				courseRepo.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found")));
	}
}