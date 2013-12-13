<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag language="java" description="Overall page template"
	pageEncoding="ISO-8859-1"%>
<%@attribute name="title" fragment="true"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="style/Style.css" type="text/css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<title><jsp:invoke fragment="title" /></title>
</head>
<body>
	<header>
		<div class="logo">
			<a href="GetPosts"><img src="assets/logo.png" /></a>
		</div>

		<div class="welcomeMessage">
			<span>Welcome, </span>
			<c:choose>
				<c:when test="${not empty sessionScope.currentUser.username }">
					<c:out escapeXml="true"
						value="${sessionScope.currentUser.username }" />
					<span>!</span><br>
					<a href="Logout">Logout</a> | 
					<a href="UserProfile">Manage profile</a> |
					<a href="Promote">Administration</a>
				</c:when>
				<c:otherwise>
					<span>guest!</span>
					<br>
					<a href="LoginForward">Login</a> | 
					<a href="AddUser">Create An Account</a>
				</c:otherwise>
			</c:choose>
		</div>

			<form method="post" action="GetPosts" class="searchSite">
				<input type="text" name="searchQuery" placeholder="Search Posts" class="searchBox"></input>
				<input type="submit" name="searchBtn" class="searchBtn" value="Search"/>
			</form>

	</header>
	<div id="body">
		<jsp:doBody />
	</div>
</body>
</html>