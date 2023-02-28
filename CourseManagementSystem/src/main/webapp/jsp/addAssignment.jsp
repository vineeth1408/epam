<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
	
	<h2>Add Assignment To Course</h2>
	<hr />
	${addStatus}
	<form:form action="/assignmentInputForm/${courseId}" method="post" modelAttribute="AssignmentDto">
		<table>
			<caption>Assignment</caption>
			<th>Course</th>
			<tr>	
				<td><label for="assignmentName">Assignment Name</label></td>
				<td><input type="text" name="assignmentName" required></td>
			</tr>
			
			<tr>	
				<td><label for="assignmentId">Assignment Id</label></td>
				<td><input type="text" name="assignmentId" required></td>
			</tr>
			
			
			<tr>	
				<td><label for="numberOfQuestions">Number Of Questions</label></td>
				<td><input type="number" name="numberOfQuestions" required></td>
			</tr>
			
			<tr>	
				<td><label for="deadlineInHours">Deadline in Hours</label></td>
				<td><input type="number" name="deadlineInHours" required></td>
			</tr>
			
			<tr>	
				<td><label for="marks">Marks</label></td>
				<td><input type="number" name="marks" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Add Assignment"></td>
			</tr>
		</table>
	</form:form>
	<hr />
	<a href="/assignmentDashboard">GO BACK TO ASSIGNMENT DASHBOARD</a>
</body>
</html>