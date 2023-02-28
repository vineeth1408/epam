<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>
<style>
.container {
	text-align: center;
}
.h1 {
	text-align: center;
}
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
	<div class="h1">
		<h1>Welcome to Course Management System</h1>
	</div>
	<div class="container">
		<a href="/login" >LOGIN</a> 
		<a href="/registration" >REGISTER</a>
	</div>
</body>
</html>