<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit A Post</title>
</head>
<body>
	<form id="addPostForm" method="post" action="CreatePost">
		Title: <input required type="text" name="title" maxlength="250">
		Content: 
		<textarea maxlength="500" required form="addPostForm" name ="content" placeholder="Enter a description of your problem/question here."></textarea>
		<input type="submit" value="Submit">
	</form>
</body>
</html>