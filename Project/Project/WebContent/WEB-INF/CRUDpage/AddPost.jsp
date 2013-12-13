<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>

	<jsp:attribute name="title">
		Submit A Post
	</jsp:attribute>

	<jsp:body>
	<div class="post">
	<form method="post" action="CreatePost">
	<h1>Add a Post</h1>
		<p>Title: </p>
		<input required type="text" rows="10" cols="50" name="title" maxlength="250"><br>
		<p>Content: </p>
		<textarea required maxlength="500" name="content"
					placeholder="Enter a description of your problem/question here."></textarea>
				<br>
		<input type="submit" value="Submit">
	</form>
	</div>
	</jsp:body>
</t:genericPage>