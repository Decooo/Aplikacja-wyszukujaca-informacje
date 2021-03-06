<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 30.03.2017
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
  

<div class="menu">
    <a href="${pageContext.request.contextPath}/">Strona główna</a>
    <a href="${pageContext.request.contextPath}/ogloszenia/lista">Ogłoszenia</a>
    <security:authorize access="hasAnyRole('ROLE_klient')">
        <a href="${pageContext.request.contextPath}/ogloszenia/dodaj">Dodaj ogłoszenie</a>
    </security:authorize>
    <security:authorize access="hasAnyRole('ROLE_klient')">
        <a href="${pageContext.request.contextPath}/ogloszenia/moje">Moje ogłoszenia</a>
    </security:authorize>
</div>