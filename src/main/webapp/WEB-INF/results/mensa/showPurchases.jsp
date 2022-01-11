<%@ page import="model.utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.acquisto.Acquisto" %>
<%@ page import="java.util.Collections" %>
<%@ page import="model.menu.Menu" %>

<%--
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
            <h1>Acquisti effettuati</h1>
            <%
                List<Acquisto> acquisti = (List<Acquisto>) request.getAttribute("listaAcquisti");
                List<Menu> menus = (List<Menu>) request.getAttribute("listaMenu");
                if (acquisti == null || acquisti.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Non hai effettuato nessun acquisto.</p>
            <%
                } else {
            %>
                <%
                    Collections.reverse(acquisti);
                    Collections.reverse(menus);
                    for (int i=0; i < acquisti.size(); i++) {
                %>
                    <div class="card">
                        <div class="content" style="height: 5rem">
                            <img src="${pageContext.request.contextPath}/covers/<%=menus.get(i).getImmagine()%>" alt="cover">
                            <div class="container">
                                <h3><%=menus.get(i).getNome()%></h3>
                                <p>Acquistato il <%=acquisti.get(i).getDataAcquisto()%></p>
                            </div>
                            <div class="price" style="background: none">
                                <form action="ShowInfoPurchase" method="post">
                                    <input type="hidden" name="dataAcquisto" value="<%=acquisti.get(i).getDataAcquisto()%>">
                                    <input type="hidden" name="CF" value="<%=acquisti.get(i).getCF()%>">
                                    <input type="hidden" name="codiceMenu" value="<%=acquisti.get(i).getCodiceMenu()%>">
                                    <input type="submit" value="Info" style="padding: 1rem 2rem">
                                </form>
                            </div>
                        </div>
                    </div>
                <%
                    }
                %>
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
