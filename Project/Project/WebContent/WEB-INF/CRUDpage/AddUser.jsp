<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<h3>Please enter a username, password, and email.</h3>
	<form method="post" action="AddUser">
		Username: <input name="username" required type="text" maxlength="20">
		Password: <input name="password" required type="password" maxlength="50">
		Email: <input name="email" required type="email" maxlength="100">
		<input type="submit">
	</form>

</body>
</html>