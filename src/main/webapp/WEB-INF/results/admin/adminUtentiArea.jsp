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
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/tables.css">
    </header>

    <main class="display">
        <div class="box">
            <h1>Gestione utenti</h1><hr>
            <form action="${pageContext.request.contextPath}/toAcceptUtente" method="get">
                <input type="submit" value="Accettazione utenti">
            </form>
            <%
                List<Utente> utenti = (List<Utente>) request.getAttribute("listaUtenti");
                if (utenti == null || utenti.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Si è verificato un errore nel caricamento degli utenti. Riprovare.</p>
            <%
                } else {
            %>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>Codice Fiscale</th>
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
                </div>
            <%
                }
            %>
        </div>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
