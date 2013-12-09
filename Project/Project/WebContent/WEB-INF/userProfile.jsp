<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>

<c:if test="${not empty sessionScope.profileChanges }">
	<c:forEach items="${sessionScope.profileChanges}" var="change">
		<c:out value="${change}" />
	</c:forEach>
</c:if>

<h1>Welcome, ${username}</h1> <br>
<form method="post" action="UserProfile">
Password: <input name="password" type="password" maxlength="50"><br>
Email: <input name="email" type="email" maxlength="100" value="${email}"><br>
<input type="submit" value="Update Data">
</form>

</body>
</html>