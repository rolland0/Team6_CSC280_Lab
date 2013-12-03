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

<form method="post" action="AddUser">
	Username: <input type="text" name="username"> <br>
	Email: <input type="text" name="email"> <br>
	Password: <input type="text" name="password"> <br>
	<input type="submit" value="Create">
</form>

</body>
</html>