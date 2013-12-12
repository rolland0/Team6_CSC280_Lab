<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome, admin</title>
</head>
<body>
	<t:genericPage>
		<jsp:attribute name="title">This is the admin's page</jsp:attribute>
		<br />
		<form method="post" action="Promote">
			Promote another user:
			<input type="text" placeholder="Enter the name of the member you want to make an admin" value="userName"></input>
			<br />
			<button type="submit">Enter</button>
		</form>
		<br />
		<a href="GetPosts">Go back to home page</a>
	</t:genericPage>
</body>
</html>