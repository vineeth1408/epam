package com.epam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.InstructorDto;
import com.epam.services.RegistrationService;

@Controller
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	String redirectPage = "redirect:/registration";

	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("instructorDto", new InstructorDto());
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@PostMapping("/registrationForm")
	public ModelAndView registrationFormView(@ModelAttribute("instructorDto") @Valid InstructorDto instructorDto,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView();

		if (!result.hasFieldErrors()) {

			registrationService.register(instructorDto);
			modelAndView.setViewName("redirect:/login");
		}

		else {
			modelAndView.setViewName(redirectPage);
		}
		return modelAndView;
	}
}
