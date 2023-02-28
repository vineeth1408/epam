<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

</head>
<h1>Registration Page</h1>
<hr />
<body>
	<form:form action="/registrationForm" method="post"
		modelAttribute="instructorDto">
		<table>
		<caption>Instructor</caption>
		<th>Course</th>
			<tr>
				<td>Instructor name</td>
				<td><form:input path="instructorName" /></td>
				<td><form:errors path="instructorName">
					</form:errors></td>
			</tr>

			<tr>
				<td>username</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username"></form:errors></td>
			</tr>
			<tr>
				<td>password</td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password"></form:errors></td>
			</tr>

			<tr>
				<td><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>