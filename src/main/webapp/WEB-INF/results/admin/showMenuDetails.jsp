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
                    <p>Prezzo: <b><%=menu.getPrezzo()%>€</b></p>
                    <p><%=menu.getDescrizione()%></p>
                    <p><%=menu.getPrimo()%></p>
                    <p><%=menu.getSecondo()%></p>
                    <p><%=menu.getDessert()%></p>
                    <div style="flex: 1"></div>
                    <form action="ToggleMenu" method="post" style="margin-bottom: .5rem">
                        <input type="hidden" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
                        <input type="submit" value="<%=menu.isAvailable() ? "Disattiva" : "Attiva"%>">
                    </form>
                    <form action="DeleteMenu" method="post">
                        <input type="hidden" name="codiceMenu" value="<%=menu.getCodiceMenu()%>">
                        <input type="submit" value="Elimina menù" onclick="return confirmDelete()">
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
