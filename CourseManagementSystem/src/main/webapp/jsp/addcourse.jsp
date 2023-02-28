<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Course</title>
<style type="text/css">
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
<body>
	<h2>Add Course Page</h2>
	<hr />
	${addCourseStatus}
	<form:form action="/addCourseForm" method="post"
		modelAttribute="courseDto">
		<table>
		<caption>Add Course</caption>
		<th>Course</th>
			<tr>
				<td>Course Id</td>
				<td><form:input path="courseId" /></td>
				<td><form:errors path="courseId">
					</form:errors></td>
			</tr>

			<tr>
				<td>Course Name</td>
				<td><form:input path="courseName" /></td>
				<td><form:errors path="courseName"></form:errors></td>
			</tr>
			<tr>
				<td>Course Description</td>
				<td><form:input path="courseDescription" /></td>
				<td><form:errors path="courseDescription"></form:errors></td>
			</tr>

			<tr>
				<td><input type="submit" value="Add Course"></td>
			</tr>
		</table>
	</form:form>
	<hr />
	<a href="/dashboard">GO BACK TO DASHBOARD</a>
</body>
</html>