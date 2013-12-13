<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome, Administrator!</title>
</head>
<body>
	<t:genericPage>
		<jsp:attribute name="title">This is the admin's page</jsp:attribute>
		<jsp:body>
				<br />
		<c:choose>
			<c:when test="${not empty requestScope.admin }">
				<form method="post" action="Promote">
					Promote another user:
					<select name="userName">
						<c:forEach var="user" items="${userList }">
							<option>${user }</option>
						</c:forEach>	
					</select>
					<button type="submit">Enter</button>
				</form>
			</c:when>
			<c:otherwise>
				<form method="post" action="PromotionPassword">
					To become an admin, enter the admin password:
					<input type="password" name="password"></input>
					<button type="submit">Enter</button>
				</form>
			</c:otherwise>
		</c:choose>
			<br />
			<a href="GetPosts">Go back to home page</a>
		</jsp:body>
	</t:genericPage>
</body>
</html>