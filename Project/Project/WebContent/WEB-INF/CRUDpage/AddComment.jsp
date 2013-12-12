<!-- this should be a partial page that is added to the post page. -->

<form method="post" action="AddComment" class="addForm" id="addCommentForm">
<input type="hidden" name="origPost" value="${post.id }"/>
<input type="hidden" name="origComment" value="${comment.id}"/>
<textarea name="comment" placeholder="Add a comment."></textarea>
<input type="submit" value="Add comment">
</form>