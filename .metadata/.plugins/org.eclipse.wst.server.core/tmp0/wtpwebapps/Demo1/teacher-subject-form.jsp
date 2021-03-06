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
		<h1>Assign Teacher to Class <%=request.getParameter("id") %> </h1>
		<form action="insertTeacherSubject" method="post">
		<input type="hidden" name="className" value=<%= request.getParameter("id")%>>
			<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/demo1" user="root"
				password="123456" />
				<sql:query dataSource="${db}" var="subjectRecord">
	select * from subjects where className='<%=request.getParameter("id") %>';
	</sql:query>
			<sql:query dataSource="${db}" var="teacherRecord">
	select * from teacher;
	</sql:query>
	
			<table>
				<tr>
					<td>Subject</td>
					<td><select name="selectSubject" id="selectSubject">
							<option disabled value="" selected>Select a subject</option>
							<c:forEach var="row" items="${subjectRecord.rows}">
								<option value='<c:out value="${row.subName}"/>'><c:out
										value="${row.subName}" /></option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>Teacher</td>
					<td><select name="selectTeacher" id="selectTeacher">
							<option disabled value="" selected>Select a teacher</option>
							<c:forEach var="row" items="${teacherRecord.rows}">
								<option value='<c:out value="${row.tName}"/>'><c:out
										value="${row.tName}" /></option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
				</tr>
			</table>
			<input type="submit">
		</form>
		<sql:query dataSource="${db}" var="recordSet">
	select * from sub_teacher;
	</sql:query>
	<br>
		<table border="1" cellpadding="5">
		<tr>
			<td>ID</td>
			<td>Class Name</td>
			<td>Subject Name</td>
			<td>Teacher</td>
		</tr>
			<c:forEach var="row" items="${recordSet.rows}">
				<tr>
					<td>${row.id}</td>
					<td>${row.className}</td>
					<td>${row.subName}</td>
					<td>${row.tName}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>