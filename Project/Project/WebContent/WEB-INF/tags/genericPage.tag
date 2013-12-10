<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag language="java" description="Overall page template"
	pageEncoding="ISO-8859-1"%>
<%@attribute name="title" fragment="true"%>
<html>
<head>
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title><jsp:invoke fragment="title" /></title>
</head>
<body>
	<header>
		<div class="logo">
			<img src="/../../assets/logo.png" />
		</div>

		<div class="welcomeMessage">
			<span>Welcome, </span>
			<c:choose>
				<c:when test="${not empty sessionScope.currentUser.username }">
					<c:out escapeXml="true"
						value="${sessionScope.currentUser.username }" />
					<span>!</span>
					<a href="Logout">Logout</a> | 
					<a href="UserProfile">Manage profile</a>
				</c:when>
				<c:otherwise>
					<span>guest!</span>
					<br>
					<a href="LoginForward">Login</a> | 
					<a href="AddUser">Create An Account</a>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="searchBox">
			<form method="post" action="GetPosts">
				<input type="text" name="searchQuery" placeholder="Search Posts"></input>
				<button type="submit">Search</button>
			</form>
		</div>

	</header>
	<div id="body">
		<jsp:doBody />
	</div>
</body>
</html>