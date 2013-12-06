<html>
<head>
<link rel="stylesheet" href="style/Style.css" type="text/css">
</head>
<body>
	<div class="vote">
		<button name="upvote">Thumbs up</button>
		<button name="downvote">Thumbs down</button>
	</div>
	<div class="info">
		<span>${rating }</span> 
		<span>${poster }</span>
		<p>${content }</p>
		<p>${timestamp }</p>
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

