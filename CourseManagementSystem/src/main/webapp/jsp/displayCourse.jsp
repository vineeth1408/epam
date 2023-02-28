<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>displayCourse</title>

<style type="text/css">
.labeltext {
	font-weight: bold;
	color: blue;
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
	
	<h3>View Course</h3>
	<hr />

	<table border="1">
	<caption>Course</caption>
	<th>Course</th>
		<tr>
			<td class="labeltext">Course Id</td>
			<td>${course.courseId}</td>
		</tr>
		<tr>
			<td class="labeltext">Course Name</td>
			<td>${course.courseName}</td>
		</tr>
		<tr>
			<td class="labeltext">Course Duration</td>
			<td>${course.courseDescription}</td>
		</tr>
	</table>
	<hr />
	<a href="/dashboard">GO BACK TO DASHBAORD</a>
</body>
</html>