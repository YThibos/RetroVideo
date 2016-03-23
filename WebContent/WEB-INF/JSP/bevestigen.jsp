<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Bevestigen"/>
</head>

<body>
	<h1>Bevestigen</h1>
	
	<p>${filmCount} film(s) voor ${klant.voornaam} ${klant.familienaam}</p>
	<form method="post" action="<c:url value="/rapport.htm"/>">
		<input type="submit" value="Bevestigen">
	</form>	
	 
</body>

</html>