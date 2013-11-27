<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit A Post</title>
</head>
<body>
	<form id="addPostForm" method="post" action="AddPostServlet">
		Title: <input required type="text" name="title" maxlength="100">
		Content: 
		<textarea required form="addPostForm" name ="content" placeholder="Enter a description of your problem/question here."></textarea>
		<input type="submit" value="Submit">
	</form>
</body>
</html>