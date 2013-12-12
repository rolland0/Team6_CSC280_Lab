<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:genericPage>
	<jsp:attribute name="title">Add User</jsp:attribute>

	<jsp:body>
	<c:if test="${not empty requestScope.error }">
			${requestScope.error }
	</c:if>

	<form method="post" action="AddUser" class="addForm">
	<h3>Please enter a username, password, and email.</h3>
		<p>Username: </p>
		<input name="username" required type="text" maxlength="20"><br>
		<p>Password: </p>
		<input name="password" required type="password" maxlength="50"><br>
		<p>Email: </p>
		<input name="email" required type="email" maxlength="100"><br>
		<input type="submit" value="Create Account">
	</form>

</jsp:body>
</t:genericPage>