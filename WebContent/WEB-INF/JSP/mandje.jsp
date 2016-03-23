<%@ page contentType='text/html' pageEncoding='UTF-8' session='true'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>

<head>
<vdab:head title="Mandje" />
</head>

<body>
	<a href="<c:url value="/index.htm" />">Reservaties</a>
	<a href="<c:url value="/klant.htm" />">Klant</a>

	<h1>Mandje</h1>

	<form action="" method="post">
		<table>
			<thead>
				<tr>
					<th>Film</th>
					<th>Prijs</th>
					<th><input type="submit" value="Verwijderen" />
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${filmsInMandje}">
					<tr>
						<td>${film.titel}</td>
						<td>&euro;${film.prijs}</td>
						<td><input class='verwijderCheck' type='checkbox'
							name='remove' value='${film.id}'></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

</body>

</html>