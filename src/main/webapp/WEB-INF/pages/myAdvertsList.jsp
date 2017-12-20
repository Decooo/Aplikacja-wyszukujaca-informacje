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

<div id="container">

    <h1>Moje ogłoszenia</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>




    <list:forEach var="advert" items="${adverts}" varStatus="loop">

        <div id="tytul2">${advert.tytul}</div>
        <div id="box"><h>Autor:</h> <list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="user"
                                                  items="${users}">${user.login}</list:forEach></div>
        <div id="box"><h>Kategoria:</h> <list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="cat"
                                                      items="${category}">${cat.nazwa}</list:forEach></div>
        <div id="box"><h>Forma zatrudnienia:</h> <list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="formOfEmployment"
                                                               items="${formOfEmployments}">${formOfEmployment.nazwa}</list:forEach></div>
        <div id="box"><h>Stanowisko:</h> <list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="position"
                                                       items="${positions}">${position.nazwa}</list:forEach></div>

        <div id="box"><h>Lokalizacja:</h> ${advert.lokalizacja}</div>
        <div id="box"><h>Zarobki:</h> ${advert.zarobki}</div>
        <div id="opis"> ${advert.opis}</div>

        <input type="submit" value="Usuń" onclick="location.href='usun/${advert.id_ogloszenie}';"/>
        <input type="submit" value="Edytuj" onclick="location.href='edytuj/${advert.id_ogloszenie}';"/>
        <hr />

        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>


</body>
</html>
