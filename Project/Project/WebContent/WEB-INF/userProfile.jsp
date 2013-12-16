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

<form method="post" action="UserProfile" class="post">
<h1>Welcome, ${currentUser.username}</h1>
<p>Password: </p>
<input name="password" type="password" maxlength="50"><br>
<p>Email: </p>
<input name="email" type="email" maxlength="100" value="${currentUser.email}"><br>
<input type="submit" value="Update Data"><br>
<a href="GetPosts">Go back to home page</a>
</form>

</jsp:body>
</t:genericPage>