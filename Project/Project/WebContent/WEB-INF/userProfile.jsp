<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericPage>
	<jsp:attribute name="title">User Profile</jsp:attribute>

	<jsp:body>
<c:if test="${not empty sessionScope.profileChanges }">
	<c:forEach items="${sessionScope.profileChanges}" var="change">
		<c:out value="${change}" />
	</c:forEach>
</c:if>

<form method="post" action="UserProfile">
<h1>Welcome, ${username}</h1> <br>
Password: <input name="password" type="password" maxlength="50"><br>
Email: <input name="email" type="email" maxlength="100" value="${email}"><br>
<input type="submit" value="Update Data">
<a href="GetPosts">Go back to home page</a>
</form>

</jsp:body>
</t:genericPage>