<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Klant"/>	
</head>

<body>
	<a href="<c:url value="/index.htm" />">Reservaties</a>
	<a href="<c:url value="/mandje.htm" />">Mandje</a>
	
	<h1>Klant</h1>
	
	<form method="get" action="">
		<label>Familienaam bevat:</label>
		<input type="text" name="familienaam" value="${param.familienaam}" autofocus required />
		<input type="submit" value="Zoeken" />
	</form>

</body>

</html>