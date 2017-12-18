<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 29.03.2017
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Logowanie</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="main">

    <div class="log-box">
    <br/>
    <c:if test="${param.error == 'true'}">
        <div class="error">

            Logowanie niepoprawne!<br/>
                ${error}

        </div>
    </c:if>

    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

        <h3 >Logowanie:</h3>



        <div id="panel">
            <form method="post" action="${pageContext.request.contextPath}j_spring_security_check" role="form">
            <label for="username">Nazwa użytkownika:</label>
                <input type="text" id="username" name="username">
                <label for="password">Hasło:</label>
                <input type="password" id="password" name="password">
                <div id="lower">
                    <input type="submit" value="Zaloguj">
                    <input type="submit" value="Zarejestruj" onclick="form.action='registration';">
                </div>

            </form>
        </div>
        <%--<table align="center">--%>
            <%--<tr>--%>
                <%--<td style="text-align: left;">Nazwa użytkownika:</td>--%>
                <%--<td><input type="text" name="username"/></td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td style="text-align: left;">Hasło:</td>--%>
                <%--<td><input type="password" name="password"/></td>--%>

            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td>&nbsp;</td>--%>
                <%--<td align="left"><input type="submit" value="Zaloguj"></td>--%>
                <%--<td align="left"><input type="submit" value="Zarejestruj" onclick="form.action='registration';"></td>--%>
            <%--</tr>--%>

        <%--</table>--%>
    <%--</form>--%>
    <%--<br/><br/>--%>

</div>
</div>

<jsp:include page="_footer.jsp"/>
</body>
</html>
