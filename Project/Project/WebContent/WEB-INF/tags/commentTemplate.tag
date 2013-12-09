
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="comment" rtexprvalue="true" required="true" type="entities.Comment" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style/Style.css" type="text/css">
</head>
<body>
	<div class="vote">
		<form method="get" action="RateComment">
			<button name="upvote" type="submit">Thumbs up</button>
			<button name="downvote" type="submit">Thumbs down</button>
		</form>
	</div>
	<div class="info">
		<span>${comment.rating }</span> 
		<span>${comment.poster }</span>
		<p>${comment.content }</p>
		<p>${comment.timestamp }</p>
	</div>
	<div class="commentBox">
		<form method="post" action="AddComment">
			<input type="hidden" name="origPost" value="${post.id }" />
			<textarea rows="5" cols="50" name="comment">Add a comment</textarea>
			<input type="submit" value="Add comment">
		</form>
	</div>
</body>
</html>

