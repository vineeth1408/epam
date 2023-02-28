<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	${loginStatus}
	
	<h1>welcome to Instructor DashBoard</h1>
	
	<div class="container">
		<hr />
		<a href="/courses">COURSE DASHBOARD </a>
		<hr />
		<a href="/assignmentDashboard">ASSIGNMENT DASHBOARD </a>
		<hr />
		<a href="/logout">LOG OUT</a>
	</div>
</body>
</html>