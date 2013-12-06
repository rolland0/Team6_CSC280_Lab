<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title>${post.title }</title>
</head>
<body>
	<b>${post.getTitle() }</b>
	<br />
	Posted by: ${post.getPoster() }
	<br />
	Posted on: ${post.getTimeStamp() }
	<br />
	${post.getContent() }
	<br />
	
	<c:forEach var="comment" items="${post.getComments() }" >
		<div>
			<br />
			Posted by: ${comment.getPoster() }
			<br />
			Posted on: ${comment.getTimeStamp() }
			<br />
			${comment.getContent() }
		</div>
	</c:forEach>
	
	
	<a href="Setup">Go back to home page</a>
</body>
</html>