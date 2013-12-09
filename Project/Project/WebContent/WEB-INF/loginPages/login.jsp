<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title>Login</title>
</head>
<body>
	<h1>Login Here</h1>
	<form method="POST" action="j_security_check">
		User Name: <input type="text" name="j_username" /><br />
		Password: <input type="password" name="j_password" /><br />
		<input type="submit" value="Login" />
	</form>
</body>
</html>