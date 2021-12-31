<%@ page import="model.Utente" %>
<%@ page import="model.Mensa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione mensa</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        List<String> mensa = (List<String>) request.getAttribute("mensa");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="field">
        <form action="UpdateMensa" method="post">
            <h2><%=mensa.get(0)%></h2><hr>

            <label for="postiDisponibili">Numero posti disponibili</label>
            <input type="number" id="postiDisponibili" name="postiDisponibili" value="<%=mensa.get(1)%>"><br>

            <label for="orarioApertura">Orario di apertura</label>
            <input type="time" id="orarioApertura" step="1" name="orarioApertura" value="<%=mensa.get(2)%>"><br>

            <label for="orarioChiusura">Orario di chiusura</label>
            <input type="time" id="orarioChiusura" step="1" name="orarioChiusura" value="<%=mensa.get(3)%>"><br>

            <input type="submit" value="Conferma modifiche"><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
