package com.epam.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.utility.Principle;

@Controller
public class InstructorDashboardController {

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Principle principle = Principle.getInstance();
		principle.setUsername(username);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}
