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
	<c:if test="${not empty requestScope.error }">
			${requestScope.error }
	</c:if>
	<h3>Please enter a username, password, and email.</h3>

	<form method="post" action="AddUserServlet">
		Username: <input name="username" required type="text" maxlength="20"><br>
		Password: <input name="password" required type="password" maxlength="50"><br>
		Email: <input name="email" required type="email" maxlength="100"><br>
		<input type="submit" value="Create Account">
	</form>

</body>
</html>