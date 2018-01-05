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
        <hr>
        <div id="box2" >
            <h1> Wyszukaj Ogłoszenie:</h1>
        </div>
        <div id="box2">

            <c:if test="${not empty msg}">
                <div class=${css}>${msg}</div>
            </c:if>



            <form:form action="search" method="post">


                <div id="lower">
                    <input type="search" name="inquiry">

                    <input  type="submit" name="btnSearch"  value="SZUKAJ">
                </div>
             </form:form>
        </div>

    <h1>Lista wszystkich ogłoszeń</h1>


        <div class="pagination">
            <list:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<list:url value="/ogloszenia/lista" ><list:param name="page" value="${p}"/>${p}</list:url>"> ${p}</a></list:forEach>
        </div>

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
                <hr />

        </list:forEach>



    <div class="pagination">
            <list:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<list:url value="/ogloszenia/lista" ><list:param name="page" value="${p}"/>${p}</list:url>"> ${p}</a></list:forEach>
    </div>

</div>
<jsp:include page="_footer.jsp"/>


</body>
</html>
