<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head> <center>
<body>
	<h1>Student Record Management</h1>
	<h2>
		<a href="<%=request.getContextPath()%>/newStudent">Add New Student</a>
		&nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/listStudent">List
			All Students</a>
	</h2>
	<table border="1">
		<caption>
			<h2>List of Students</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Address</th>
			<th>Class</th>
			<th>Action</th>
		</tr>
		<c:forEach var="student" items="${listStudent}">
			<tr>
				<td><c:out value="${student.id}" /></td>
				<td><c:out value="${student.name}" /></td>
				<td><c:out value="${student.address}" /></td>
				<td><c:out value="${student.cName}" /></td>
				<td><a href="editStudent?id=<c:out value='${student.id}' />">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="deleteStudent?id=<c:out value='${student.id}' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>