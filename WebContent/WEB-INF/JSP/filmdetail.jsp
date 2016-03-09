<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Film details" />
</head>

<body>
	<a href="<c:url value="/index.htm" />">Reservaties</a>
	
	<c:choose>
	<c:when test="${not empty film}">
	<h1>${film.titel}</h1>
	<img alt="${film.titel}" src="<c:url value="/images/filmPosters/${film.id}.jpg" />">
	<dl>
		<dt>Prijs</dt>
		<dd>&euro; ${film.prijs}</dd>
		<dt>Voorraad</dt>
		<dd>${film.voorraad} st.</dd>
		<dt>Gereserveerd</dt>
		<dd>${film.gereserveerd} st.</dd>
		<dt>Beschikbaar</dt>
		<c:set var="beschikbaar" value="${film.voorraad - film.gereserveerd}" />
		<dd>${beschikbaar} st.</dd>
	</dl>
	<c:if test="${beschikbaar > 0}">
		<form method="post" action="<c:url value="/mandje.htm" />" id="inmandjeform">
			<input type="submit" value="In mandje" name="${film.id}" id="inmandjeknop" />
		</form>
	</c:if>
	</c:when>
	<c:otherwise>
		<h1>Film niet gevonden</h1>
		<span>${error}</span>
	</c:otherwise>
	</c:choose>
	
	<script>
		document.getElementById("inmandjeform").onsubmit = function() {
			if (!navigator.cookieEnabled) {
				alert("Dit werkt enkel met cookies enabled");
				return false;
			}
			document.getElementById("inmandjeknop").disabled = true;
		};
	</script>
	
</body>

</html>