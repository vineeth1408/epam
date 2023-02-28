package com.epam.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.AssignmentDto;
import com.epam.services.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentRestController {
	
	@Autowired
	AssignmentService assignmentService;
	
	@GetMapping()
	public ResponseEntity<List<AssignmentDto>> assignmentsList(){
		return  ResponseEntity.ok().body(assignmentService.viewAllAssignments());
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<AssignmentDto>> assignmentsListForAcourse(@PathVariable("courseId") String courseId){
		return  ResponseEntity.ok().body(assignmentService.viewAssignmentsForAcourse(courseId));
	}
	
	@PostMapping("/{courseId}")
	public ResponseEntity<AssignmentDto> insertAssignment(@RequestBody @Valid AssignmentDto assignmentDto, @PathVariable("courseId") String courseId){
		return new ResponseEntity<>(assignmentService.add(assignmentDto, courseId),HttpStatus.CREATED);
	}
	
	@GetMapping("/{assignmentid}")
	public ResponseEntity<AssignmentDto> viewAssignment(@PathVariable("assignmentid") String assignmentId){
		return new ResponseEntity<>(assignmentService.findAssignmentById(assignmentId) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/{assignmentid}")
	public ResponseEntity<String> deleteAssignment(@PathVariable("assignmentid") String assignmentId){
		assignmentService.delete(assignmentId);
		return new ResponseEntity<>("assignment Deleted successfully",HttpStatus.OK);
	}
}