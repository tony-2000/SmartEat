<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 20/12/2021
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registrati</title>
    <script>
        function validateCF() {
            let cf = document.forms["signUp"]["CF"].value;
            let cfAlert = document.getElementById("CFAlert");
            if (cf === "") {
                cfAlert.innerHTML = "Il campo Codice Fiscale non può essere vuoto.";
                return false;
            } else if (cf.length !== 16) {
                cfAlert.innerHTML = "Il campo Codice Fiscale non può avere una lunghezza diversa da 16.";
            } else {
                cfAlert.innerHTML = "";
                return true;
            }
        }
        function validateNome() {
            let nome = document.forms["signUp"]["nome_utente"].value;
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
            let cognome = document.forms["signUp"]["cognome"].value;
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
            let ddn = document.forms["signUp"]["dataDiNascita"].value;
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
            let ldn = document.forms["signUp"]["luogoDiNascita"].value;
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
            let residenza = document.forms["signUp"]["residenza"].value;
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
        function validateEmail() {
            let email = document.forms["signUp"]["mail"].value;
            let emailAlert = document.getElementById("emailAlert");
            if (email === "") {
                emailAlert.innerHTML = "Il campo e-mail non può essere vuoto.";
                return false;
            } else if (email.length < 5) {
                emailAlert.innerHTML = "Il campo e-mail deve essere lungo almeno 5 caratteri.";
                return false;
            } else if (email.length > 35) {
                emailAlert.innerHTML = "Il campo e-mail deve essere lungo al massimo 35 caratteri.";
                return false;
            } else {
                emailAlert.innerHTML = "";
                return true;
            }
        }
        function validatePassword() {
            let password = document.forms["signUp"]["password"].value;
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
            let checkPassword = document.forms["signUp"]["passwordCheck"].value;
            let password = document.forms["signUp"]["password"].value;
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
            return (validateCF &&
                validateNome() &&
                validateCognome() &&
                validateDDN() &&
                validateLDN() &&
                validateResidenza() &&
                validateEmail() &&
                validatePassword() &&
                validateCheckPassword());
        }
    </script>
</head>
<body>
    <%@include file="WEB-INF/partials/mainImports.jsp"%>

    <header>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main>
        <h2>Registrati a SmartEat</h2>

        <form name="signUp" onsubmit="return validateForm()" action="SignUp" method="post">
            <label for="CF">Codice Fiscale</label><br>
            <input type="text" id="CF" name="CF" maxlength="16" required><a id="CFAlert" style="color: red"></a><br>

            <label for="nome_utente">Nome</label><br>
            <input type="text" id="nome_utente" name="nome_utente" maxlength="20" required><a id="nomeAlert" style="color: red"></a><br>

            <label for="cognome">Cognome</label><br>
            <input type="text" id="cognome" name="cognome" maxlength="20" required><a id="cognomeAlert" style="color: red"></a><br>

            <label for="gender">Sesso</label><br>
            <select id="gender" name="gender" required>
                <option value="M">Maschio</option>
                <option value="F">Femmina</option>
                <option value="N">Altro</option>
            </select><a id="genderAlert" style="color: red"></a><br>

            <label for="dataDiNascita">Data di nascita</label><br>
            <input type="date" id="dataDiNascita" name="dataDiNascita" required><a id="ddnAlert" style="color: red"></a><br>

            <label for="luogoDiNascita">Luogo di nascita</label><br>
            <input type="text" id="luogoDiNascita" name="luogoDiNascita" maxlength="25" required><a id="ldnAlert" style="color: red"></a><br>

            <label for="residenza">Luogo di residenza</label><br>
            <input type="text" id="residenza" name="residenza" maxlength="25" required><a id="residenzaAlert" style="color: red"></a><br>

            <label for="mail">Indirizzo e-mail</label><br>
            <input type="email" id="mail" name="mail" maxlength="35" required><a id="emailAlert" style="color: red"></a><br>

            <label for="password">Password</label><br>
            <input type="password" id="password" name="password" maxlength="16" required><a id="passwordAlert" style="color: red"></a><br>

            <label for="passwordCheck">Conferma password</label><br>
            <input type="password" id="passwordCheck" name="passwordCheck" maxlength="16" required><a id="checkPasswordAlert" style="color: red"></a><br><br>

            <input type="submit" value="Registrati">
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
