<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<div align ="center">
	<h1>This is a Home screen</h1>
	Welcome <%=session.getAttribute("name")%>&nbsp; <br>
	<a href="logout">Logout</a>
</div>
	
</body>
</html>