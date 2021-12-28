<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi una pietanza</title>
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
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <h1>Aggiungi una pietanza</h1>
        <form action="AddPietanza" method="post">
            <label for="nome">Nome</label><br>
            <input type="text" name="nome" id="nome" maxlength="25" required><br>

            <label for="descrizione">Descrizione</label><br>
            <textarea name="descrizione" id="descrizione"
                      rows="5" cols="30" style="resize: none"
                      maxlength="300" required></textarea><br>

            <label for="tipo">Tipo</label>
            <select name="tipo" id="tipo">
                <option value="P">Primo</option>
                <option value="S">Secondo</option>
                <option value="D">Dessert</option>
            </select> <br>

            <label for="ingredienti">Ingredienti</label><br>
            <textarea name="ingredienti" id="ingredienti"
                      rows="5" cols="30" style="resize: none"
                      maxlength="300" required></textarea><br><br>

            <input type="hidden" name="immagine" id="immagine">

            <input type="submit" value="Aggiungi"><br>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
