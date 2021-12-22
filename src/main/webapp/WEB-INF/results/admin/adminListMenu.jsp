<%@ page import="model.Utente" %>
<%@ page import="model.Menu" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestisci i menù</title>
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
    <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
    </header> <br>

    <main>
        <h1>Gestisci i menù</h1>
        <%
            List<Menu> menus = (List<Menu>) request.getAttribute("listaMenu");
            if (menus == null || menus.isEmpty()) {
        %>
        <p style="color: red">Non ci sono menù disponibili.</p>
        <%
        } else {
        %>
        <table>
            <caption>Menù memorizzati</caption>

            <thead>
            <tr>
                <th>Codice</th>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Prezzo</th>
                <th>Disponibile</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (Menu m: menus) {
            %>
            <tr>
                <td><a href="${pageContext.request.contextPath}/ShowMenuDetails?codiceMenu=<%=m.getCodiceMenu()%>"><%=m.getCodiceMenu()%></a></td>
                <td><%=m.getNome()%></td>
                <td><%=m.getDescrizione()%></td>
                <td><%=m.getPrezzo()%>€</td>
                <td><%=m.isAvailable()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>
        <p><a href="${pageContext.request.contextPath}/toAddMenu">Aggiungi un menù.</a></p>
    </main>

    <footer></footer>
</body>
</html>
