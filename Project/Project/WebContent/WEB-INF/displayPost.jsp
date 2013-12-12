<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">${post.title }</jsp:attribute>

	<jsp:body>
	<div class="post">
		<h1>
				<b>${post.title}</b>
			</h1>
	
	<br />
	Posted by: ${post.poster } <br />
	Posted on: ${post.timeStamp} <br />
	${post.content } <br />
	
	<c:if test="${not empty sessionScope.currentUser }">
		<form method="get" action="DeletePost">
			<input type="hidden" name="id" value="${post.id }"></input>
			<button type="submit">Delete this post?</button>
		</form>
	</c:if>
	
	<c:forEach var="comment" items="${post.comments }">
		<c:if test="${comment.parentComment == null}" >
			<div>
				<ul>
					<t:commentTemplate comment="${comment }"></t:commentTemplate>
				</ul>
			</div>
		</c:if>
	</c:forEach>
	
	<c:if test="${not empty sessionScope.currentUser }">
		<jsp:include page="CRUDpage/AddComment.jsp"></jsp:include>
	</c:if>
	
	
	<a href="GetPosts">Go back to home page</a>
	</div>
</jsp:body>
</t:genericPage>
