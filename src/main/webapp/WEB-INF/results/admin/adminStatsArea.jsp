<%@ page import="model.utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.pietanza.Pietanza" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Statistiche acquisti</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        List<Pietanza> pietanze = (List<Pietanza>) request.getAttribute("pietanze");
        List<Integer> stats = (List<Integer>) request.getAttribute("statsMenu");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/tables.css">
    </header>

    <main class="display">
        <div class="box">
            <h1>Statistiche acquisti</h1><hr>
            <%
                if (pietanze == null || pietanze.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Non ci sono pietanze di cui visualizzare le statistiche</p>
            <%
            } else {
            %>
                <div class="table-container">
                    <table>
                        <thead>
                        <tr>
                            <th>Pietanza</th>
                            <th>Acquisti totali</th>
                            <th>Menù in cui è stata disponibile</th>
                        </tr>
                        </thead>

                        <tbody>
                        <%
                            for (int i=0; i < pietanze.size(); i++) {
                        %>
                        <tr>
                            <td><%=pietanze.get(i).getNome()%></td>
                            <td><%=pietanze.get(i).getNumeroAcquisti()%></td>
                            <td><%=stats.get(i)%></td>
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
