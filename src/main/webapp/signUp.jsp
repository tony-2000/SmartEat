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
            let pattern = /[A-Z]{6}\d{2}[ABCDEHLMPRST]{1}\d{2}[A-Z\d]{4}[A-Z]{1}/;
            if (cf.length !== 16) {
                cfAlert.innerHTML = "Il campo Codice Fiscale non può avere una lunghezza diversa da 16.";
                return false;
            } else if (!pattern.test(cf)) {
                cfAlert.innerHTML = "Il Codice Fiscale ha un formato scorretto.";
                return false;
            } else {
                cfAlert.innerHTML = "";
                return true;
            }
        }

        function validateNome() {
            let nome = document.forms["signUp"]["nome_utente"].value;
            let nomeAlert = document.getElementById("nomeAlert");
            let pattern = /^[a-z'àèéìòù ]+$/gi;
            if (nome.length < 1) {
                nomeAlert.innerHTML = "Il campo nome non può essere vuoto.";
                return false;
            } else if (nome.length > 20) {
                nomeAlert.innerHTML = "Il campo nome non può essere più lungo di 20 caratteri.";
                return false;
            } else if (!pattern.test(nome)) {
                nomeAlert.innerHTML = "Il nome inserito ha un formato scorretto.";
                return false;
            } else {
                nomeAlert.innerHTML = "";
                return true;
            }
        }

        function validateCognome() {
            let cognome = document.forms["signUp"]["cognome"].value;
            let cognomeAlert = document.getElementById("cognomeAlert");
            let pattern = /^[a-z'àèéìòù ]+$/gi;
            if (cognome.length < 1) {
                cognomeAlert.innerHTML = "Il campo cognome non può essere vuoto.";
                return false;
            } else if (cognome.length > 20) {
                cognomeAlert.innerHTML = "Il campo cognome non può essere più lungo di 20 caratteri.";
                return false;
            } else if (!pattern.test(cognome)) {
                cognomeAlert.innerHTML = "Il cognome inserito ha un formato scorretto.";
                return false;
            } else {
                cognomeAlert.innerHTML = "";
                return true;
            }
        }

        function validateSesso() {
            let gender = document.forms["signUp"]["gender"].value;
            let genderAlert = document.getElementById("genderAlert");
            let pattern = /[MFN]/;
            if (gender.length !== 1) {
                genderAlert.innerHTML = "Il sesso inserito non è valido.";
                return false;
            } else if (!pattern.test(gender)) {
                genderAlert.innerHTML = "Il sesso inserito non è valido.";
                return false;
            } else {
                genderAlert.innerHTML = "";
                return true;
            }
        }

        function validateDDN () {
            let ddn = document.forms["signUp"]["dataDiNascita"].value;
            let ddnAlert = document.getElementById("ddnAlert");
            let pattern = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/;
            if (ddn.length !== 10) {
                ddnAlert.innerHTML = "Inserire una data valida.";
                return false;
            } else if (!pattern.test(ddn)) {
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
            let pattern = /^[a-z'àèéìòù ]+$/gi;
            if (ldn === "") {
                ldnAlert.innerHTML = "Il campo luogo di nascita non può essere vuoto.";
                return false;
            } else if (ldn.length > 25) {
                ldnAlert.innerHTML = "Il campo luogo di nascita non può essere più lungo di 25 caratteri.";
                return false;
            } else if (!pattern.test(ldn)) {
                ldnAlert.innerHTML = "Il luogo di nascita inserito non è valido.";
                return false;
            } else {
                ldnAlert.innerHTML = "";
                return true;
            }
        }

        function validateResidenza() {
            let residenza = document.forms["signUp"]["residenza"].value;
            let residenzaAlert = document.getElementById("residenzaAlert");
            let pattern = /^[a-z'àèéìòù ]+$/gi;
            if (residenza === "") {
                residenzaAlert.innerHTML = "Il campo residenza non può essere vuoto.";
                return false;
            } else if (residenza.length > 25) {
                residenzaAlert.innerHTML = "Il campo residenza non può essere più lungo di 25 caratteri.";
                return false;
            } else if (!pattern.test(residenza)) {
                residenzaAlert.innerHTML = "Il luogo di residenza inserito non è valido.";
                return false;
            } else {
                residenzaAlert.innerHTML = "";
                return true;
            }
        }

        function validateEmail() {
            let email = document.forms["signUp"]["mail"].value;
            let emailAlert = document.getElementById("emailAlert");
            let pattern = /([A-Za-z0-9._%+-]+)@([A-Za-z]+)\.([A-Za-z]+)/g;
            if (email === "") {
                emailAlert.innerHTML = "Il campo e-mail non può essere vuoto.";
                return false;
            } else if (email.length < 5) {
                emailAlert.innerHTML = "Il campo e-mail deve essere lungo almeno 5 caratteri.";
                return false;
            } else if (email.length > 35) {
                emailAlert.innerHTML = "Il campo e-mail deve essere lungo al massimo 35 caratteri.";
                return false;
            } else if (!pattern.test(email)) {
                emailAlert.innerHTML = "L'email inserita non è valida.";
                return false;
            } else {
                emailAlert.innerHTML = "";
                return true;
            }
        }

        function validatePassword() {
            let password = document.forms["signUp"]["password"].value;
            let passwordAlert = document.getElementById("passwordAlert");
            let pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[._%+-])[a-zA-Z._%+\-\d]+/g;
            if (password === "") {
                passwordAlert.innerHTML = "Il campo password non può essere vuoto.";
                return false;
            } else if (password.length < 8) {
                passwordAlert.innerHTML = "Il campo password deve essere lungo almeno 8 caratteri.";
                return false;
            } else if (password.length > 16) {
                passwordAlert.innerHTML = "Il campo password deve essere lungo al massimo 16 caratteri.";
                return false;
            } else if (!pattern.test(password)) {
                passwordAlert.innerHTML = "La password inserita non va bene. " +
                    "Deve avere almeno un carattere minuscolo, uno maiuscolo, " +
                    "un numero e un carattere speciale.";
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
            let pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[._%+-])[a-zA-Z._%+\-\d]+/g;
            if (checkPassword === "") {
                checkPasswordAlert.innerHTML = "Il campo conferma password non può essere vuoto.";
                return false;
            } else if (checkPassword.length < 8) {
                checkPasswordAlert.innerHTML = "Il campo conferma password deve essere lungo almeno 8 caratteri.";
                return false;
            } else if (checkPassword.length > 16) {
                checkPasswordAlert.innerHTML = "Il campo conferma password deve essere lungo al massimo 16 caratteri.";
                return false;
            } else if (!pattern.test(checkPassword)) {
                checkPasswordAlert.innerHTML = "La password inserita non va bene. " +
                    "Deve avere almeno un carattere minuscolo, uno maiuscolo, " +
                    "un numero e un carattere speciale.";
                return false;
            } else if (checkPassword !== password) {
                checkPasswordAlert.innerHTML = "I campi password e conferma password non corrispondono.";
                return false;
            } else {
                checkPasswordAlert.innerHTML = "";
                return true;
            }
        }

        function validateForm() {
            return (validateCF() &&
                validateNome() &&
                validateCognome() &&
                validateSesso() &&
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

    <main class="field">
        <form name="signUp" onsubmit="return validateForm()" action="SignUp" method="post">
            <h2>Registrati a SmartEat</h2><hr>

            <label for="CF">Codice Fiscale</label>
            <input type="text" id="CF" name="CF"><a id="CFAlert" style="color: red"></a><br>

            <label for="nome_utente">Nome</label>
            <input type="text" id="nome_utente" name="nome_utente"><a id="nomeAlert" style="color: red"></a><br>

            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome"><a id="cognomeAlert" style="color: red"></a><br>

            <label for="gender">Sesso</label>
            <select id="gender" name="gender">
                <option value="" hidden selected></option>
                <option value="M">Maschio</option>
                <option value="F">Femmina</option>
                <option value="N">Altro</option>
            </select><a id="genderAlert" style="color: red"></a><br>

            <label for="dataDiNascita">Data di nascita</label>
            <input type="date" id="dataDiNascita" name="dataDiNascita"><a id="ddnAlert" style="color: red"></a><br>

            <label for="luogoDiNascita">Luogo di nascita</label>
            <input type="text" id="luogoDiNascita" name="luogoDiNascita"><a id="ldnAlert" style="color: red"></a><br>

            <label for="residenza">Luogo di residenza</label>
            <input type="text" id="residenza" name="residenza"><a id="residenzaAlert" style="color: red"></a><br>

            <label for="mail">Indirizzo e-mail</label>
            <input type="text" id="mail" name="mail"><a id="emailAlert" style="color: red"></a><br>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"><a id="passwordAlert" style="color: red"></a><br>

            <label for="passwordCheck">Conferma password</label>
            <input type="password" id="passwordCheck" name="passwordCheck"><a id="checkPasswordAlert" style="color: red"></a><br>

            <input type="submit" value="Registrati"><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
