<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericPage>

	<jsp:attribute name="title">Welcome, Member</jsp:attribute>
	<jsp:body>
		<div class="post">
				<form method="post" action="PromoteAnotherUser">
					Promote another user:
					<select name="userName">
						<c:forEach var="user" items="${userList }">
							<option>${user }</option>
						</c:forEach>	
					</select>
					<button type="submit">Enter</button>
				</form>
				<br />
				<a href="PromoteMePlease">Administration</a>
		</div>
	</jsp:body>
</t:genericPage>
</body>
</html>