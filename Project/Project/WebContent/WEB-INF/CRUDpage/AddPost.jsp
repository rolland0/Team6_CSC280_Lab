<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>

	<jsp:attribute name="title">
		Submit A Post
	</jsp:attribute>

	<jsp:body>
	<form class="addPostForm" method="post" action="CreatePost">
		<p>Title: </p>
		<input required type="text" name="title" maxlength="250"><br>
		<p>Content: </p>
		<textarea maxlength="500" required form="addPostForm" name="content"
				placeholder="Enter a description of your problem/question here."></textarea>
		<input type="submit" value="Submit">
	</form>
	</jsp:body>
</t:genericPage>