<%@taglib prefix="list" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ogloszenia</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="main">

<h2 >Aplikacja  wyszukujaca ogloszenia o prace</h2>
    <img src="${pageContext.request.contextPath}/img/jobsearch.png" align="center" width="425px" height="380px">

</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>
