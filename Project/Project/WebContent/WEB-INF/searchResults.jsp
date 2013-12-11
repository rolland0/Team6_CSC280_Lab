<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>
	<jsp:attribute name="title">Search Results</jsp:attribute>

	<jsp:body>
	<c:if test="${not empty requestScope.titleMatches}">
	<div class="post">
		<h1>Title Matches</h1> <br>
		<c:forEach items="${titleMatches}" var="titleMatch">
			<c:set var="postLink" value="ViewPost?id=${titleMatch.id}" />
			<a href="${postLink}">
			<c:out value="${titleMatch.title }"></c:out>
			</a>
					<br />
		</c:forEach>
		</div>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.contentMatches}">
	<div class="post">
		<h1>Content Matches</h1> <br>
		<c:forEach items="${contentMatches}" var="contentMatch">
			<c:set var="postLink" value="ViewPost?id=${contentMatch.id}" />
			<a href="${postLink}">
			<c:out value="${contentMatch.content }"></c:out>
			</a>
					<br />
		</c:forEach>
		</div>
	</c:if>
	<br>
	
	<c:if test="${not empty requestScope.commentMatches}">
	<div class="post">
		<h1>Comment Matches</h1> <br>
		<c:forEach items="${commentMatches}" var="commentMatch">
			<c:set var="postLink" value="ViewPost?id=${commentMatch.post.id}" />
			<a href="${postLink}">
			<c:out value="${commentMatch.content }"></c:out>
			</a>
					<br />
		</c:forEach>
		</div>
	</c:if>
	<br>
	
</jsp:body>
</t:genericPage>