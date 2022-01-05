<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 20/12/2021
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Accedi</title>
    <script>
        function validateEmail() {
            let email = document.forms["login"]["mail"].value;
            let emailAlert = document.getElementById("emailAlert");
            let pattern = /^([A-Za-z0-9._%+-]+)@([A-Za-z]+)\.([A-Za-z]+)/g;
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
            let password = document.forms["login"]["password"].value;
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

        function validateForm() {
            return (validateEmail() && validatePassword());
        }
    </script>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente != null)  {
            response.sendRedirect(request.getContextPath() + "/toHome");
        }
    %>
    <%@include file="WEB-INF/partials/mainImports.jsp"%>

    <header>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="field">
        <form name="login" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/Login" method="post">
            <h2>Effettua l'accesso a SmartEat</h2><hr>

            <label for="mail">Indirizzo e-mail</label>
            <input type="text" id="mail" name="mail"><a id="emailAlert" style="color: red"></a><br>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"><a id="passwordAlert" style="color: red"></a><br>

            <input type="submit" value="Login"><hr>

            <p><a href="${pageContext.request.contextPath}/toSignUp">Oppure registrati</a></p>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
