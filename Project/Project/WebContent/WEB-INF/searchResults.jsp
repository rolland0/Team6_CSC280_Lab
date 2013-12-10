<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>
	<jsp:attribute name="title">Search Results</jsp:attribute>

	<jsp:body>
	<c:if test="${not empty requestScope.titleMatches}">
		Title Matches <br>
		<c:forEach items="${titleMatches}" var="titleMatch">
		<c:set var="postLink" value="ViewPost?id=${titleMatch.id}" />
			<a href="${postLink}">
			<c:out value="${titleMatch.title }"></c:out>
			</a>
				<br />
		</c:forEach>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.contentMatches}">
		Content Matches <br>
		<c:forEach items="${contentMatches}" var="contentMatch">
			<c:set var="postLink" value="ViewPost?id=${contentMatch.id}" />
			<a href="${postLink}">
			<c:out value="${contentMatch.content }"></c:out>
			</a>
				<br />
		</c:forEach>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.commentMatches}">
		Comment Matches <br>
		<c:forEach items="${commentMatches}" var="commentMatch">
			<c:set var="postLink" value="ViewPost?id=${commentMatch.post.id}" />
			<a href="${postLink}">
			<c:out value="${commentMatch.content }"></c:out>
			</a>
				<br />
		</c:forEach>
	</c:if>
	<br>
	
</jsp:body>
</t:genericPage>