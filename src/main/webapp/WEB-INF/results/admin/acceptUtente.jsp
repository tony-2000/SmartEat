<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accetazione utenti</title>

    <script>
        function confirmDelete() {
            return confirm("Sicuro di voler rifiutare l'utente selezionato?");
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
        <%@include file="/WEB-INF/partials/messages.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/tables.css">
    </header>

    <main class="display">
        <div class="box">
            <h1>Gestione utenti</h1><hr>
            <%
                if (utenti == null || utenti.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Non ci sono utenti da accettare.</p>
            <%
                } else {
            %>
                <div class="table-container">
                    <table>
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
                                <form action="AcceptUtente" method="post">
                                    <input type="hidden" name="CFUser" value="<%=user.getCF()%>">
                                    <input type="hidden" name="accept" value="true">
                                    <input type="submit" value="Accetta">
                                </form>
                            </td>
                            <td>
                                <form action="AcceptUtente" method="post">
                                    <input type="hidden" name="CFUser" value="<%=user.getCF()%>">
                                    <input type="hidden" name="accept" value="false">
                                    <input type="submit" onclick="return confirmDelete()" value="Rifiuta">
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
