<button name="upvote"></button>
<button name="downvote"></button>
<span>${rating }</span>
<span>${poster }</span>
<p>${content }</p>
<p>${timestamp }</p>
<form method="post" action="AddComment">
	<input type="hidden" name="origPost" value="${post.id }" />
	<textarea rows="5" cols="50" name="comment">Add a comment</textarea>
	<input type="submit" value="Add comment">
</form>
