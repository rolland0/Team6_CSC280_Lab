<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="comment" rtexprvalue="true" required="true"
	type="entities.Comment"%>
<%@ attribute name="post" rtexprvalue="true" required="true"
	type="entities.Post"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="vote">
	<form method="get" action="RateComment" class="vote">
		<input type="hidden" name="id" value="${comment.id }"/>
		<button name="upvote" type="submit">
			<i class="fa fa-thumbs-o-up"></i>
		</button>
		<span> <c:out value="${comment.rating }" escapeXml="true"></c:out></span>
		<button name="downvote" type="submit">
			<i class="fa fa-thumbs-o-down"></i>
		</button>
	</form>
</div>

<div>
	<form method="post" action="DeleteComment">
		<input type="hidden" name="comment" value="${comment.id }"></input>
		<button type="submit">Delete this comment</button>
	</form>
</div>

<li>
	<div class="info">
		<div class="commenterInfo">
			<em>Posted by: </em>
			<c:out value="${comment.poster }" escapeXml="true"></c:out>
			<em> at </em>
			<c:out value="${comment.timeStamp }" escapeXml="true"></c:out>
		</div>
		<p class="commentContent">
			<c:out value="${comment.content }" escapeXml="true"></c:out>
		</p>
		<c:if test="${comment.poster.groups.contains('admins')}">
			AWESOME
		</c:if>
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
	</div> <c:if test="${!empty comment.replies}">
		<c:forEach items="${comment.replies}" var="reply">
			<ul>
				<t:commentTemplate comment="${reply }" post="${post }"></t:commentTemplate>
			</ul>
		</c:forEach>
	</c:if>
</li>