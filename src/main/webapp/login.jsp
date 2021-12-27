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
                return true;
            }
        }
        function validatePassword() {
            let password = document.forms["login"]["password"].value;
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

    <h2>Effettua l'accesso a SmartEat</h2>

    <%
        String message = (String) request.getAttribute("logError");
        if (message != null && message.length() > 0) {
    %>
        <p style="color: red"><%=message%></p>
    <%
        }
        message = (String) request.getAttribute("message");
        if (message != null && message.length() > 0) {
    %>
    <p style="color: green"><%=message%></p>
    <%
        }
    %>

    <form name="login" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/Login" method="post">
        <label for="mail">Indirizzo e-mail</label><br>
        <input type="email" id="mail" name="mail" maxlength="35" required><a id="emailAlert" style="color: red"></a><br>

        <label for="password">Password</label><br>
        <input type="password" id="password" name="password" maxlength="16" required><a id="passwordAlert" style="color: red"></a><br><br>

        <input type="submit" value="Login">
    </form>
    <p><a href="${pageContext.request.contextPath}/toSignUp">Oppure registrati</a></p>
</body>
</html>
