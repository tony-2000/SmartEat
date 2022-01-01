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
                if (acquisti == null || acquisti.isEmpty()) {
            %>
                <p style="color: red; text-align: center">Non hai effettuato nessun acquisto.</p>
            <%
            } else {
            %>
                <%
                    for (Acquisto acquisto: acquisti) {
                %>
            <div class="card">
                <div class="content" style="height: 5rem">
                    <div class="container">
                        <p>Menù n°<b><%=acquisto.getCodiceMenu()%></b></p>
                        <p>Acquistato il <%=acquisto.getDataAcquisto()%></p>
                    </div>

                    <div class="price" style="background: none">
                        <form action="ShowInfoPurchase" method="post">
                            <input type="hidden" name="dataAcquisto" value="<%=acquisto.getDataAcquisto()%>">
                            <input type="hidden" name="CF" value="<%=acquisto.getCF()%>">
                            <input type="hidden" name="codiceMenu" value="<%=acquisto.getCodiceMenu()%>">
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
