<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dashboard</title>
</head>
<body>
<center>
Welcome <%=session.getAttribute("name")%>&nbsp; <br>
	<a href="logout">Logout</a>
	<h2>Select an option from below</h1>
	<table>
		<th><a href="student-list.jsp"><h3>List all Students</h3></a></th>
		<tr></tr>
		<th><a href="teacher-list.jsp"><h3>List all Teachers</h3></a></th>
		<tr></tr>
		<th><a href="class-list.jsp"><h3>List all Classes</h3></a></th>
		<tr></tr>
		<th><a href="subject-list.jsp"><h3>List all Subjects</h3></a></th>
		<tr></tr>
	</table>
	</center>
</body>
</html>