<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body>
	<center>
		<h1>
			Class Report of
			<%=request.getParameter("id")%>
		</h1>
		<input type="hidden" name="className"
			value=<%=request.getParameter("id")%>>
		<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
			url="jdbc:mysql://localhost:3306/demo1" user="root" password="123456" />
		<sql:query dataSource="${db}" var="classRecord">
	select * from sub_teacher where className='<%=request.getParameter("id")%>';
	</sql:query>
		<br>
		<sql:query dataSource="${db}" var="studentRecord">
	select * from student where className='<%=request.getParameter("id")%>';
	</sql:query>
		<table border="1" cellpadding="5">
			<tr>
				<td>ID</td>
				<td>Class Name</td>
				<td>Subject Name</td>
				<td>Teacher</td>
			</tr>
			<c:forEach var="classList" items="${classRecord.rows}">
				<tr>
					<td>${classList.id}</td>
					<td>${classList.className}</td>
					<td>${classList.subName}</td>
					<td>${classList.tName}</td>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<h2>Student List</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
			</tr>
			<c:forEach var="studentList" items="${studentRecord.rows}">
				<tr>
					<td>${studentList.id}</td>
					<td>${studentList.stuName}</td>
					<td>${studentList.stuAddress}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="dashboard.jsp"><h2>Dashboard</h2></a>
	</center>
</body>
</html>