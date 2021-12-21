<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica il tuo profilo</title>
    <script>
        function validateNome() {
            let nome = document.forms["updateProfile"]["nome_utente"].value;
            let nomeAlert = document.getElementById("nomeAlert");
            if (nome === "") {
                nomeAlert.innerHTML = "Il campo nome non può essere vuoto.";
                return false;
            } else if (nome.length > 20) {
                nomeAlert.innerHTML = "Il campo nome non può essere più lungo di 20 caratteri.";
                return false;
            } else {
                nomeAlert.innerHTML = "";
                return true;
            }
        }
        function validateCognome() {
            let cognome = document.forms["updateProfile"]["cognome"].value;
            let cognomeAlert = document.getElementById("cognomeAlert");
            if (cognome === "") {
                cognomeAlert.innerHTML = "Il campo cognome non può essere vuoto.";
                return false;
            } else if (cognome.length > 20) {
                cognomeAlert.innerHTML = "Il campo cognome non può essere più lungo di 20 caratteri.";
                return false;
            } else {
                cognomeAlert.innerHTML = "";
                return true;
            }
        }
        function validateDDN () {
            let ddn = document.forms["updateProfile"]["dataDiNascita"].value;
            let ddnAlert = document.getElementById("ddnAlert");
            if (ddn.length !== 10) {
                ddnAlert.innerHTML = "Inserire una data valida.";
                return false;
            } else {
                ddnAlert.innerHTML = "";
                return true;
            }
        }
        function validateLDN() {
            let ldn = document.forms["updateProfile"]["luogoDiNascita"].value;
            let ldnAlert = document.getElementById("ldnAlert");
            if (ldn === "") {
                ldnAlert.innerHTML = "Il campo luogo di nascita non può essere vuoto.";
                return false;
            } else if (ldn.length > 25) {
                ldnAlert.innerHTML = "Il campo luogo di nascita non può essere più lungo di 25 caratteri.";
                return false;
            } else {
                ldnAlert.innerHTML = "";
                return true;
            }
        }
        function validateResidenza() {
            let residenza = document.forms["updateProfile"]["residenza"].value;
            let residenzaAlert = document.getElementById("residenzaAlert");
            if (residenza === "") {
                residenzaAlert.innerHTML = "Il campo residenza non può essere vuoto.";
                return false;
            } else if (residenza.length > 25) {
                residenzaAlert.innerHTML = "Il campo residenza non può essere più lungo di 25 caratteri.";
                return false;
            } else {
                residenzaAlert.innerHTML = "";
                return true;
            }
        }
        function validatePassword() {
            let password = document.forms["updateProfile"]["password"].value;
            let passwordAlert = document.getElementById("passwordAlert");
            if (password === "") {
                passwordAlert.innerHTML = "Il campo password non può essere vuoto.";
                return false;
            } else if (password.length < 8) {
                passwordAlert.innerHTML = "Il campo password deve essere lungo almeno 8 caratteri.";
                return false;
            } else if (password.length > 16) {
                passwordAlert.innerHTML = "Il campo password deve essere lungo al massimo 16 caratteri.";
                return false;
            } else {
                passwordAlert.innerHTML = "";
                return true;
            }
        }
        function validateCheckPassword() {
            let checkPassword = document.forms["updateProfile"]["passwordCheck"].value;
            let password = document.forms["updateProfile"]["password"].value;
            let checkPasswordAlert = document.getElementById("checkPasswordAlert");
            if (checkPassword !== password) {
                checkPasswordAlert.innerHTML = "I campi password e conferma password non corrispondono.";
                return false;
            } else if (checkPassword === "") {
                checkPasswordAlert.innerHTML = "Il campo conferma password non può essere vuoto.";
                return false;
            } else if (checkPassword.length < 8) {
                checkPasswordAlert.innerHTML = "Il campo conferma password deve essere lungo almeno 8 caratteri.";
                return false;
            } else if (checkPassword.length > 16) {
                checkPasswordAlert.innerHTML = "Il campo conferma password deve essere lungo al massimo 16 caratteri.";
                return false;
            } else {
                checkPasswordAlert.innerHTML = "";
                return true;
            }
        }

        function validateForm() {
            return (validateNome() &&
                validateCognome() &&
                validateDDN() &&
                validateLDN() &&
                validateResidenza() &&
                validatePassword() &&
                validateCheckPassword());
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
    </header> <br>

    <main>
        <div>
            <h1>Modifica Info Utente</h1>
            <!-- TODO aggiungere messaggio di errore -->
            <form name="updateProfile" onsubmit="return validateForm()" action="UpdateProfile" method="post">
                <label for="nome_utente">Nome</label><br>
                <input type="text" id="nome_utente" name="nome_utente" maxlength="20" value="<%=utente.getNome()%>" required><a id="nomeAlert" style="color: red"></a><br>

                <label for="cognome">Cognome</label><br>
                <input type="text" id="cognome" name="cognome" maxlength="20" value="<%=utente.getCognome()%>" required><a id="cognomeAlert" style="color: red"></a><br>

                <label for="gender">Sesso</label><br>
                <select id="gender" name="gender" required>
                    <option value="M">Maschio</option>
                    <option value="F">Femmina</option>
                    <option value="N">Altro</option>
                </select><a id="genderAlert" style="color: red"></a><br>

                <label for="dataDiNascita">Data di nascita</label><br>
                <input type="date" id="dataDiNascita" name="dataDiNascita" required><a id="ddnAlert" style="color: red"></a><br>

                <label for="luogoDiNascita">Luogo di nascita</label><br>
                <input type="text" id="luogoDiNascita" name="luogoDiNascita" maxlength="25" value="<%=utente.getLuogoDiNascita()%>" required><a id="ldnAlert" style="color: red"></a><br>

                <label for="residenza">Luogo di residenza</label><br>
                <input type="text" id="residenza" name="residenza" maxlength="25" value="<%=utente.getResidenza()%>" required><a id="residenzaAlert" style="color: red"></a><br>

                <label for="password">Password</label><br>
                <input type="password" id="password" name="password" maxlength="16" required><a id="passwordAlert" style="color: red"></a><br>

                <label for="passwordCheck">Conferma password</label><br>
                <input type="password" id="passwordCheck" name="passwordCheck" maxlength="16" required><a id="checkPasswordAlert" style="color: red"></a><br><br>

                <input type="submit" value="Conferma modifiche">
            </form>
        </div>
    </main>

    <footer></footer>
</body>
</html>
