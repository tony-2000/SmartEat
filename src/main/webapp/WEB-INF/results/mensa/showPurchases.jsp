<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Acquisto" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 23/12/2021
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visualizza gli acquisti effettuati</title>
    <style>
        table {
            width: 80%;
            margin-left: auto;
            margin-right: auto;
            table-layout: fixed;
        }

        table, th, td {
            border:1px solid black;
            border-collapse: collapse;
        }
    </style>
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
    </header> <br>

    <main>
        <h1>Acquisti effettuati</h1>
        <%
            List<Acquisto> acquisti = (List<Acquisto>) request.getAttribute("listaAcquisti");
            if (acquisti == null || acquisti.isEmpty()) {
        %>
            <p style="color: red">Non hai effettuato nessun acquisto.</p>
        <%
            } else {
        %>
            <table>
                <caption>Acquisti effettuati</caption>

                <thead>
                <tr>
                    <th>Codice menù</th>
                    <th>Data di acquisto</th>
                    <th>Visualizza informazioni</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (Acquisto acquisto: acquisti) {
                %>
                    <tr>
                        <td><%=acquisto.getCodiceMenu()%></td>
                        <td><%=acquisto.getDataAcquisto()%></td>
                        <td>
                            <form action="ShowInfoPurchase" method="post">
                                <input type="hidden" name="dataAcquisto" value="<%=acquisto.getDataAcquisto()%>">
                                <input type="hidden" name="CF" value="<%=acquisto.getCF()%>">
                                <input type="hidden" name="codiceMenu" value="<%=acquisto.getCodiceMenu()%>">
                                <input type="submit" value="Info">
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
    </main>

    <footer></footer>
</body>
</html>
