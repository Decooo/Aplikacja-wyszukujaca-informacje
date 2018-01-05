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

<div id="container" >

        <h1>Wyszukaj</h1>
        <c:if test="${not empty msg}">
            <div class=${css}>${msg}</div>
        </c:if>
    <div id="box4">
      <form:form commandName="search" action="advancedSearch" method="post" role="form">
          Słowo Kluczowe: <input type="search" name="inquiry" value="${pageContext.request.getAttribute("inquiry")}">
    </div>

    <div id="box4">
        Lokalizacja: <input type="search" name="location" value="${pageContext.request.getAttribute("location")}">
    </div>
    <div id="box4">
        Kategoria: <form:select path="id_kategoria"><option value=0 selected="selected">Nie wybrano</option><form:options items="${id_kategoria}" itemValue="id_kategoria"
                                                                 itemLabel="nazwa"/></form:select>
    </div>

    <div id="box4">
    Forma zatrudnienia: <form:select path="id_forma_zatrudnienia"><option value=0 selected="selected">Nie wybrano</option><form:options items="${id_forma_zatrudnienia}"
                                                                                    itemValue="id_forma_zatrudnienia"
                                                                                    itemLabel="nazwa"  /></form:select>
    </div>

    <div id="box4">
    Stanowisko: <form:select path="id_stanowisko"><option value=0 selected="selected">Nie wybrano</option><form:options items="${id_stanowisko}" itemValue="id_stanowisko"
                                                                    itemLabel="nazwa"/></form:select>
    </div>

    <div id="box4">
    Zarobki:<select size="1" name="salary">
        <option selected="selected">${pageContext.request.getAttribute("salary")}</option>
        <c:if test="${not empty salary}">
        <option></option>
        </c:if>
        <option value="0-1500">0-1500</option>
        <option value="1501-3000">1501-3000</option>
        <option value="3001-4000">3001-4000</option>
        <option value="4001-5000">4001-5000</option>
        <option value="5001-6000">5001-6000</option>
        <option value="6001-7500">6001-7500</option>
        <option value="7501-10000">7501-10000</option>
        <option value="10001-15000">10001-15000</option>
        <option value="15001-100000">15001-100000</option>
        </select><br/><br/>
    </div>

    <div id="box4">
    <form:button>Wyszukaj</form:button>
    </form:form>
    </div>


    <h1>Znalezione ogłoszenia</h1>




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
        </list:forEach>
    </table>

</div>
<jsp:include page="_footer.jsp"/>


</body>
</html>
