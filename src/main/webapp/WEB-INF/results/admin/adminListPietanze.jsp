<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Pietanza" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/12/2021
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Gestisci le pietanze</title>
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

    <main>
        <p style="color: blue"><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%></p>
        <h1>Gestisci le pietanze</h1>
        <%
            List<Pietanza> pietanze = (List<Pietanza>) request.getAttribute("listaPietanze");
            if (pietanze == null || pietanze.isEmpty()) {
        %>
            <p style="color: red">Non ci sono pietanze disponibili.</p>
        <%
            } else {
        %>
            <table>
                <caption>Pietanze memorizzate</caption>

                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Tipo</th>
                    <th>Ingredienti</th>
                    <th>Informazioni</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (Pietanza pietanza: pietanze) {
                %>
                    <tr>
                        <td><%=pietanza.getNome()%></td>
                        <td><%=pietanza.getTipo()%></td>
                        <td><%=pietanza.getIngredienti()%></td>
                        <td>
                            <form action="ShowPietanzaDetails" method="post">
                                <input type="hidden" name="nomePietanza" value="<%=pietanza.getNome()%>">
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

        <p><a href="${pageContext.request.contextPath}/toAddPietanza">Aggiungi una pietanza.</a></p>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
