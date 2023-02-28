<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Enter course Id</title>
</head>
<body>
	<form:form action="/viewAssignmentByCourseId" method="post">
		<table>
			<caption>Course Input</caption>
			<th>Course</th>
			<tr>	
				<td><label for="courseId">Course Id</label></td>
				<td><input type="text" name="courseId" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Display"> </td>
			</tr>
			
		</table>
	</form:form>
	<hr />
	<a href="/assignmentDashboard">GO BACK TO DASHBAORD</a>
</body>
</html>