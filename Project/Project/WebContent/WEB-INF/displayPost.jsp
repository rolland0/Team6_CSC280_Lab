<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<t:genericPage>
	<jsp:attribute name="title">${post.title }</jsp:attribute>

	<jsp:body>
	<div class="post" id="fullPost">
	<div class="postNoComments">
		<h1>
				<c:out value="${post.title}" escapeXml="true"></c:out>
			</h1>
	
	<div class="postShowInfo">
	<span>Posted by: <c:out value="${post.poster }" escapeXml="true"></c:out> </span><br />
	<span>Posted on: <c:out value="${post.timeStamp}"
							escapeXml="true"></c:out></span>
	</div>
	
	<div class="postShow">
	<c:out value="${post.content }" escapeXml="true"></c:out>
	</div>
	
	<c:if test="${not empty sessionScope.currentUser and sessionScope.isAdmin}">
		<form method="post" action="DeletePost" class="deletePostBtn">
			<input type="hidden" name="id" value="${post.id }"></input>
			<button type="submit">Delete this post</button>
		</form>
	</c:if>
	</div>
	
	<c:forEach var="comment" items="${post.comments }">
		<c:if test="${comment.parentComment == null}">
			<div>
				<ul>
					
					<c:set var="isAdminComment" value="false"/>
					
					<c:forEach var="group" items="${comment.poster.groups }">
						<c:if test="${group.toString() eq 'admins' }">
							<c:set var="isAdminComment" value="true"/>
						</c:if>
					</c:forEach>
					
					<c:choose>
					
             			<c:when test="${isAdminComment }">
              				<t:adminCommentTemplate comment="${comment }" post="${post }"></t:adminCommentTemplate>
             			</c:when>
             			
             			<c:otherwise>
               				<t:commentTemplate comment="${comment }" post="${post }"></t:commentTemplate>
             			</c:otherwise>
           			
           			</c:choose>
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