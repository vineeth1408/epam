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

import com.epam.dto.CourseDto;
import com.epam.services.CourseService;
import com.epam.utility.Principle;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CourseController {

	private static final String successview ="success";
	
	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public ModelAndView loadCoursePage() {

		Principle principle = Principle.getInstance();
		List<CourseDto> courses = courseService.viewCoursesByInstructor(principle.getUsername());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("courses", courses);
		modelAndView.setViewName("displayCourses");

		return modelAndView;
	}

	@GetMapping("/addcourse")
	public ModelAndView addCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("courseDto", new CourseDto());
		modelAndView.setViewName("addcourse");
		return modelAndView;
	}

	@PostMapping("/addCourseForm")
	public ModelAndView addCourse(@ModelAttribute("courseDto") @Valid CourseDto courseDto, BindingResult result) {

		Principle principle = Principle.getInstance();

		ModelAndView modelAndView = new ModelAndView();

		if (!result.hasFieldErrors()) {
			courseService.add(principle.getUsername(), courseDto);
			modelAndView.addObject("courseAddingStatus", "Course Added Successfully");
			modelAndView.setViewName(successview);
		} else {
			modelAndView.setViewName("addcourse");
		}
		return modelAndView;
	}

	@GetMapping("/findCourseById")
	public String findCourseById() {
		return "findCourseById";
	}

	@PostMapping("/courses/findCourseById")
	public ModelAndView loadCourseBasedOnId(CourseDto courseDto1) {

		CourseDto courseDto = courseService.findCourseById(courseDto1.getCourseId());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("course", courseDto);
		modelAndView.setViewName("displayCourse");
		return modelAndView;
	}

	@GetMapping("/courses/deletecourse/{courseId}")
	public ModelAndView deleteCourse(@PathVariable("courseId") String courseId) {

		Principle principle = Principle.getInstance();

		ModelAndView modelAndView = new ModelAndView();
		courseService.delete(principle.getUsername(), courseId);
		modelAndView.addObject("courseDeletionStatus", "Course Deleted Succesfully");
		modelAndView.setViewName(successview);
		return modelAndView;
	}

	@GetMapping("/courses/updateview/{courseId}")
	public ModelAndView updateCourse(@PathVariable String courseId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("courseId", courseId);
		modelAndView.addObject("courseDto",courseService.findCourseById(courseId));
		modelAndView.setViewName("updatecourse");
		return modelAndView;
	}

	@PostMapping("/courses/update/{courseId}")
	public ModelAndView update(@ModelAttribute("courseDto") @Valid CourseDto courseDto, BindingResult result, @PathVariable String courseId) {
		ModelAndView modelAndView = new ModelAndView();
		if( !result.hasFieldErrors() ) {
			
			courseService.update(courseDto);
			modelAndView.addObject("courseUpdatingStatus", "Course Updated Successfully");
			modelAndView.setViewName(successview);
		}
		else {
			modelAndView.setViewName("updatecourse");
		}
		return modelAndView;
	}
}
