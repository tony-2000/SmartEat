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
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="display">
        <div class="box">
            <h1 style="margin-bottom: 1rem"><%=menu.getNome()%></h1>
            <div style="flex: 1; display: flex; flex-direction: row; justify-content: center; align-items: center">
                <div style="flex: 1; height: 100%; margin-right: 1rem; display: flex; flex-direction: column">
                    <img src="${pageContext.request.contextPath}/covers/<%=menu.getImmagine()%>" alt="cover"
                         style="width: 100%; height: 15rem; object-fit: cover">
                    <div style="flex: 1"></div>
                    <p><b>€<%=menu.getPrezzo()%></b></p>
                    <p><%=menu.getDescrizione()%></p>
                    <p><%=menu.getPrimo()%>, <%=menu.getSecondo()%>, <%=menu.getDessert()%></p>
                    <div style="flex: 1"></div>
                </div>
                <div style="flex: 1; height: 100%; margin-left: 1rem; display: flex; flex-direction: column">
                    <p>Hai attualmente <b>€<%=tessera.getSaldo()%></b> disponibili.</p>
                    <div style="flex: 1"></div>
                    <form action="BuyMenu" method="post">
                        <%
                            if (postiVuoti > 0) {
                        %>
                            <label for="postoMensa">
                                <input type="checkbox" id="postoMensa" name="postoMensa" value="true">
                                Desidero anche prenotare un posto
                            </label>
                        <%
                            }
                        %>
                        <input type="hidden" id="codiceMenu" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
                        <input type="submit" value="Conferma acquisto" style="margin-top: .25rem">
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
