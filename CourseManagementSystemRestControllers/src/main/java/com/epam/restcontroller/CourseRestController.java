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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.CourseDto;
import com.epam.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	@Operation(description = "It fetches all the courses from the course catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	public ResponseEntity<List<CourseDto>> courseList() {
		return ResponseEntity.ok().body(courseService.viewAllCourses());
	}
	
	@Operation(description = "It fetches all the courses of the particular instructor")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	@GetMapping("/of/{username}")
	public ResponseEntity<List<CourseDto>> instructorCourseList(@PathVariable("username") String username) {
		return ResponseEntity.ok().body(courseService.viewCoursesByInstructor(username));
	}

	@PostMapping("/{username}")
	@Operation(description = "It adds new course into the course catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "201", description = "Successfull") })
	public ResponseEntity<CourseDto> insertCourse(@RequestBody @Valid CourseDto courseDto,
			@PathVariable("username") String username) {
		return new ResponseEntity<>(courseService.add(username, courseDto), HttpStatus.CREATED);
	}

	@GetMapping("/{courseid}")
	@Operation(description = "It fetches a course from the course catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	public ResponseEntity<CourseDto> fecthCourse(@PathVariable("courseid") String courseId) {
		return new ResponseEntity<>(courseService.findCourseById(courseId), HttpStatus.OK);
	}

	@DeleteMapping("/{courseId}")
	@Operation(description = "It delete's a course from the course catalogue")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	public ResponseEntity<String> deleteCourse(@PathVariable("courseId") String courseId, @RequestParam String username) {
		courseService.delete(username, courseId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@PutMapping()
	@Operation(description = "It updates the course")
	@ApiResponses({ @ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "200", description = "Successfull") })
	public ResponseEntity<CourseDto> updateCourse(@RequestBody @Valid CourseDto courseDto) {
		return new ResponseEntity<>(courseService.update(courseDto), HttpStatus.CREATED);
	}
}