<%@ page import="model.Utente" %>
<%@ page import="model.Pietanza" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza dettagli pietanza</title>

    <script>
        function confirmDelete() {
            return confirm("Sicuro di voler eliminare la pietanza selezionata?");
        }
    </script>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        Pietanza pietanza = (Pietanza) request.getAttribute("pietanza");
    %>
    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <h2><%=pietanza.getNome()%></h2>
        <p><%=pietanza.getDescrizione()%></p>
        <p>Tipo: <%=pietanza.getTipo()%></p>
        <p><%=pietanza.getIngredienti()%></p> <br>

        <form action="DeletePietanza" method="post">
            <input type="hidden" name="nomePietanza" value="<%=pietanza.getNome()%>">
            <input type="submit" onclick="return confirmDelete()" value="Elimina pietanza">
        </form>
    </main>

    <footer></footer>
</body>
</html>
