<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<div align="center">
		<h1>Admin Login</h1>
		<form action="Login" method="post">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" placeholder="Enter username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" placeholder="Enter password"/></td>
				</tr>
			</table>
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>
