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
<h1>Welcome, ${user.username}</h1>
<p>Password: </p>
<input name="password" type="password" maxlength="50"><br>
<p>Email: </p>
<input name="email" type="email" maxlength="100" value="${user.email}"><br>
<input type="submit" value="Update Data"><br>
<a href="GetPosts">Go back to home page</a>
</form>

<form method="post" action="RemoveUser" class="deletePostBtn">
	<input type="hidden" name="id" value="${user.id }"></input>
	<button type="submit">Remove My Account</button>
</form>

</jsp:body>
</t:genericPage>