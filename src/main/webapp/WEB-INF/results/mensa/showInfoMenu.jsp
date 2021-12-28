<%@ page import="model.Menu" %>
<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza menù</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        Menu menu = (Menu) request.getAttribute("menu");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main>
        <h2><%=menu.getNome()%></h2>
        <h4><%=menu.getPrezzo()%>€</h4>
        <p><%=menu.getDescrizione()%></p>
        <ul>
            <li><%=menu.getPrimo()%></li>
            <li><%=menu.getSecondo()%></li>
            <li><%=menu.getDessert()%></li>
        </ul>
        <form action="toBuyMenu" method="post">
            <input type="hidden" id="codiceMenu" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
            <input type="submit" value="Acquista">
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
