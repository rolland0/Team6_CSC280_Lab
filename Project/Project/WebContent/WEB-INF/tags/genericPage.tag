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
				<c:when test="${currentUser == null }">
					<span>guest</span>
					<br>
					<span>Please <a href="../loginPages/login.jsp">login</a>
						here.
					</span>
				</c:when>
				<c:when test="${currentUser != null }">
					<span>${currentUser }</span>
				</c:when>
			</c:choose>
		</div>

	</header>
	<div id="body">
		<jsp:doBody />
	</div>
</body>
</html>