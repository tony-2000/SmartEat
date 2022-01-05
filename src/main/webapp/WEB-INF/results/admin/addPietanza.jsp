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

    <script>
        function validateNome() {
            let nome = document.forms["addPietanza"]["nome"];
            let nomeAlert = document.getElementById("nomeAlert");
            let pattern = /^[a-zàèéìòù\d ]+/gi;
            if (nome.length < 1) {
                nomeAlert.innerHTML = "Il nome non può essere nullo.";
                return false;
            } else if (nome.length > 25) {
                nomeAlert.innerHTML = "Il nome non può avere una lunghezza superiore ai 25 caratteri.";
                return false;
            } else if (!pattern.test(nome)) {
                nomeAlert.innerHTML = "Il nome non rispetta il formato.";
                return false;
            } else {
                nomeAlert.innerHTML = "";
                return true;
            }
        }

        function validateDescrizione() {
            let descrizione = document.forms["addPietanza"]["descrizione"];
            let descrizioneAlert = document.getElementById("descrizioneAlert");
            if (descrizione.length < 1) {
                descrizioneAlert.innerHTML = "La descrizione non può essere nulla.";
                return false;
            } else if (descrizione.length > 300) {
                descrizioneAlert.innerHTML = "La descrizione non può avere una lunghezza superiore ai 300 caratteri.";
                return false;
            } else {
                descrizioneAlert.innerHTML = "";
                return true;
            }
        }

        function validateTipo() {
            let tipo = document.forms["addPietanza"]["tipo"];
            let tipoAlert = document.getElementById("tipoAlert");
            let pattern = /^[PSD]$/;
            if (tipo.length !== 1) {
                tipoAlert.innerHTML = "Il tipo non può essere nullo.";
                return false;
            } else if (!pattern.test(tipo)) {
                tipoAlert.innerHTML = "Il tipo non rispetta il formato."
                return false;
            } else {
                tipoAlert.innerHTML = "";
                return true;
            }
        }

        function validateIngredienti() {
            let ingredienti = document.forms["addPietanza"]["ingredienti"];
            let ingredientiAlert = document.getElementById("ingredientiAlert");
            if (ingredienti.length < 1) {
                ingredientiAlert.innerHTML = "L'elenco degli ingredienti non può essere nullo.";
                return false;
            } else if (ingredienti.length > 300) {
                ingredientiAlert.innerHTML = "L'elenco degli ingredienti non può avere una lunghezza superiore ai 300 caratteri.";
                return false;
            } else {
                ingredientiAlert.innerHTML = "";
                return true;
            }
        }

        function validateForm() {
            return (validateNome() &&
                validateDescrizione() &&
                validateTipo() &&
                validateIngredienti());
        }
    </script>
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

    <main class="field">
        <form action="AddPietanza" onsubmit="return validateForm()" id="addPietanza" method="post" enctype="multipart/form-data">
            <h2>Aggiungi una pietanza</h2><hr>

            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nome">
            <a id="nomeAlert" style="color: red"></a><br>

            <label for="descrizione">Descrizione</label>
            <textarea name="descrizione" id="descrizione"
                      rows="5" cols="30" style="resize: none"
                      maxlength="300"></textarea>
            <a id="descrizioneAlert" style="color: red"></a><br>

            <label for="tipo">Tipo</label>
            <select name="tipo" id="tipo">
                <option value="P">Primo</option>
                <option value="S">Secondo</option>
                <option value="D">Dessert</option>
            </select>
            <a id="tipoAlert" style="color: red"></a><br>

            <label for="ingredienti">Ingredienti</label>
            <textarea name="ingredienti" id="ingredienti"
                      rows="5" cols="30" style="resize: none"
                      maxlength="300"></textarea>
            <a id="ingredientiAlert" style="color: red"></a><br>

            <label for="image">Immagine</label>
            <input type="file" name="image" id="image" required>

            <input type="submit" value="Aggiungi"><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
