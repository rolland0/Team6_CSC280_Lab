<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="showComment" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title>${post.title }</title>
</head>
<body>
	<div style="text-align:center;">
		<h1><b>${post.title}</b></h1>
	</div>
	<br />
	Posted by: ${post.poster }
	<br />
	Posted on: ${post.timeStamp}
	<br />
	${post.content }
	<br />
	
	<c:forEach var="comment" items="${post.comments }" >
		<div>
			<showComment:commentTemplate comment="${comment }"></showComment:commentTemplate>
		</div>
	</c:forEach>
	
	
	
	<a href="GetPosts">Go back to home page</a>
</body>
</html>