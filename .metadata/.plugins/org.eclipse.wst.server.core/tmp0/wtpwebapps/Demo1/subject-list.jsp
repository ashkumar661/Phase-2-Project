<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body> <center>
	<h1>Subject Record Management</h1>
	<h2>
		<a href="<%=request.getContextPath()%>/newSubject">Add New Subject</a>
		&nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/listSubject">List
			All Subjects</a>
	</h2>
	<table border="1">
		<caption>
			<h2>List of Subjects</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Class</th>
			<th>Subject</th>
			<th>Action</th>
		</tr>
		<c:forEach var="subList" items="${listSubject}">
			<tr>
				<td><c:out value="${subList.id}" /></td>
				<td><c:out value="${subList.cName}" /></td>
				<td><c:out value="${subList.subName}" /></td>
				<td><a href="editSubject?id=<c:out value='${subList.id}' />">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="deleteSubject?id=<c:out value='${subList.id}' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>