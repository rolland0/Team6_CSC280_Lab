<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title>Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.currentUser.username }">
			<h1>Welcome, <c:out escapeXml="true" value="${sessionScope.currentUser.username }"></c:out>!</h1>
		</c:when>
		<c:otherwise>
			<h1>Welcome, guest!</h1>
			<br>
			<h2><a href="LoginForward">Login</a> | <a href="AddUser">Create An Account</a></h2>
		</c:otherwise>
	</c:choose>
	<br />
	Search post titles:
	<form method="post" action="GetPosts">
		<input type="text" name="searchQuery" placeholder="Enter your search term"></input>
		<button type="submit">Go</button>
	</form>
	<br />
	
	<form method="get" action="CreatePost" >
		<button type="submit" name="addPost">Add a new post</button>
	</form>
	
	<c:if test="${not empty posts}">
		<form method="get" action="ViewPost">
		<c:forEach var="nextPost" items="${posts }" >
			<div>
				<br />
				<b>${nextPost.title}</b>
				<br />
				Posted by: ${nextPost.poster.username}
				<br />
				Posted on: ${nextPost.timeStamp}
				<br />
				<input type="hidden" value="${nextPost.id}" name="id" ></input>
				<button type="submit">Open this post</button>
			</div>
		</c:forEach>
		</form>
	</c:if>

	<c:if test="${not empty sessionScope.currentUser.username }">
		<a href="Logout">Logout</a> | <a href="UserProfile">Manage profile</a>
	</c:if>
	
</body>
</html>