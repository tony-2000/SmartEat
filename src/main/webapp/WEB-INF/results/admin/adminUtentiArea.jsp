<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Gestione utenti</title>

    <style>
        table {
            width: 95%;
            margin-left: auto;
            margin-right: auto;
        }

        table, th, td {
            border:1px solid black;
            border-collapse: collapse;
        }
    </style>

    <script>
        function confirmDelete() {
            return confirm("Sicuro di voler eliminare l'utente selezionato?");
        }
    </script>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        List<Utente> utenti = (List<Utente>) request.getAttribute("listaUtenti");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <h1>Gestione utenti</h1>
        <%
            if (utenti == null || utenti.isEmpty()) {
        %>
            <p style="color: red">Si è verificato un errore nel caricamento degli utenti. Riprovare.</p>
        <%
            } else {
        %>
            <table>
                <caption>Utenti memorizzati</caption>

                <thead>
                <tr>
                    <th>CF</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Sesso</th>
                    <th>Data di nascita</th>
                    <th>Luogo di nascita</th>
                    <th>Residenza</th>
                    <th>E-Mail</th>
                    <th>Admin</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (Utente user: utenti) {
                %>
                <tr>
                    <td><%=user.getCF()%></td>
                    <td><%=user.getNome()%></td>
                    <td><%=user.getCognome()%></td>
                    <td><%=user.getSesso()%></td>
                    <td><%=user.getDataDiNascita()%></td>
                    <td><%=user.getLuogoDiNascita()%></td>
                    <td><%=user.getResidenza()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.isAmministratore().isAdmin() ? "Sì" : "No"%></td>
                    <td>
                        <form action="DeleteUtente" method="post">
                            <input type="hidden" name="CF" value="<%=user.getCF()%>">
                            <input type="submit" onclick="return confirmDelete()" value="Elimina">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        <%
            }
        %>
        <p><a href="${pageContext.request.contextPath}/toAcceptUtente">Accettazione utenti.</a></p>
    </main>

    <footer></footer>
</body>
</html>
