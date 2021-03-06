<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body> <center>
	<h1>Class Record Management</h1>
	<h2>
		<a href="<%=request.getContextPath()%>/newClass">Add New Class</a>
		&nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/listClass">List
			All Classes</a>
	</h2>
	<table border="1">
		<caption>
			<h2>List of Classes</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Class Name</th>
			<th>Action</th>
		</tr>
		<c:forEach var="cList" items="${listClass}">
			<tr>
				<td><c:out value="${cList.id}" /></td>
				<td><c:out value="${cList.cName}" /></td>
				<td><a href="editClass?id=<c:out value='${cList.id}' />">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="deleteClass?id=<c:out value='${cList.id}' />">Delete</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="newTeacherSubject?id=<c:out value='${cList.cName}' />">Assign Subjects and Teachers</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="classReport?id=<c:out value='${cList.cName}' />">Class Report</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>