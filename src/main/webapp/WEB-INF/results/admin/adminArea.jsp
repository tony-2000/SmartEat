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

    <main class="field">
        <form id="gestioneMensa"
              action="${pageContext.request.contextPath}/AdminMensaArea"
              method="get" style="display: none"></form>

        <form id="gestioneMenu"
              action="${pageContext.request.contextPath}/AdminMenuArea"
              method="get" style="display: none"></form>

        <form id="gestionePietanze"
              action="${pageContext.request.contextPath}/AdminPietanzeArea"
              method="get" style="display: none"></form>

        <form id="gestioneUtenti"
              action="${pageContext.request.contextPath}/AdminUtentiArea"
              method="get" style="display: none"></form>

        <form id="statsMensa"
              action="${pageContext.request.contextPath}/AdminStatsArea"
              method="get" style="display: none"></form>

        <form>
            <h2>Funzioni amministrative</h2><hr>
            <input type="submit" form="gestioneMensa" value="Gestisci la mensa">
            <input type="submit" form="gestioneMenu" value="Gestisci i menù">
            <input type="submit" form="gestionePietanze" value="Gestisci le pietanze">
            <input type="submit" form="gestioneUtenti" value="Gestisci gli utenti">
            <input type="submit" form="statsMensa" value="Visualizza le statistiche degli acquisti"><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
