<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Assignment</title>
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
</head>
<body>
	
	<h1>Welcome to Assignment DashBoard</h1>

	<hr />

	<table border="1">
	<caption>Assignment</caption>
		<tr>
			<th>Assignment Id</th>
			<th>Name</th>
			<th>Marks</th>
			<th>Number of Questions</th>
			<th>Deadline in Hours</th>
		</tr>

		<core:forEach items="${assignments}" var="assignment">

			<tr>
				<td>${assignment.getAssignmentId()}</td>
				<td>${assignment.getAssignmentName()}</td>
				<td>${assignment.getMarks()}</td>
				<td>${assignment.getNumberOfQuestions()}</td>
				<td>${assignment.getDeadlineInHours()}</td>
				<td> <a href="/assignment/updateview/${assignment.assignmentId}">UPDATE </a>
				<td> <a href="/assignment/delete/${assignment.assignmentId}">DELETE </a></td>
			</tr>

		</core:forEach>

	</table>

	<a href="/displayAssignmentsBasedOnCourseId">VIEW ASSIGNMENTS FOR A
		COURSE</a>
	<a href="/findAssignmentById">FIND ASSIGNMENT BY ID</a>
	<hr />
	<a href="/dashboard">GO BACK TO COURSE DASHBOARD</a>

</body>
</html>