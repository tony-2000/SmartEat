<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Amministrativa</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    %>
    <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <ul>
            <li><a href="${pageContext.request.contextPath}/AdminMenuArea">Visualizza i menù</a></li>
        </ul>
    </main>

    <footer></footer>
</body>
</html>
