<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">${post.title }</jsp:attribute>

	<jsp:body>
	<div style="text-align: center;">
		<h1>
				<b>${post.title}</b>
			</h1>
	</div>
	<br />
	Posted by: ${post.poster }
	<br />
	Posted on: ${post.timeStamp}
	<br />
	${post.content }
	<br />
	
	<c:forEach var="comment" items="${post.comments }">
		<div>
			<t:commentTemplate comment="${comment }"></t:commentTemplate>
		</div>
	</c:forEach>
	
	<c:if test="${not empty sessionScope.currentUser }">
		<jsp:include page="WEB-INF/CRUDpage/AddComment.jsp"></jsp:include>
	</c:if>
	
	
	<a href="GetPosts">Go back to home page</a>
</jsp:body>
</t:genericPage>
