<%@ tag description="" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty mandjeIDs}">
	<a href="<c:url value="/mandje.htm" />">Mandje</a>
</c:if>