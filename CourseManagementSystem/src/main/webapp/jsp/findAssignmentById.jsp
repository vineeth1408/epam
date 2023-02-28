<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/assignmentDashboard/find" method="post">
		<table>
		<caption>Assignment</caption>
		<th>Course</th>
			<tr>	
				<td><label for="assignmentId">Assignment Id</label></td>
				<td><input type="text" name="assignmentId" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="findAssignmentById"> </td>
			</tr>
		</table>
	</form:form>
	<hr />
	<a href="/assignmentDashboard">GO BACK TO DASHBAORD</a>
</body>
</html>