<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Rapport"/>
</head>

<body>
	<h1>Rapport</h1>
	
	<c:choose>
		<c:when test="${allSuccess == true}">
			<p>Alle reservaties zijn OK</p>
		</c:when>
		<c:otherwise>
			<p>Reservatie van</p>
			<ul>
			<c:forEach var="entry" items="${filmGereserveerd}" >
				<li>${entry.key.titel}: ${entry.value } </li>
			</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>

</body>

</html>