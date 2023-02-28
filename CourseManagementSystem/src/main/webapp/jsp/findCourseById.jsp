<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Find</title>
<style>
input[type=submit] {
  background-color: #04AA6D;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
</head>
<body>

	<form:form action="/courses/findCourseById" method="post">
		<table>
		<caption>Course</caption>
		<th>Course</th>
			<tr>	
				<td><label for="courseId">Course Id</label></td>
				<td><input type="text" name="courseId" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="findCourseById"> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>