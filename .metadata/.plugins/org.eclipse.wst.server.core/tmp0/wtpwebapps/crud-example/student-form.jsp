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
	<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/LearnersAcademy" user="root"
		password="123456" />
	<sql:query dataSource="${db}" var="classRecord">
	select className from classes;
	</sql:query>
	<center>
		<h1>Student Record Management</h1>
		<h2>
			<a href="<%=request.getContextPath()%>/newStudent">Add New
				Student</a> &nbsp;&nbsp;&nbsp; <a
				href="<%=request.getContextPath()%>/listStudent">List All
				Students</a>

		</h2>
		<c:if test="${userStudent != null}">
			<form action="updateStudent" method="post">
		</c:if>
		<c:if test="${userStudent == null}">
			<form action="insertStudent" method="post">
		</c:if>
		<table border="1">
			<caption>
				<h2>
					<c:if test="${userStudent != null}">
                        Edit Student Record
                    </c:if>
					<c:if test="${userStudent == null}">
                        Add New Student Record
                    </c:if>
				</h2>
			</caption>
			<c:if test="${userStudent != null}">
				<input type="hidden" name="id"
					value="<c:out value='${userStudent.id}' />" />
			</c:if>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${userStudent.name}' />" /></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><select name="gender" id="gender">
						<c:if test="${userStudent == null}">
							<option disabled value="" selected>Select gender</option>

							<option value="Male">Male</option>
							<option value="Female">Female</option>
						</c:if>
						<c:if test="${userStudent != null}">
							<option value='<c:out value="${userStudent.gender}"/>'><c:out
									value="${userStudent.gender}" /></option>
							<c:if test="${userStudent.gender == 'Male'}">
								<option value="Female">Female</option>
							</c:if>
							<c:if test="${userStudent.gender == 'Female'}">
								<option value="Male">Male</option>
							</c:if>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<th>DOB</th>
				<td><input type="date" name="dob" size="45"
					value="<c:out value='${userStudent.dob}' />" /></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input type="text" name="address" size="45"
					value="<c:out value='${userStudent.address}' />" /></td>
			</tr>
			<tr>
				<th>Class</th>
				<td><select name="className" id="className">
							<option disabled value="" selected>Select a subject</option>
							<c:forEach var="row" items="${classRecord.rows}">
								<option value='<c:out value="${row.className}"/>'><c:out
										value="${row.className}" /></option>
							</c:forEach>
					</select></td>
			</tr>
		</table>
		<br> <input type="submit" value="Save" />
		</form>
	</center>
</body>
</html>