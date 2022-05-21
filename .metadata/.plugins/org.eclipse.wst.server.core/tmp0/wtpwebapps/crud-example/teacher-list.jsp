<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body>
	<center>
		<h1>Teacher Record Management</h1>
		<h2>
			<a href="<%=request.getContextPath()%>/newTeacher">Add New
				Teacher</a> &nbsp;&nbsp;&nbsp; <a
				href="<%=request.getContextPath()%>/listTeacher">List All
				Teachers</a>
		</h2>
		<table border="1">
			<caption>
				<h2>List of Teachers</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
			<c:forEach var="teacher" items="${listTeacher}">
				<tr>
					<td><c:out value="${teacher.id}" /></td>
					<td><c:out value="${teacher.name}" /></td>
					<td><c:out value="${teacher.gender}" /></td>
					<td><c:out value="${teacher.dob}" /></td>
					<td><c:out value="${teacher.address}" /></td>
					<td><a href="editTeacher?id=<c:out value='${teacher.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteTeacher?id=<c:out value='${teacher.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>