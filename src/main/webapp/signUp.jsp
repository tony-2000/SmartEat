<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 20/12/2021
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrati</title>
</head>
<body>
    <h2>Registrati a SmartEat</h2>
    <form action="${pageContext.request.contextPath}/SignUp" method="post">
        <label for="CF">Codice Fiscale</label><br>
        <input type="text" id="CF" name="CF" minlength="16" maxlength="16"><br>

        <label for="nome_utente">Nome</label><br>
        <input type="text" id="nome_utente" name="nome_utente" minlength="1" maxlength="20"><br>

        <label for="cognome">Cognome</label><br>
        <input type="text" id="cognome" name="cognome" minlength="1" maxlength="20"><br>

        <label for="gender">Sesso</label><br>
        <select id="gender" name="gender">
            <option value="M">Maschio</option>
            <option value="F">Femmina</option>
            <option value="N">Altro</option>
        </select><br>

        <label for="dataDiNascita">Data di nascita</label><br>
        <input type="date" id="dataDiNascita" name="dataDiNascita"><br>

        <label for="luogoDiNascita">Luogo di nascita</label><br>
        <input type="text" id="luogoDiNascita" name="luogoDiNascita" minlength="1" maxlength="25"><br>

        <label for="residenza">Luogo di residenza</label><br>
        <input type="text" id="residenza" name="residenza" minlength="1" maxlength="25"><br>

        <label for="mail">Indirizzo e-mail</label><br>
        <input type="email" id="mail" name="mail" minlength="5" maxlength="35"><br>

        <label for="password">Password</label><br>
        <input type="password" id="password" name="password" minlength="8" maxlength="16"><br>

        <label for="passwordCheck">Conferma password</label><br>
        <input type="password" id="passwordCheck" name="passwordCheck" minlength="8" maxlength="16"><br><br>

        <input type="submit" value="Registrati">
    </form>
</body>
</html>
