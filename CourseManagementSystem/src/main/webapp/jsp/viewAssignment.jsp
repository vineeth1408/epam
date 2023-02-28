<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>View Assignment</h3>
	<hr />

	<table border="1">
		<caption>Assignment Data</caption>
		<th>Assignment table</th>
		<tr>
			<td class="labeltext">Assignment Id</td>
			<td>${assignment.getAssignmentId()}</td>
		</tr>
		<tr>
			<td class="labeltext">Assignment Name</td>
			<td>${assignment.getAssignmentName()}</td>
		</tr>
		<tr>
			<td class="labeltext">Marks</td>
			<td>${assignment.getMarks()}</td>
		</tr>
		
		<tr>
			<td class="labeltext">Number Of Questions</td>
			<td>${assignment.getNumberOfQuestions()}</td>
		</tr>
		
		<tr>
			<td class="labeltext">DeadLine in Hours</td>
			<td>${assignment.getDeadlineInHours()}</td>
		</tr>
		
		
	</table>
	<hr />
	<a href="/assignmentDashboard">GO BACK TO DASHBAORD</a>
</body>
</html>