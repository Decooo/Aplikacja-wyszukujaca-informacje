<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 06.04.2017
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Rejestracja</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">

</head>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">
    <h1>Rejestracja</h1><br/>


    <c:if test="${not empty msg}">
        <div class=${css}>${msg}</div>
    </c:if>

    <form:form commandName="users" method="post" action="${pageContext.request.contextPath}save" role="form">
        <h3>Wprowadz swoje dane</h3>
        Login: <form:input path="login"/><form:errors path="login"/> <br/><br/>
        Hasło: <form:input path="haslo"/><form:errors path="haslo"/><br/><br/>

        <form:button >Zarejestruj</form:button>
    </form:form>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>
