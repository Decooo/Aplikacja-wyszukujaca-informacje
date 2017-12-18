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
    <title>Ogloszenia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div id="container">
    <h1>Wyszukaj</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <form:form action="search" method="post">

        <input type="search" name="inquiry"><br/><br/>
        <button name="btnSearch" >Szukaj</button>

    </form:form>

    <h1 style="text-align:center;color:#193f8f;">Lista wszystkich ogłoszeń</h1>



        <list:forEach var="advert" items="${adverts}" varStatus="loop">

                <div id="tytul">${advert.tytul}</div>
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
                <hr />

        </list:forEach>



    <div class="pagination">
            <list:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<list:url value="/ogloszenia/lista" ><list:param name="page" value="${p}"/>${p}</list:url>"> ${p}</a></list:forEach>
    </div>

</div>
<jsp:include page="_footer.jsp"/>


</body>
</html>
