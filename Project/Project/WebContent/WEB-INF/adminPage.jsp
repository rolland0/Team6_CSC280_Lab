<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>

	<jsp:attribute name="title">Welcome, Administration</jsp:attribute>
	<jsp:body>
	
	<div class="post">
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
					<h1>
								<label for="password">Please Enter the Administration Code.</label>
							</h1>
					<input type="password" name="password"></input>
					<button type="submit">Enter</button>
				</form>
			</c:otherwise>
		</c:choose>
			<br />
			<a href="GetPosts">Go back to home page</a>
			</div>
		</jsp:body>
</t:genericPage>
</body>
</html>