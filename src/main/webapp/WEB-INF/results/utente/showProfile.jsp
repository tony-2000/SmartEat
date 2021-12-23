<%@ page import="model.Utente" %>
<%@ page import="model.Tessera" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        Tessera tessera = (Tessera) request.getAttribute("tessera");
        System.out.println("jsp " + tessera.getSaldo()); //DEBUG
    %>
    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <div>
            <h1>Profilo Utente</h1>
            <ul>
                <li>Codice Fiscale <%=utente.getCF()%></li>
                <li>Nome <%=utente.getNome()%></li>
                <li>Cognome <%=utente.getCognome()%></li>
                <li>Sesso <%=utente.getSesso()%></li>
                <li>Data di Nascita <%=utente.getDataDiNascita()%></li>
                <li>Luogo di Nascita <%=utente.getLuogoDiNascita()%></li>
                <li>Indirizzo e-mail <%=utente.getEmail()%></li>
                <li>Residenza <%=utente.getResidenza()%></li>
                <li>Saldo <b>€<%=tessera.getSaldo()%></b></li>
                <li><a href="${pageContext.request.contextPath}/toUpdateProfile">Modifica profilo</a></li>
            </ul>
        </div>
    </main>

    <footer></footer>
</body>
</html>
