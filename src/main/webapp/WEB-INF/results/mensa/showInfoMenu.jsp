<%@ page import="model.menu.Menu" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="model.pietanza.Pietanza" %><%--
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
        Pietanza primo = (Pietanza) request.getAttribute("primo");
        Pietanza secondo = (Pietanza) request.getAttribute("secondo");
        Pietanza dessert = (Pietanza) request.getAttribute("dessert");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="display">
        <div class="box">
            <div style="flex: 1; display: flex; flex-direction: row; justify-content: center">
                <div style="width: 50%; display: flex; flex-direction: row; justify-content: center">
                    <img src="${pageContext.request.contextPath}/covers/<%=menu.getImmagine()%>" alt="cover"
                         style="flex: 1; height: 25rem; width: 5rem; object-fit: cover">
                </div>
                <div style="width: 50%; height: 100%; margin: 0 1rem; display: flex; flex-direction: column">
                    <h1><%=menu.getNome()%></h1>
                    <p>Prezzo: <b><%=menu.getPrezzo()%>€</b></p><hr style="width: 100%; color: black">
                    <p><%=menu.getDescrizione()%></p><hr style="width: 100%; color: black">

                    <div class="card">
                        <div class="content">
                            <img src="${pageContext.request.contextPath}/covers/<%=primo.getImmagine()%>" alt="cover">
                            <div class="container">
                                <h3><%=primo.getNome()%></h3>
                                <p><%=primo.getDescrizione()%></p>
                                <p><%=primo.getIngredienti()%></p>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="content">
                            <img src="${pageContext.request.contextPath}/covers/<%=secondo.getImmagine()%>" alt="cover">
                            <div class="container">
                                <h3><%=secondo.getNome()%></h3>
                                <p><%=secondo.getDescrizione()%></p>
                                <p><%=secondo.getIngredienti()%></p>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="content">
                            <img src="${pageContext.request.contextPath}/covers/<%=dessert.getImmagine()%>" alt="cover">
                            <div class="container">
                                <h3><%=dessert.getNome()%></h3>
                                <p><%=dessert.getDescrizione()%></p>
                                <p><%=dessert.getIngredienti()%></p>
                            </div>
                        </div>
                    </div><hr style="width: 100%; color: black">

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
