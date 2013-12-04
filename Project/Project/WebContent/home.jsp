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
	
	<form method="get" >
		<button type="submit" name="addPost">Add a new post</button>
	</form>
	
	<c:if test="$(not empty request.posts)">
		<form method="get" action="ViewPost">
		<c:forEach var="nextPost" items="${posts }" >
			<div>
				<br />
				<b>${nextPost.getTitle() }</b>
				<br />
				Posted by: ${nextPost.getPoster() }
				<br />
				Posted on: ${nextPost.getTimeStamp() }
				<br />
				<input type="hidden" value="${nextPost.id() }" name="id" ></input>
				<button type="submit">Open this post</button>
			</div>
		</c:forEach>
		</form>
	</c:if>

	<c:if test="${not empty sessionScope.username }">
		<a href="logout">Log out</a>
		<a href="accountHandler">Manage account</a>
	</c:if>
	<a href="AddUser">Add User</a>
</body>
</html>