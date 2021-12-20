<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 20/12/2021
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accedi</title>
</head>
<body>
    <h2>Effettua l'accesso a SmartEat</h2>
    <form action="${pageContext.request.contextPath}/Login" method="post">
        <label for="mail">Indirizzo e-mail</label><br>
        <input type="email" id="mail" name="mail" minlength="5" maxlength="35" required><br>

        <label for="password">Password</label><br>
        <input type="password" id="password" name="password" minlength="8" maxlength="16" required><br><br>

        <input type="submit" value="Login">
    </form>
    <p><a href="signUp.jsp">Oppure registrati</a></p>
</body>
</html>
