<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:genericPage>
	<jsp:attribute name="title">Login</jsp:attribute>

	<jsp:body>
	<form method="POST" action="j_security_check">
	<h1>Login Here</h1>
		<span>User Name: </span> 
		<input type="text" name="j_username" /><br /> 
		<span>Password: </span>
		<input type="password" name="j_password" /><br /> 
		<input type="submit" value="Login" />
	</form>
	</jsp:body>
</t:genericPage>