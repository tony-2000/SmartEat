<%@ page import="model.utente.Utente" %>
<%@ page import="model.menu.Menu" %>
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
            <h1>Gestisci i menù</h1><hr>
            <form action="${pageContext.request.contextPath}/toAddMenu" method="get">
                <input type="submit" value="Aggiungi un menù">
            </form>
            <%
                List<Menu> menus = (List<Menu>) request.getAttribute("listaMenu");
                if (menus == null || menus.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Non ci sono menù disponibili.</p>
            <%
            } else {
            %>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Codice</th>
                        <th>Nome</th>
                        <th>Descrizione</th>
                        <th>Prezzo</th>
                        <th>Disponibile</th>
                        <th>Informazioni</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%
                        for (Menu m: menus) {
                    %>
                    <tr>
                        <td><%=m.getCodiceMenu()%></td>
                        <td><%=m.getNome()%></td>
                        <td><%=m.getDescrizione()%></td>
                        <td><%=m.getPrezzo()%>€</td>
                        <td><%=m.isAvailable() ? "Sì" : "No"%></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/ShowMenuDetails?codiceMenu=<%=m.getCodiceMenu()%>"
                                  method="get">
                                <input type="hidden" name="codiceMenu" value="<%=m.getCodiceMenu()%>">
                                <input type="submit" value="Info">
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
