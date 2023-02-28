<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Assignments</h1>

	<hr />

	<table border="1">
		<caption>Assignment Data</caption>
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
				
			</tr>

		</core:forEach>

	</table>
	<hr />
	<a href="/assignmentDashboard">GO BACK TO DASHBAORD</a>
</body>
</html>