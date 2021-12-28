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
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header>

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <img src="${pageContext.request.contextPath}/images/temp.jpg" alt="temp" style="max-width: 30%; border-radius: 50%">
        <h1><%=(String) session.getAttribute("nomeMensa")%></h1>
        <p>Sono attualmente disponibili <%=Mensa.getPostiVuoti()%> posti.</p>
    </main>

    <footer></footer>
</body>
</html>
