<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Server error"/>
</head>

<body>
	<a href="<c:url value="/index.htm" />">Reservaties</a>

	<h1>Server Error</h1>
	<img alt="Server error" src="<c:url value="/images/workmen.jpg" />">
	<p>Contact the administrator to report this issue</p>
</body>

</html>