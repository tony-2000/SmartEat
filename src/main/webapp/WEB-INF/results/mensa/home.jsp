<%@ page import="model.Utente" %>
<%@ page import="model.Mensa" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <h1><%=(String) session.getAttribute("nomeMensa")%></h1>
        <p>Sono attualmente disponibili <%=Mensa.getPostiVuoti()%> posti.</p>
    </main>

    <footer></footer>
</body>
</html>
