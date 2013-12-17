<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="comment" rtexprvalue="true" required="true"
	type="entities.Comment"%>
<%@ attribute name="post" rtexprvalue="true" required="true"
	type="entities.Post"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<li>
<div class="adminComment">
	<c:if test="${not empty sessionScope.isAdmin}">
		<div>
			<form method="post" action="DeleteComment">
				<input type="hidden" name="comment" value="${comment.id }"></input>
				<button type="submit">Delete this comment</button>
			</form>
		</div>
	</c:if>
		<div class="info">
			<div class="commenterInfo">
				<h3>Administrator, <c:out value="${comment.poster }" escapeXml="true"></c:out>, said: </h3>		
			<p class="commentContent">
				<c:out value="${comment.content }" escapeXml="true"></c:out>
			</p>
				<div style="text-align:center;">
					 <c:out value="${comment.timeStamp }" escapeXml="true"></c:out>
				</div>
			</div>

		</div>

		<div class="reply">
			<c:if test="${not empty sessionScope.currentUser }">
				<form method="post" action="AddComment" class="commentForm">
					<input type="hidden" name="origPost" value="${post.id }" /> <input
						type="hidden" name="origComment" value="${comment.id}" />
					<textarea name="comment" placeholder="Add a comment."></textarea>
					<input type="submit" value="Add comment">
				</form>
			</c:if>
		</div>
		<c:if test="${!empty comment.replies}">
			<c:forEach items="${comment.replies}" var="reply">

					<div>
						<ul>

							<c:set var="isAdminReply" value="false" />

							<c:forEach var="group" items="${reply.poster.groups }">
								<c:if test="${group.toString() eq 'admins' }">
									<c:set var="isAdminReply" value="true" />
								</c:if>
							</c:forEach>

							<c:choose>

								<c:when test="${isAdminReply }">
									<t:adminCommentTemplate comment="${reply }" post="${post }"></t:adminCommentTemplate>
								</c:when>

								<c:otherwise>
									<t:commentTemplate comment="${reply }" post="${post }"></t:commentTemplate>
								</c:otherwise>

							</c:choose>
						</ul>
					</div>			
			</c:forEach>
		</c:if>
	</div>
</li>