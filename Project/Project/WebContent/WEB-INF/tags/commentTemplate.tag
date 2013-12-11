
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="comment" rtexprvalue="true" required="true" type="entities.Comment" %>
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
	<div class="info">
		<span>${comment.rating }</span> 
		<span>${comment.poster }</span>
		<p>${comment.content }</p>
		<p>${comment.timeStamp }</p>
	</div>

