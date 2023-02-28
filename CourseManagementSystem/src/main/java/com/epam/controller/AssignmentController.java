package com.epam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.AssignmentDto;
import com.epam.dto.CourseDto;
import com.epam.services.AssignmentService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AssignmentController {
	
	private static final String AssignmentStatus ="assignmentStatus";
	@Autowired
	AssignmentService assignmentService;

	@GetMapping("/assignmentDashboard")
	public ModelAndView assignmentDashboard() {
		List<AssignmentDto> assignments = assignmentService.viewAllAssignments();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("assignments", assignments);
		modelAndView.setViewName("assignmentDashboard");
		return modelAndView;
	}

	@GetMapping("/addAssignment/{courseId}")
	public ModelAndView addAssignmentView(@PathVariable("courseId") String courseId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("courseId",courseId);
		modelAndView.setViewName("addAssignment");
		return modelAndView;
	}

	@PostMapping("/assignmentInputForm/{courseId}")
	public ModelAndView assignmentAdding(AssignmentDto assignmentDto, @PathVariable("courseId") String courseId) {

		ModelAndView modelAndView = new ModelAndView();
		
		assignmentService.add(assignmentDto, courseId);
		modelAndView.addObject("success", "Assignment Added Successfully");
		modelAndView.setViewName(AssignmentStatus);
		return modelAndView;
	}

	@GetMapping("/displayAssignmentsBasedOnCourseId")
	public ModelAndView displayAssignmentsByCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayAssignmentsBasedOnCourseId");
		return modelAndView;
	}
	
	
	@PostMapping("/viewAssignmentByCourseId")
	public ModelAndView viewAssignments(CourseDto courseDto) {
		List<AssignmentDto> assignments = assignmentService.viewAssignmentsForAcourse(courseDto.getCourseId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("assignments", assignments);
		modelAndView.setViewName("viewAssignments");

		return modelAndView;
	}

	@GetMapping("/assignment/delete/{assignmentId}")
	public ModelAndView deleteAssignment(@PathVariable("assignmentId") String assignmentId) {

		ModelAndView modelAndView = new ModelAndView();
		assignmentService.delete(assignmentId);
		modelAndView.addObject("assignmentDeletedStatus", "Assignment Deleted Successfully");
		modelAndView.setViewName(AssignmentStatus);
		return modelAndView;
	}
	
	@GetMapping("/findAssignmentById")
	public String findAssignmentById() {
		return "findAssignmentById";
	}
	
	@PostMapping("/assignmentDashboard/find")
	public ModelAndView findAssignmentById(AssignmentDto assignmentDto) {
		ModelAndView modelAndView = new ModelAndView();
		AssignmentDto assignment = assignmentService.findAssignmentById(assignmentDto.getAssignmentId());
		modelAndView.addObject("assignment", assignment);
		modelAndView.setViewName("viewAssignment");
		return modelAndView;
	}
	
	@GetMapping("/assignment/updateview/{assignmentId}")
	public ModelAndView updateView(@PathVariable String assignmentId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("assignmentDto", assignmentService.findAssignmentById(assignmentId));
		modelAndView.setViewName("updateAssignment");
		return modelAndView;
	}
	
	@PostMapping("/assignment/update/{assignmentId}")
	public ModelAndView update(@ModelAttribute("assignmentDto") @Valid AssignmentDto assignment, BindingResult result, @PathVariable String assignmentId) {
		ModelAndView modelAndView = new ModelAndView();
		if( !result.hasFieldErrors() ) {
			assignmentService.update(assignment);
			modelAndView.addObject("assignmentUpdate", "assignment updated successfully");
			modelAndView.setViewName(AssignmentStatus);
		}
		else {
			modelAndView.setViewName("updateAssignment");
		}
		return modelAndView;
	}
	
}