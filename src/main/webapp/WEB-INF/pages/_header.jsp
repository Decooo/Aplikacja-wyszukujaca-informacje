<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 30.03.2017
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header-container">


    <div class="site-name">
        <img src="${pageContext.request.contextPath}/img/logoUR.png" align="left" width="292px" height="43px">
    </div>

    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            Witaj
            <a href="${pageContext.request.contextPath}/accountInfo">
                    ${pageContext.request.userPrincipal.name} </a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/logout">Wyloguj</a>

        </c:if>

        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Zaloguj</a>
        </c:if>
        </div>

</div>