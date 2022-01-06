<%@ page import="model.Utente" %>
<%@ page import="model.Mensa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione mensa</title>

    <script>
        function validatePosti() {
            let posti = document.forms["updateMensa"]["postiDisponibili"].value;
            let postiAlert = document.getElementById("postiAlert");
            let pattern = /^\d+$/;
            if (posti.length < 1) {
                postiAlert.innerHTML = "Il campo posti disponibili non può essere vuoto.";
                return false;
            } else if (posti.length > 4) {
                postiAlert.innerHTML = "Il campo posti disponibili non può avere una lunghezza superiore alle 4 cifre.";
                return false;
            } else if (!pattern.test(posti)) {
                postiAlert.innerHTML = "Il campo posti disponibili non rispetta il formato.";
                return false;
            } else {
                postiAlert.innerHTML = "";
                return true;
            }
        }

        function validateApertura() {
            let apertura = document.forms["updateMensa"]["orarioApertura"].value;
            let aperturaAlert = document.getElementById("aperturaAlert");
            let pattern = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/;
            if (apertura.length < 1) {
                aperturaAlert.innerHTML = "Il campo orario di apertura non può essere vuoto.";
                return false;
            } else if (apertura.length > 8) {
                aperturaAlert.innerHTML = "Il campo orario di apertura non può avere una lunghezza superiore alle 8 cifre.";
                return false;
            } else if (!pattern.test(apertura)) {
                aperturaAlert.innerHTML = "Il campo orario di apertura non rispetta il formato.";
                return false;
            } else {
                aperturaAlert.innerHTML = "";
                return true;
            }
        }

        function validateChiusura() {
            let chiusura = document.forms["updateMensa"]["orarioChiusura"].value;
            let chiusuraAlert = document.getElementById("chiusuraAlert");
            let pattern = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/;
            if (chiusura.length < 1) {
                chiusuraAlert.innerHTML = "Il campo orario di chiusura non può essere vuoto.";
                return false;
            } else if (chiusura.length > 8) {
                chiusuraAlert.innerHTML = "Il campo orario di chiusura non può avere una lunghezza superiore alle 8 cifre.";
                return false;
            } else if (!pattern.test(chiusura)) {
                chiusuraAlert.innerHTML = "Il campo orario di chiusura non rispetta il formato.";
                return false;
            } else {
                chiusuraAlert.innerHTML = "";
                return true;
            }
        }

        function validateForm() {
            return (validatePosti() &&
                validateApertura() &&
                validateChiusura());
        }
    </script>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        List<String> mensa = (List<String>) request.getAttribute("mensa");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="field">
        <form name="updateMensa" action="UpdateMensa" onsubmit="return validateForm()" method="post">
            <h2><%=mensa.get(0)%></h2><hr>

            <label for="postiDisponibili">Numero posti disponibili</label>
            <input type="number" id="postiDisponibili" name="postiDisponibili" value="<%=mensa.get(1)%>">
            <a id="postiAlert" style="color: red"></a><br>

            <label for="orarioApertura">Orario di apertura</label>
            <input type="time" id="orarioApertura" step="1" name="orarioApertura" value="<%=mensa.get(2)%>">
            <a id="aperturaAlert" style="color: red"></a><br>

            <label for="orarioChiusura">Orario di chiusura</label>
            <input type="time" id="orarioChiusura" step="1" name="orarioChiusura" value="<%=mensa.get(3)%>">
            <a id="chiusuraAlert" style="color: red"></a><br>

            <input type="submit" value="Conferma modifiche"><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
