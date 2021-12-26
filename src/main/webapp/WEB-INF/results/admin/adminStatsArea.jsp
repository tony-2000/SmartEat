<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Pietanza" %><%--
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

        List<Pietanza> pietanze = (List<Pietanza>) request.getAttribute("pietanze");
        List<Integer> stats = (List<Integer>) request.getAttribute("statsMenu");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <h1>Statistiche acquisti</h1>
        <%
            if (pietanze == null || pietanze.isEmpty()) {
        %>
            <p style="color: red">Non ci sono pietanze di cui visualizzare le statistiche</p>
        <%
            } else {
        %>
            <table>
                <thead>
                <tr>
                    <th>Pietanza</th>
                    <th>Acquisti totali</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (int i=0; i < pietanze.size(); i++) {

                %>
                    <tr>
                        <td><%=pietanze.get(i).getNome()%></td>
                        <td><%=stats.get(i)%></td>
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
