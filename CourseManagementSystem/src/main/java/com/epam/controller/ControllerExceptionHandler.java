package com.epam.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.exceptions.AssignmentExistException;
import com.epam.exceptions.AssignmentNotFoundException;
import com.epam.exceptions.CourseExistException;
import com.epam.exceptions.CourseNotFoundException;
import com.epam.exceptions.InstructorExistException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	public static final String MESSAGE_FOR_VIEW = "message";
	public static final String ERROR_PAGE1 = "courseError";
	public static final String ERROR_PAGE2 = "assignmentError";
	
	@ExceptionHandler(value= CourseNotFoundException.class)
	public ModelAndView handleCourseNotFoundException(CourseNotFoundException exception) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(MESSAGE_FOR_VIEW,exception.getMessage());
		modelAndView.setViewName(ERROR_PAGE1);
		
		return modelAndView;
	}
	
	@ExceptionHandler(value= AssignmentNotFoundException.class)
	public ModelAndView handleAssignmentNotFoundException(AssignmentNotFoundException exception) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(MESSAGE_FOR_VIEW, exception.getMessage());
		modelAndView.setViewName(ERROR_PAGE2);
		
		return modelAndView;
	}
	
	@ExceptionHandler(value= AssignmentExistException.class)
	public ModelAndView handleAssignmentExistException(AssignmentExistException exception) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(MESSAGE_FOR_VIEW, exception.getMessage());
		modelAndView.setViewName(ERROR_PAGE2);
		
		return modelAndView;
	}
	
	@ExceptionHandler(value= InstructorExistException.class)
	public ModelAndView handleInstructorExistException(InstructorExistException exception) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(MESSAGE_FOR_VIEW,exception.getMessage());
		modelAndView.setViewName("error");
	
		return modelAndView;
	}
	
	
	@ExceptionHandler(value= CourseExistException.class)
	public ModelAndView handleCourseExistException(CourseExistException exception) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(MESSAGE_FOR_VIEW,exception.getMessage());
		modelAndView.setViewName(ERROR_PAGE1);
	
		return modelAndView;
	}
}
