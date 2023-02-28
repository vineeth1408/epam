<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
a:link, a:visited {
	background-color: #f44336;
	color: white;
	padding: 14px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

a:hover, a:active {
	background-color: red;
}
</style>
<body>
	<h1>Courses</h1>

	<hr />

	<table border="1">
	<caption>Course</caption>
		<tr>
			<th>Course Id</th>
			<th>Course Name</th>
			<th>Course Description</th>
		</tr>

		<core:forEach items="${courses}" var="course">

			<tr>
				<td>${course.getCourseId()}</td>
				<td>${course.getCourseName()}</td>
				<td>${course.getCourseDescription()}</td>
				<td> <a href="/courses/updateview/${course.courseId}">UPDATE COURSE</a> 
				<td> <a href="/courses/deletecourse/${course.courseId}">DELETE COURSE</a></td>
				<td> <a href="/addAssignment/${course.courseId}">ADD ASSIGNMENT</a></td>
			</tr>

		</core:forEach>
	</table>
	<hr />
	<a href="/addcourse">ADD NEW COURSE</a>
	<a href="/findCourseById">FIND COURSE BY ID</a>
	<a href="/dashboard">GO BACK TO DASHBAORD</a>
</body>

</html>