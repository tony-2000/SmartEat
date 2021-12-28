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

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <ul>
            <li><a href="${pageContext.request.contextPath}/AdminMensaArea">Gestisci la mensa</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminMenuArea">Gestisci i menù</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminPietanzeArea">Gestisci le pietanze</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminUtentiArea">Gestisci gli utenti</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminStatsArea">Visualizza le statistiche degli acquisti</a></li>
        </ul>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
