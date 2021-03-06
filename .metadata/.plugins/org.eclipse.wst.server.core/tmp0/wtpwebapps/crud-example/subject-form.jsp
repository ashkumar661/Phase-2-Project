<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Learner's Academy</title>
</head>
<body>
	<center>
		<h1>Subject Record Management</h1>
		<h2>
			<a href="<%=request.getContextPath()%>/newSubject">Add New
				Subject</a> &nbsp;&nbsp;&nbsp; <a
				href="<%=request.getContextPath()%>/listSubject">List All
				Subjects</a>

		</h2>
		<c:if test="${userSubject != null}">
			<form action="updateSubject" method="post">
		</c:if>
		<c:if test="${userSubject == null}">
			<form action="insertSubject" method="post">
		</c:if>
		<table border="1">
			<caption>
				<h2>
					<c:if test="${userSubject != null}">
                        Edit Subject Record
                    </c:if>
					<c:if test="${userSubject == null}">
                        Add New Subject Record
                    </c:if>
				</h2>
			</caption>
			<c:if test="${userSubject != null}">
				<input type="hidden" name="id"
					value="<c:out value='${userSubject.id}' />" />
			</c:if>
			<tr>
				<th>Class Name</th>
				<td><input type="text" name="cName" size="45"
					value="<c:out value='${userSubject.className}' />" /></td>
			</tr>
			<tr>
				<th>Subject Name</th>
				<td><input type="text" name="sName" size="45"
					value="<c:out value='${userSubject.subName}' />" /></td>
			</tr>			
		</table>
		<br> <input type="submit" value="Save" />
		</form>
	</center>
</body>
</html>