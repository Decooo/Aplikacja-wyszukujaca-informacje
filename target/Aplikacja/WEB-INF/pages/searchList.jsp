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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Znalezione ogłoszenia</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="main">
    <h1>Wyszukaj</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <form:form action="search" method="post">

        <input type="search" name="inquiry" value="${pageContext.request.getAttribute("inquiry")}"><br/><br/>
        <button name="btnSearch">Szukaj</button>

    </form:form>

    <form:form commandName="search" action="advancedSearch" method="post" role="form">
        Lokalizacja: <input type="search" name="location"><br/>

        Kategoria: <form:select path="id_kategoria"><form:options items="${id_kategoria}" itemValue="id_kategoria"
                                                                  itemLabel="nazwa"/></form:select><br/><br/>
        Forma zatrudnienia: <form:select path="id_forma_zatrudnienia"><form:options items="${id_forma_zatrudnienia}"
                                                                                    itemValue="0"
                                                                                    itemLabel="nazwa"  /></form:select><br/><br/>
        Stanowisko: <form:select path="id_stanowisko"><form:options items="${id_stanowisko}" itemValue="id_stanowisko"
                                                                    itemLabel="nazwa"/></form:select><br/><br/>
        Zarobki:<select size="1" name="salary">
        <option value="1">0-1000</option>
        <option value="2">1000-2000</option>
        <option value="3">2000-3000</option>
        <option value="4">4000-5000</option>
        <option value="5">5000-6000</option>
        <option value="6">6000-7500</option>
        <option value="7">7500-10000</option>
        <option value="8">10000-15000</option>
        <option value="9">15000-100000</option>
        </select><br/><br/>
        <form:button>Wyszukaj</form:button>
    </form:form>

    <h1>Znalezione ogłoszenia</h1>

    <table border="1" align="center">
        <th>Autor</th>
        <th>Kategoria</th>
        <th>Forma zatrudnienia</th>
        <th>Stanowisko</th>
        <th>Tytul</th>
        <th>Lokalizacja</th>
        <th>Zarobki</th>
        <th>Opis</th>


        <list:forEach var="advert" items="${adverts}" varStatus="loop">
            <tr>
                <td><list:forEach begin="${loop.index}" step="1" end="${loop.index}" var="user"
                                  items="${users}">${user.login}</list:forEach></td>
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