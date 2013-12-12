<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ attribute name="comment" rtexprvalue="true" required="true" type="entities.Comment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<li>
	<div class="info">
		<span><c:out value="${comment.rating }" escapeXml="true"></c:out></span> 
		<span><c:out value="${comment.poster }" escapeXml="true"></c:out></span>
		<p><c:out value="${comment.content }" escapeXml="true"></c:out></p>
		<p><c:out value="${comment.timeStamp }" escapeXml="true"></c:out></p>
	</div>
	<div class="vote">
		<form method="get" action="RateComment" class="vote">
			<input type="hidden" name="id" value="${comment.id }"></input>
			<button name="upvote" type="submit"><i class="fa fa-thumbs-o-up fa-2x"></i></button>
			<button name="downvote" type="submit"><i class="fa fa-thumbs-o-down fa-2x"></i></button>
		</form>
	</div>
	<div class="reply">
			<form method="post" action="AddComment">
			<input type="hidden" name="origPost" value="${comment.post.id }"/>
			<input type="hidden" name="origComment" value="${comment.id}"/>
			<textarea rows="10" cols="50" name="comment" placeholder="Add a comment."></textarea>
			<input type="submit" value="Add comment">
		</form>
	</div>
	<c:if test="${!empty comment.replies}">
		<c:forEach items="${comment.replies}" var="reply">
		<ul>
			<t:commentTemplate comment="${reply }"></t:commentTemplate>
		</ul>
		</c:forEach>
	</c:if>
</li>