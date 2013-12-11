<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">Noooo!</jsp:attribute>

	<jsp:body>
	<h3>Sad giraffe is sad!</h3>
	<p>An error occurred!</p>
	
	<c:if test="${not empty errorMessages }">
		<h2>Errors: </h2>
		<c:forEach var="error" items="${errorMessages }">
			<c:out value="${error }" />
		</c:forEach>
	</c:if>
		
		<!-- sad giraffe image here -->
		<img src="assets/depressed-giraffe.png"/>
		<br>
		<a href="GetPosts">Go home?</a>
	</jsp:body>
</t:genericPage>