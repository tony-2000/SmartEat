<%@ page import="model.Utente" %>
<%@ page import="model.Menu" %>
<%@ page import="model.Tessera" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 23/12/2021
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Acquista menù</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        Menu menu = (Menu) request.getAttribute("menu");
        Tessera tessera = (Tessera) request.getAttribute("tessera");
        int postiVuoti = (int) request.getAttribute("postiVuoti");
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
        <p>Hai attualmente <b>€<%=tessera.getSaldo()%></b> disponibili.</p>
        <form action="BuyMenu" method="post">
            <%
                if (postiVuoti > 0) {
            %>
                <p>Desideri anche prenotare un posto?</p>
                <input type="radio" id="postotrue" name="postoMensa" value="true"> <label for="postotrue">Sì</label> <br>
                <input type="radio" id="postofalse" name="postoMensa" value="false"> <label for="postofalse">No</label> <br>
            <%
                } else {
            %>
                <input type="hidden" name="postoMensa" value="false">
            <%
                }
            %>
            <input type="hidden" id="codiceMenu" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
            <input type="submit" value="Conferma acquisto">
        </form>
    </main>

    <footer></footer>
</body>
</html>
