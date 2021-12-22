<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi un menù</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    %>
    <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <h1>Aggiungi un menù</h1>
        <form action="AddMenu" method="post">
            <label for="nome">Nome</label><br>
            <input type="text" name="nome" id="nome"><br>

            <label for="primo">Primo</label><br>
            <input type="text" name="primo" id="primo"><br>

            <label for="secondo">Secondo</label><br>
            <input type="text" name="secondo" id="secondo"><br>

            <label for="dessert">Dessert</label><br>
            <input type="text" name="dessert" id="dessert"><br>

            <label for="descrizione">Descrizione</label><br>
            <input type="text" name="descrizione" id="descrizione"><br>

            <input type="hidden" name="immagine" id="immagine" value=""><br>

            <label for="prezzo">Prezzo</label><br>
            <input type="number" name="prezzo" id="prezzo"><br>

            <input type="submit" value="Crea">
        </form>
    </main>

    <footer></footer>
</body>
</html>
