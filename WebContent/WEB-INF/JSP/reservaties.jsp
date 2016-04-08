<%@ page contentType='text/html' pageEncoding='UTF-8' session='true' trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Reservaties" />
</head>

<body>
	<vdab:displayMandje/>
	
	<h1>Reservaties</h1>
	<nav>
	<ul>
	<c:forEach var="genre" items="${genres}">
		<c:url value="/index.htm" var="genreURL">
			<c:param name="genre" value="${genre}"/>
		</c:url>
		<li> 
		<c:choose>
		<c:when test="${genre.name == param.genre}">
			${genre}
		</c:when>
		<c:otherwise>
			<a href="<c:out value="${genreURL}"/>">${genre}</a>
		</c:otherwise>
		</c:choose>
		</li>
	</c:forEach>
	</ul>
	</nav>
	
	 	
	<c:forEach var="film" items="${filmsByGenre}">
		<c:url value="/filmDetail.htm" var="filmURL">
			<c:param name="film" value="${film.titel}"/>
		</c:url>
		<a href="<c:out value="${filmURL}" />">
			<img title=
			"<c:choose>
			<c:when test="${film.gereserveerd < film.voorraad}">
				RESERVATIE MOGELIJK
			</c:when>
			<c:otherwise>
				RESERVATIE NIET MOGELIJK (NIET OP VOORRAAD)
			</c:otherwise>
			</c:choose>" 
			alt="${film.titel}" src="<c:url value="/images/filmPosters/${film.id}.jpg" />" />
		</a>
	</c:forEach>

</body>

</html>