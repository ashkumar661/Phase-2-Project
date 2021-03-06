<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:if test="${userClass != null}">
		<form action="updateClass" method="post">
	</c:if>
	<c:if test="${userClass == null}">
		<form action="insertClass" method="post">
	</c:if>
	<table border="1">
		<caption>
			<h2>
				<c:if test="${userClass != null}">
                        Edit Class Record
                    </c:if>
				<c:if test="${userClass == null}">
                        Add New Class Record
                    </c:if>
			</h2>
		</caption>
		<c:if test="${userClass != null}">
			<input type="hidden" name="id"
				value="<c:out value='${userClass.id}' />" />
		</c:if>
		<tr>
			<th>Name</th>
			<td><input type="text" name="name" size="45"
				value="<c:out value='${userClass.cName}' />" /></td>
		</tr>
		<tr>
		<tr>
			<td align="center"><input type="submit" value="Save" /></td>
		</tr>
	</table>
	</form>
	</center>
</body>
</html>