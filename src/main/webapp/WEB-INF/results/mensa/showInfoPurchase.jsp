<%@ page import="model.Utente" %>
<%@ page import="model.Menu" %>
<%@ page import="model.Acquisto" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 23/12/2021
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza l'acquisto</title>
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
    </header> <br>

    <main>
        <%
            Acquisto acquisto = (Acquisto) session.getAttribute("acquisto");
            System.out.println("acquisto (nella jsp) " + (acquisto == null)); //DEBUG
            Menu menu = (Menu) session.getAttribute("menu");
            System.out.println("menu (nella jsp) " + (menu == null)); //DEBUG
        %>
        <h1>Info acquisto</h1>
        <h2><%=menu.getNome()%></h2>
        <p><%=menu.getDescrizione()%></p>
        <ul>
            <li><%=menu.getPrimo()%></li>
            <li><%=menu.getSecondo()%></li>
            <li><%=menu.getDessert()%></li>
        </ul>
        <p><%=acquisto.getDataAcquisto()%>, €<%=menu.getPrezzo()%></p>
        <%
            if (acquisto.isRefund()) {
        %>
            <form action="DeletePurchase" method="post">
                <input type="hidden" name="dataAcquisto" value="<%=acquisto.getDataAcquisto()%>">
                <input type="hidden" name="CF" value="<%=acquisto.getCF()%>">
                <input type="hidden" name="codiceMenu" value="<%=acquisto.getCodiceMenu()%>">
                <input type="submit" value="Rimborsa">
            </form>
        <%
            }
        %>
    </main>

    <footer></footer>
</body>
</html>
