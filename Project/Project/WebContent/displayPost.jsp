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
	<b>${post.getTitle() }</b>
	<br />
	Posted by: ${post.getPoster() }
	<br />
	Posted on: ${post.getTimeStamp() }
	<br />
	${post.getContent() }
	<br />
	
	<c:forEach var="comment" items="${post.comments }" >
		<div>
			<br />
			Posted by: ${comment.poster }
			<br />
			Posted on: ${comment.timeStamp) }
			<br />
			${commentcontent }
		</div>
	</c:forEach>
	
	<!--<showComment:commentTemplate comment=""></showComment:commentTemplate>-->
	
	<a href="GetPosts">Go back to home page</a>
</body>
</html>