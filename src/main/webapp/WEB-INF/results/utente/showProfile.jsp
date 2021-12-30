<%@ page import="model.Utente" %>
<%@ page import="model.Tessera" %><%--
  Created by IntelpJ IDEA.
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
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="field">
        <div class="profile">
            <h2>Profilo Utente</h2>

            <table>
                <tbody>
                    <tr>
                        <td>Codice Fiscale</td>
                        <td><%=utente.getCF()%></td>
                    </tr>

                    <tr>
                        <td>Nome</td>
                        <td><%=utente.getNome()%></td>
                    </tr>

                    <tr>
                        <td>Cognome</td>
                        <td><%=utente.getCognome()%></td>
                    </tr>

                    <tr>
                        <td>Sesso</td>
                        <td><%=utente.getSesso()%></td>
                    </tr>

                    <tr>
                        <td>Data di Nascita</td>
                        <td><%=utente.getDataDiNascita()%></td>
                    </tr>

                    <tr>
                        <td>Luogo di Nascita</td>
                        <td><%=utente.getLuogoDiNascita()%></td>
                    </tr>

                    <tr>
                        <td>Indirizzo e-mail</td>
                        <td><%=utente.getEmail()%></td>
                    </tr>

                    <tr>
                        <td>Residenza</td>
                        <td><%=utente.getResidenza()%></td>
                    </tr>

                    <tr>
                        <td>Saldo</td>
                        <td><b>€<%=tessera.getSaldo()%></b></td>
                    </tr>
                </tbody>
            </table>

            <p><a href="${pageContext.request.contextPath}/toUpdateProfile">Modifica profilo</a></p>
        </div>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
