
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="comment" rtexprvalue="true" required="true" type="entities.Comment" %>
	<div class="vote">
		<form method="get" action="RateComment">
			<input type="hidden" name="id" value="${comment.id }"></input>
			<button name="upvote" type="submit"><i class="fa fa-thumbs-o-up fa-2x"></i></button>
			<button name="downvote" type="submit"><i class="fa fa-thumbs-o-down fa-2x"></i></button>
		</form>
	</div>
	<div class="info">
		<span>${comment.rating }</span> 
		<span>${comment.poster }</span>
		<p>${comment.content }</p>
		<p>${comment.timeStamp }</p>
	</div>

