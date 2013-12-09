<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
<title>Search Results</title>
</head>
<body>
	<c:if test="${not empty requestScope.titleMatches}">
		Title Matches <br>
		<c:forEach items="${titleMatches}" var="titleMatch">
		<c:set var="postLink" value="ViewPost?id=${titleMatch.id}" />
			<a href="${postLink}">
			<c:out value="${titleMatch.title }"></c:out>
			</a><br />
		</c:forEach>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.contentMatches}">
		Content Matches <br>
		<c:forEach items="${contentMatches}" var="contentMatch">
			<c:set var="postLink" value="ViewPost?id=${contentMatch.id}" />
			<a href="${postLink}">
			<c:out value="${contentMatch.content }"></c:out>
			</a><br />
		</c:forEach>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.commentMatches}">
		Comment Matches <br>
		<c:forEach items="${commentMatches}" var="commentMatch">
			<c:set var="postLink" value="ViewPost?id=${commentMatch.post.id}" />
			<a href="${postLink}">
			<c:out value="${commentMatch.content }"></c:out>
			</a><br />
		</c:forEach>
	</c:if>
	<br>
	
</body>
</html>