<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Menu" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Acquista un menù</title>
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
        <h1>Acquista un menù</h1>
        <%
            List<Menu> menus = (List<Menu>) request.getAttribute("listaMenu");
            if (menus == null || menus.isEmpty()) {
        %>
            <p style="color: red">Non ci sono menù disponibili.</p>
        <%
            } else {
        %>
            <table>
                <caption>Menù acquistabili</caption>

                <thead>
                <tr>
                    <th>Nome del menù</th>
                    <th>Descrizione</th>
                    <th>Prezzo</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (Menu m: menus) {
                %>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/ShowInfoMenu?codiceMenu=<%=m.getCodiceMenu()%>"><%=m.getNome()%></a></td>
                        <td><%=m.getDescrizione()%></td>
                        <td><%=m.getPrezzo()%>€</td>
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
