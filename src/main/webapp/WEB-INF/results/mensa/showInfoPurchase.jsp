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
        Acquisto acquisto = (Acquisto) request.getAttribute("acquisto");
        Menu menu = (Menu) request.getAttribute("menu");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="display">
        <div class="box">
            <div style="flex: 1; display: flex; flex-direction: row; justify-content: center; align-items: center">
                <div style="width: 50%; display: flex; flex-direction: row; justify-content: center">
                    <img src="${pageContext.request.contextPath}/covers/<%=menu.getImmagine()%>" alt="cover"
                         style="flex: 1; height: 25rem; width: 5rem; object-fit: cover">
                </div>
                <div style="width: 50%; height: 100%; margin: 0 1rem; display: flex; flex-direction: column">
                    <h1><%=menu.getNome()%></h1>
                    <p>Acquisto del <b><%=acquisto.getDataAcquisto()%></b></p>
                    <p>Prezzo: <b><%=menu.getPrezzo()%>€</b></p>
                    <p><%=menu.getDescrizione()%></p>
                    <p><%=menu.getPrimo()%></p>
                    <p><%=menu.getSecondo()%></p>
                    <p><%=menu.getDessert()%></p>
                    <div style="flex: 1"></div>
                    <form action="DeletePurchase" method="post">
                        <input type="hidden" name="dataAcquisto" value="<%=acquisto.getDataAcquisto()%>">
                        <input type="hidden" name="CF" value="<%=acquisto.getCF()%>">
                        <input type="hidden" name="codiceMenu" value="<%=acquisto.getCodiceMenu()%>">
                        <input type="submit" value="Rimborsa" <% if (!acquisto.isRefund()) { %>disabled<% } %>>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
