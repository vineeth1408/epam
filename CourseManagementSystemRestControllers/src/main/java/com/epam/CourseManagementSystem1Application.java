package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Course Management Application", version = "1.0", description ="Course Service"))
public class CourseManagementSystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementSystem1Application.class, args);
	}

}
