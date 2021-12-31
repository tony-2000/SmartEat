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
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main style="display: flex; flex-direction: row; justify-content: center; align-items: center">
        <div style="display: flex; flex-direction: row">
            <img src="${pageContext.request.contextPath}/images/temp.jpg" alt="temp"
                 style="max-width: 23rem; border-radius: 50%; margin-right: 1rem">
            <div style="align-self: center; margin-left: 1rem">
                <h1><%=(String) session.getAttribute("nomeMensa")%></h1>
                <p>Sono attualmente disponibili <%=session.getAttribute("postiVuoti")%> posti.</p>
                <p>Apertura - <%=session.getAttribute("aperturaMensa")%></p>
                <p>Chiusura - <%=session.getAttribute("chiusuraMensa")%></p>
            </div>
        </div>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
