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
		<table border="1">
			<caption>
				<h2>Date List</h2>
				
				<a href="<%=request.getContextPath()%>/listDate">List
			Date</a>
			</caption>
			<tr>
				<th>DOB</th>

			</tr>
			<c:forEach var="dList" items="${listDOB}">
				<tr>
					<td>
					<input type = "date" value="${dList.dob}">				
					<c:out value="${dList.dob}" /></td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>