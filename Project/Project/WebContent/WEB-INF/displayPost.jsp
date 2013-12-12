<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">${post.title }</jsp:attribute>

	<jsp:body>
	<div id="fullPost">
		<h1>
				<b><c:out value="${post.title}" escapeXml="true"></c:out></b>
			</h1>
	
	<br />
	Posted by: <c:out value="${post.poster }" escapeXml="true"></c:out> <br />
	Posted on: <c:out value="${post.timeStamp}" escapeXml="true"></c:out> <br />
	<br>
	
	<c:out value="${post.content }" escapeXml="true"></c:out>
	<br />
	
	<c:if test="${not empty sessionScope.currentUser }">
		<form method="get" action="DeletePost">
			<input type="hidden" name="id" value="${post.id }"></input>
			<button type="submit">Delete this post?</button>
		</form>
	</c:if>
	
	<c:forEach var="comment" items="${post.comments }">
		<div>
		<ul>
			<t:commentTemplate comment="${comment }"></t:commentTemplate>
		</ul>
		</div>
	</c:forEach>
	
	<c:if test="${not empty sessionScope.currentUser }">
		<jsp:include page="CRUDpage/AddComment.jsp"></jsp:include>
	</c:if>
	
	
	<a href="GetPosts">Go back to home page</a>
	</div>
</jsp:body>
</t:genericPage>
