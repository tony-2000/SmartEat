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

    <main class="display">
        <div class="box">
            <h1 style="margin-bottom: 1rem"><%=menu.getNome()%></h1>
            <div style="flex: 1; display: flex; flex-direction: row; justify-content: center; align-items: center">
                <div style="width: 50%; display: flex; flex-direction: row; justify-content: center">
                    <img src="${pageContext.request.contextPath}/covers/<%=menu.getImmagine()%>" alt="cover"
                         style="flex: 1; height: 20rem; object-fit: cover">
                </div>
                <div style="width: 50%; height: 100%; margin: 0 1rem; display: flex; flex-direction: column">
                    <p><b><%=menu.getPrezzo()%>€</b></p>
                    <p><%=menu.getDescrizione()%></p>
                    <p><%=menu.getPrimo()%></p>
                    <p><%=menu.getSecondo()%></p>
                    <p><%=menu.getDessert()%></p>
                    <div style="flex: 1"></div>
                    <form action="toBuyMenu" method="post">
                        <input type="hidden" id="codiceMenu" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
                        <input type="submit" value="Acquista">
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
