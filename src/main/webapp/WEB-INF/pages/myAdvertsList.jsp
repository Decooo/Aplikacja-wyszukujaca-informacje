<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 08.12.2017
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="list" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Moje ogloszenia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="main">

    <h1>Moje ogłoszenia</h1>

    <table border="1" align="center">
        <th>Kategoria</th>
        <th>Forma zatrudnienia</th>
        <th>Stanowisko</th>
        <th>Tytul</th>
        <th>Lokalizacja</th>
        <th>Zarobki</th>
        <th>Opis</th>


        <list:forEach var="advert" items="${adverts}" varStatus="loop">
            <tr>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="cat"
                                  items="${category}">${cat.nazwa}</list:forEach></td>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="formOfEmployment"
                                  items="${formOfEmployments}">${formOfEmployment.nazwa}</list:forEach></td>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="position"
                                  items="${positions}">${position.nazwa}</list:forEach></td>
                <td>${advert.tytul}</td>
                <td>${advert.lokalizacja}</td>
                <td>${advert.zarobki}</td>
                <td>${advert.opis}</td>
            </tr>
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>


</body>
</html>
