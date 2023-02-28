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
	<form:form action="/courses/update/${courseId}" method="post" modelAttribute="courseDto">
		<table> 
		<caption>Course</caption>
		<th>Course</th>
			<tr>
				<td><label for="courseId">Course Id</label></td>
				<td>${courseId}</td>
			</tr>

			<tr>
				<td><label for="courseName">Course Name</label></td>
				<td><form:input type="text" path="courseName" /></td>
				<td><form:errors path="courseName"></form:errors></td>
			</tr>
			
			<tr>
				<td><label for="courseDescription">Course Description</label></td>
				<td><form:input type="text" path="courseDescription" /></td>
				<td><form:errors path="courseDescription"></form:errors></td>
			</tr>

			<tr>
				<td><input type="submit" value="Update Course"></td>
			</tr>
			
		</table>
	</form:form>
</body>
</html>