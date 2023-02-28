<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
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
	<h2>LOGIN PAGE</h2>
	<hr />
	${registrationStatus} ${loginStatus}
	<table>
	<caption>Instructor</caption>
	<form:form action="/loginForm" method="post"
		modelAttribute="instructorDto">

		<tr>
			<th id="">Username</th>
			<td><form:input path="username" /></td>
			<td><form:errors path="username" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<th id="">Password</th>
			<td><form:input path="password" /></td>
			<td><form:errors path="password" cssClass="error"></form:errors></td>
		</tr>

		<tr>
			<td><input type="submit" value="login"></td>
		</tr>
	</form:form>
	</table>
	<hr />
	<a href="/registration">Create an Account here</a>

</body>
</html>