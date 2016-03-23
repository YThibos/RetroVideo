<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags"%>

<!DOCTYPE HTML>
<html>

<head>
<vdab:head title="Klant" />
</head>

<body>
	<a href="<c:url value="/index.htm" />">Reservaties</a>
	<a href="<c:url value="/mandje.htm" />">Mandje</a>

	<h1>Klant</h1>

	<form method="get" action="">
		<label>Familienaam bevat:</label> <input type="text"
			name="familienaam" value="${param.familienaam}" autofocus required
		/> <input type="submit" value="Zoeken" />
	</form>

	<table>
		<thead>
			<tr>
				<th>Naam</th>
				<th>Straat - Huisnummer</th>
				<th>Postcode</th>
				<th>Gemeente</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="klant" items="${gevondenKlanten}">
				<tr>
					<td>
						<c:url value="/bevestigen.htm" var="bevestigenURL">
							<c:param name="klant" value="${klant.id}"/>
						</c:url>
						<a href="${bevestigenURL}">${klant.voornaam} ${klant.familienaam}</a>
					</td>
					<td>${klant.straatNummer}</td>
					<td>${klant.postcode}</td>
					<td>${klant.gemeente}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

</html>