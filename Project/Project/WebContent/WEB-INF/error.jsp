<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="title">Noooo!</jsp:attribute>

	<jsp:body>
	<div class="sadGiraffe">
	<h3>Sad giraffe is sad!</h3>
	<p>An error occurred!</p>

	<c:if test="${not empty error}">
			<h2>
					<c:out value="${error }" />
				</h2>
	</c:if>
	
	
		
		<!-- sad giraffe image here -->
		<img src="assets/depressed-giraffe.png" />
		<a
				href="http://sketchflood.blogspot.com/2006/12/depressed-giraffe.html">credits</a>
		<br>
		<a href="GetPosts">Go home?</a>
		</div>
	</jsp:body>
</t:genericPage>


http://sketchflood.blogspot.com/2006/12/depressed-giraffe.html
