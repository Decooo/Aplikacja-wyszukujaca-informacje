<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 21.11.2017
  Time: 01:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dodaj ogloszenie</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div id="container">
    <h1>Dodawanie nowego ogloszenia</h1>

    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <form:form commandName="advert" method="post" action="${pageContext.request.contextPath}save" role="form">

        Kategoria: <form:select path="id_kategoria"><form:options items="${id_kategoria}" itemValue="id_kategoria"
                                                                  itemLabel="nazwa"/></form:select><br/><br/>
        Forma zatrudnienia: <form:select path="id_forma_zatrudnienia"><form:options items="${id_forma_zatrudnienia}"
                                                                                    itemValue="id_forma_zatrudnienia"
                                                                                    itemLabel="nazwa"/></form:select><br/><br/>
        Stanowisko: <form:select path="id_stanowisko"><form:options items="${id_stanowisko}" itemValue="id_stanowisko"
                                                                    itemLabel="nazwa"/></form:select><br/><br/>
        <div id="box3">Tytul: <form:input path="tytul" /><form:errors path="tytul" id="error"/>
        </div>
        <div id="box3">
        Lokalizacja: <form:input path="lokalizacja" /><form:errors path="lokalizacja" id="error"/>
        </div>
        <div id="box3">
        Zarobki: <form:input path="zarobki"/><form:errors path="zarobki"  id="error"/>
        </div>
        <p></p>
        Opis: <form:input path="opis" id="error"/><form:errors path="opis"/><br/><br/>

        <form:button>Dodaj</form:button>
    </form:form>


</div>

<jsp:include page="_footer.jsp"/>
</body>
</html>
