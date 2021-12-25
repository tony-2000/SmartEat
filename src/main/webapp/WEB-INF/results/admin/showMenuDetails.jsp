<%@ page import="model.Utente" %>
<%@ page import="model.Menu" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza dettagli menù</title>

    <script>
        function confirmDelete() {
            return confirm("Sicuro di voler eliminare il menù selezionato?");
        }
    </script>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    %>
    <%
        Menu menu = (Menu) request.getAttribute("menu");
    %>
    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>
    <main>
        <h2><%=menu.getNome()%></h2>
        <h4><%=menu.getPrezzo()%>€</h4>
        <p><%=menu.getDescrizione()%></p>
        <ul>
            <li><%=menu.getPrimo()%></li>
            <li><%=menu.getSecondo()%></li>
            <li><%=menu.getDessert()%></li>
        </ul>

        <form action="ToggleMenu" method="post">
            <input type="hidden" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
            <input type="submit" value="<%=menu.isAvailable() ? "Disattiva" : "Attiva"%>">
        </form>

        <form action="DeleteMenu" method="post">
            <input type="hidden" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
            <input type="submit" value="Elimina menù" onclick="return confirmDelete()">
        </form>
    </main>

    <footer></footer>
</body>
</html>
