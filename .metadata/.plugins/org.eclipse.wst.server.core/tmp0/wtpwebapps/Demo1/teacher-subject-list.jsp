<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body>
	<center>
		<h1>Assigned Teacher and Subject List</h1>
		<h2>
			<a href="<%=request.getContextPath()%>/listClass">List All
				Classes</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
				href="<%=request.getContextPath()%>/listTeacherSubjects">List
				Assigned teacher</a>
		</h2>
		<table border="1">
			<caption>
				<h2>List of Assigned Teachers</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Class Name</th>
				<th>Subject Name</th>
				<th>Teacher Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="teachetsublist" items="${assignedTeacherList}">
				<tr>
					<td><c:out value="${teachetsublist.id}" /></td>
					<td><c:out value="${teachetsublist.cName}" /></td>
					<td><c:out value="${teachetsublist.sName}" /></td>
					<td><c:out value="${teachetsublist.tName}" /></td>
					<td><a
						href="deleteTeacherSubject?id=<c:out value='${teachetsublist.id}'/>">Delete
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>