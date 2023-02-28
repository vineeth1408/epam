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

	<form:form action="/assignment/update/${assignmentId}" method="post" modelAttribute="assignmentDto">
		<table>
		<caption>ASSIGNMENT</caption>
		<th>Assignment table</th>
			<tr>
				<td><label for="assignmentId">Assignment Id</label></td>
				<td>${assignmentId}</td>
			</tr>

			<tr>
				<td><label for="assignmentName">Assignment Name</label></td>
				<td><form:input type="text" path="assignmentName" /></td>
				<td><form:errors path="assignmentName"></form:errors></td>
			</tr>

			<tr>
				<td><label for="numberOfQuestions">Number Of Questions</label></td>
				<td><form:input type="number" path="numberOfQuestions" /></td>
				<td><form:errors path="numberOfQuestions"></form:errors></td>
			</tr>
			
			<tr>
				<td><label for="deadlineInHours">Deadline in Hours</label></td>
				<td><form:input type="number" path="deadlineInHours" /></td>
				<td><form:errors path="deadlineInHours"></form:errors></td>
			</tr>
				
			<tr>
				<td><label for="marks">marks</label></td>
				<td><form:input type="number" path="marks" /></td>
				<td><form:errors path="marks"></form:errors></td>
			</tr>

			<tr>
				<td><input type="submit" value="Update Assignment"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>