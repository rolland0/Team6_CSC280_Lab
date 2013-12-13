<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<t:genericPage>

	<jsp:attribute name="title">Welcome, Member</jsp:attribute>
	<jsp:body>
		<form method="post" action="PromotionPassword">
			<h1><label for="password">Please Enter the Administration Code.</label></h1>
			<input type="password" name="password"></input>
			<button type="submit">Enter</button>
		</form>
	</jsp:body>
</t:genericPage>
</body>
</html>