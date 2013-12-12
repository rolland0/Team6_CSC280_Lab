<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>
	<jsp:attribute name="title">Login</jsp:attribute>

	<jsp:body>
	<form method="POST" action="j_security_check" class="addForm">
	<h2>Login Here</h2>
		<p>User Name: </p> 
		<input type="text" name="j_username" /><br /> 
		<p>Password: </p>
		<input type="password" name="j_password" /><br /> 
		<input type="submit" value="Login" />
	</form>
	</jsp:body>
</t:genericPage>