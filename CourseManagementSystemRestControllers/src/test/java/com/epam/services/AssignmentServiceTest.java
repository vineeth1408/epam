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

import com.epam.dao.AssignmentRepo;
import com.epam.dao.CourseRepo;
import com.epam.dto.AssignmentDto;
import com.epam.entities.AssignmentEntity;
import com.epam.entities.CourseEntity;
import com.epam.exceptions.AssignmentExistException;
import com.epam.exceptions.AssignmentNotFoundException;
import com.epam.exceptions.CourseNotFoundException;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

	@Mock
	AssignmentRepo assignmentRepo;

	@Mock
	CourseRepo courseRepo;

	@InjectMocks
	AssignmentService assignmentService;

	Optional<AssignmentEntity> optionalAssignmentEntity;
	Optional<CourseEntity> optionalCourse;
	List<AssignmentEntity> mockAssignments;
	AssignmentDto assignmentDto;
	AssignmentEntity assignmentEntity;
	@BeforeEach
	public void setUp() {

		optionalCourse = Optional.ofNullable(new CourseEntity());
		optionalCourse.get().setCourseId("c1");

		AssignmentEntity a1 = new AssignmentEntity();
		a1.setAssignmentId("a1");
		a1.setAssignmentName("java assignment");

		AssignmentEntity a2 = new AssignmentEntity();
		a2.setAssignmentId("a2");
		a2.setAssignmentName("oops assignment");

		optionalCourse.get().setAssignments(Arrays.asList(a1, a2));

		optionalAssignmentEntity = Optional.ofNullable(new AssignmentEntity());
		optionalAssignmentEntity.get().setAssignmentId("a1");

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId("c1");

		mockAssignments = Arrays.asList(a1, a2);

		assignmentDto = new AssignmentDto();
		assignmentDto.setAssignmentId("a1");
		assignmentEntity = new AssignmentEntity();
		assignmentEntity.setAssignmentId("a1");

	}

	@AfterEach
	public void tearDown() {

	}

	@Test
	void testForAddingTheAssignment() {
		
		when(assignmentRepo.existsById(any())).thenReturn(false);
		when(assignmentRepo.save(any())).thenReturn(assignmentEntity);
		assignmentDto = assignmentService.add(assignmentDto, "c1");
		assertEquals(assignmentDto.getAssignmentId(), assignmentEntity.getAssignmentId());
		verify(assignmentRepo, times(1)).save(any());
	}

	@Test
	void testForNotAddingTheAssignment() {

		when(assignmentRepo.existsById(any())).thenReturn(true);
		assertThrows(AssignmentExistException.class, () -> assignmentService.add(assignmentDto, "c1"));
		verify(assignmentRepo, times(0)).save(any());
	}

	@Test
	void viewAllAssignmentsTest() {

		when(assignmentRepo.findAll()).thenReturn(mockAssignments);

		assertEquals(mockAssignments.size(), assignmentService.viewAllAssignments().size());
	}

	@Test
	void viewingAssignmentsForACourseTest() {

		when(courseRepo.findById(any())).thenReturn(optionalCourse);
		List<AssignmentEntity> assignments = optionalCourse.get().getAssignments();

		List<AssignmentDto> assignmentDtos = assignmentService.viewAssignmentsForAcourse("c1");

		assertEquals(assignmentDtos.size(), assignments.size());
	}
	
	@Test
	void courseNotFoundExceptionTest() {
		when(courseRepo.findById(any())).thenReturn(Optional.empty());
		assertThrows(CourseNotFoundException.class, () -> assignmentService.viewAssignmentsForAcourse("c1") );
		
	}
	

	@Test
	void deleteAssignmentTest() {

		when(assignmentRepo.existsById(any())).thenReturn(true);
		assignmentService.delete(any());
		verify(assignmentRepo, times(1)).delete(any());
	}

	@Test
	void invalidDeleteAssignmentTest() {

		when(assignmentRepo.existsById(any())).thenReturn(false);
		assertThrows(AssignmentNotFoundException.class, () -> assignmentService.delete("c1"));
		verify(assignmentRepo, times(0)).delete(any());
	}

	@Test
	void findAssignmentByValidIdTest() {

		when(assignmentRepo.findById("a1")).thenReturn(optionalAssignmentEntity);

		AssignmentDto assignmentDto = assignmentService.findAssignmentById("a1");
		assertEquals(optionalAssignmentEntity.get().getAssignmentId(), assignmentDto.getAssignmentId());
		verify(assignmentRepo, times(1)).findById("a1");
	}

	@Test
	void findAssignmentByInValidIdTest() {

		when(assignmentRepo.findById(any())).thenReturn(Optional.empty());

		assertThrows(AssignmentNotFoundException.class, () -> {
			assignmentService.findAssignmentById("a5");
		});
		verify(assignmentRepo, times(1)).findById("a5");
	}
	
	@Test
	void updateAssignmentTest() {
		when(assignmentRepo.existsById(any())).thenReturn(true);
		when(assignmentRepo.save(any())).thenReturn(assignmentEntity);
		assignmentDto = assignmentService.update(assignmentDto);
		assertEquals(assignmentEntity.getAssignmentId(), assignmentDto.getAssignmentId());
		verify(assignmentRepo, times(1)).save(any());
	}
	
	@Test
	void assignmentNotFoundExceptionTest() {
		when(assignmentRepo.existsById(any())).thenReturn(false);
		assertThrows(AssignmentNotFoundException.class, () -> {
			assignmentService.update(assignmentDto);
		});
	}
}