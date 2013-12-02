<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.username }">
			Welcome, ${sessionScope.username }
		</c:when>
		<c:otherwise>
			Welcome, guest
			<br />
			<a href="login">Click here to login</a>
		</c:otherwise>
	</c:choose>
	<br />
	Search post titles:
	<form method="post" action="Search">
		<input type="text" name="searchQuery" placeholder="Enter your search term"></input>
		<button type="submit">Go</button>
	</form>
	<br />
	
	<c:if test="$(not empty request.posts)">
		<c:forEach var="nextPost" items="${posts }" >
			<br />
			<b>${nextPost.getTitle() }</b>
			<br />
			${nextPost.getContent() }
			<br />
			Posted by: ${nextPost.getPoster() }
			<br />
			Posted on: ${nextPost.getTimeStamp() }
			<hr />
		</c:forEach>
	</c:if>

	<c:if test="${not empty sessionScope.username }">
		<a href="logout">Log out</a>
		<a href="accountHandler">Manage account</a>
	</c:if>
</body>
</html>