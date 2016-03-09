<%@ tag description="Standard head metadata tag" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="title" type="java.lang.String" required="true" %>

<!-- TITLE -->
<title>${title}</title>

<!-- METADATA -->
<meta http-equiv="content-type" content="text/html" charset="UTF-8" >
<meta name="author" content="Yannick Thibos">
<meta name="keywords" content="retrovideo, video, film, reservation system">
<meta name="description" content="Online video reservation system for RetroVideo">
<meta name='viewport' content='width=device-width,initial-scale=1'>

<!-- LINKS -->
<link rel="shortcut icon" href="<c:url value="/images/favicon.ico" />" type="image/x-icon" />
<link rel="stylesheet" href="<c:url value="/styles/normalize.css"/>" />
<link rel="stylesheet" href="<c:url value="/styles/default.css"/>" />
