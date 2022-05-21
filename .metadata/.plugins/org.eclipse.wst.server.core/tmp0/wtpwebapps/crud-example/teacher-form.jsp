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
		<c:if test="${userTeacher != null}">
			<form action="updateTeacher" method="post">
		</c:if>
		<c:if test="${userTeacher == null}">
			<form action="insertTeacher" method="post">
		</c:if>
		<table border="1">
			<caption>
				<h2>
					<c:if test="${userTeacher != null}">
                        Edit Teacher Record
                    </c:if>
					<c:if test="${userTeacher == null}">
                        Add New Teacher Record
                    </c:if>
				</h2>
			</caption>
			<c:if test="${userTeacher != null}">
				<input type="hidden" name="id"
					value="<c:out value='${userTeacher.id}' />" />
			</c:if>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name" size="45"
					value="<c:out value='${userTeacher.name}' />" /></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><select name="gender" id="gender">
						<c:if test="${userTeacher == null}">
							<option disabled value="" selected>Select gender</option>

							<option value="Male">Male</option>
							<option value="Female">Female</option>
						</c:if>
						<c:if test="${userTeacher != null}">
							<option value='<c:out value="${userTeacher.gender}"/>'><c:out
									value="${userTeacher.gender}" /></option>
							<c:if test="${userTeacher.gender == 'Male'}">
								<option value="Female">Female</option>
							</c:if>
							<c:if test="${userTeacher.gender == 'Female'}">
								<option value="Male">Male</option>
							</c:if>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<th>DOB</th>
				<td><input type="date" name="dob" size="45"
					value="<c:out value='${userTeacher.dob}' />" /></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input type="text" name="address" size="45"
					value="<c:out value='${userTeacher.address}' />" /></td>
			</tr>
		</table>
		<br> <input type="submit" value="Save" />
		</form>
	</center>
</body>
</html>