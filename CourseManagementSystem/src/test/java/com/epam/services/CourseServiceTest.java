package com.epam.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	@Mock
	CourseRepo courseRepo;

	@InjectMocks
	CourseService courseService;

	@InjectMocks
	DtoToEntityConverter dtoToEntityConverter;

	@InjectMocks
	EntityToDtoConverter entityToDtoConverter;

	@Mock
	InstructorRepo instructorRepo;

	Optional<InstructorEntity> optionalInstructor;
	Optional<CourseEntity> optionalCourse;
	InstructorEntity instructorEntity;
	CourseEntity courseEntity;
	CourseDto courseDto;
	List<CourseEntity> mockcourses;

	@BeforeEach
	public void setup() {

		courseDto = new CourseDto();
		courseDto.setCourseId("c1");
		courseDto.setCourseName("java");
		courseDto.setCourseDescription("oops concepts");

		courseEntity = dtoToEntityConverter.getCourseEntity(courseDto);
		mockcourses = Arrays.asList(courseEntity);

		optionalInstructor = Optional.ofNullable(new InstructorEntity());
		optionalInstructor.get().setUsername("p1");
		optionalInstructor.get().setCourses(mockcourses);
		courseEntity.setInstructor(optionalInstructor.get());

		optionalCourse = Optional.ofNullable(new CourseEntity());
		optionalCourse.get().setCourseId("c1");
		optionalCourse.get().setCourseDescription("java 8");
		optionalCourse.get().setCourseName("java");

	}

	@AfterEach
	public void tearDown() {

	}

	@Test
	void addingNewCourseTest() {

		when(courseRepo.existsById(any())).thenReturn(false);
		when(courseRepo.save(any())).thenReturn(courseEntity);
		courseDto = courseService.add("p1", courseDto);
		assertEquals(courseEntity.getCourseId(), courseDto.getCourseId());
		verify(courseRepo, times(1)).save(any());
	}

	@Test
	void addingExistingCourseTest() {

		when(courseRepo.existsById(any())).thenReturn(true);
		assertThrows(CourseExistException.class, () -> courseService.add("vineeth", courseDto));
		verify(courseRepo, times(0)).save(any());
	}

	@Test
	void viewAllCoursesTest() {

		courseEntity = new CourseEntity();
		List<CourseEntity> listOfCourses = Arrays.asList(courseEntity);
		when(courseRepo.findAll()).thenReturn(listOfCourses);
		assertEquals(1, courseService.viewAllCourses().size());
	}

	@Test
	void getAllCoursesBasedOnInstructorTest() {

		when(instructorRepo.findById(any())).thenReturn(optionalInstructor);
		assertEquals(1, courseService.viewCoursesByInstructor("p1").size());
		verify(instructorRepo, times(1)).findById("p1");
	}

	@Test
	void instructorNotFoundExceptionTest() {
		when(instructorRepo.findById(any())).thenReturn(Optional.empty());
		InstructorNotFoundException e = assertThrows(InstructorNotFoundException.class,
				() -> courseService.viewCoursesByInstructor("vineeth"));
		assertEquals("Instructor not found", e.getMessage());
	}

	@Test
	void courseNotFoundExceptionTest() {

		when(courseRepo.findById(any())).thenReturn(Optional.empty());
		CourseNotFoundException e = assertThrows(CourseNotFoundException.class, () -> courseService.delete("p1", "c1"));
		assertEquals("Course not Found", e.getMessage());
	}

	@Test
	void testDeletingCourseWithValidCourseId() {

		InstructorEntity instructorEntity = new InstructorEntity();
		instructorEntity.setUsername("p1");

		Optional<CourseEntity> course = Optional.ofNullable(new CourseEntity());
		course.get().setCourseId("c1");
		course.get().setInstructor(instructorEntity);

		when(courseRepo.findById(any())).thenReturn(course);
		courseService.delete("p1", "c1");
		verify(courseRepo, times(1)).delete(any());
	}

	@Test

	void unAuthorizedExceptionTest() {

		InstructorEntity instructorEntity = new InstructorEntity();
		instructorEntity.setUsername("p1");

		Optional<CourseEntity> course = Optional.ofNullable(new CourseEntity());
		course.get().setCourseId("c1");
		course.get().setInstructor(instructorEntity);

		when(courseRepo.findById(any())).thenReturn(course);

		UnAuthorizedException e = assertThrows(UnAuthorizedException.class, () -> courseService.delete("p3", "c1"));
		assertEquals("Un-authorized Access", e.getMessage());
	}

	@Test
	void findCourseWithvalidIdTest() {

		Optional<CourseEntity> courseEntity = Optional.ofNullable(new CourseEntity());
		courseEntity.get().setCourseId("c1");

		when(courseRepo.findById("c1")).thenReturn(courseEntity);

		CourseDto courseDto = courseService.findCourseById("c1");
		assertEquals(courseDto.getCourseId(), courseEntity.get().getCourseId());
		verify(courseRepo, times(1)).findById("c1");
	}

	@Test
	void findCourseWithInvalidIdTest() {

		when(courseRepo.findById(any())).thenReturn(Optional.empty());

		assertThrows(CourseNotFoundException.class, () -> {
			courseService.findCourseById("c1");
		});

		verify(courseRepo, times(1)).findById(any());
	}

	@Test
	void updateCourseTest() {

		when(courseRepo.existsById(any())).thenReturn(true);
		when(courseRepo.save(any())).thenReturn(courseEntity);
		courseDto = courseService.update(courseDto);
		assertEquals(courseEntity.getCourseId(), courseDto.getCourseId());
		verify(courseRepo, times(1)).save(any());
	}

	@Test
	void updateCourseWithInvalidCourseIdTest() {
		
		when(courseRepo.existsById(any())).thenReturn(false);

		CourseNotFoundException e = assertThrows(CourseNotFoundException.class, () -> courseService.update(courseDto));
		assertEquals("Course Not Found", e.getMessage());

	}
}