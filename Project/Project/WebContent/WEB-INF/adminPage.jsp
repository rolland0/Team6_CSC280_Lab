<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>

	<jsp:attribute name="title">Welcome, Administration</jsp:attribute>
	<jsp:body>
		<c:if test="${not empty success }">
			<h1>${success }</h1>
		</c:if>
	</jsp:body>
</t:genericPage>
</body>
</html>