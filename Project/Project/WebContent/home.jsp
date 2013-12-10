<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">Home</jsp:attribute>

	<jsp:body>
		
	<form method="get" action="CreatePost">
		<button type="submit" name="addPost">Add a new post</button>
	</form>
	
	<c:if test="${not empty posts}">
		<c:forEach var="nextPost" items="${posts }">
			<div class="post">
				<br />
				<b>${nextPost.title}</b>
				<br />
				Posted by: ${nextPost.poster.username}
				<br />
				Posted on: ${nextPost.timeStamp}
				<br />
				<form method="get" action="ViewPost">
					<input type="hidden" value="${nextPost.id}" name="id"></input>
					<button type="submit">Open this post</button>
				</form>
			</div>
		</c:forEach>
	</c:if>
	</jsp:body>
</t:genericPage>