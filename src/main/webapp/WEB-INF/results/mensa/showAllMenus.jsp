<%@ page import="model.utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.menu.Menu" %><%--
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

        <style>
            .card:hover {
                box-shadow: 10px 10px 20px grey;
                outline: .3rem solid var(--main-color);
            }
        </style>
    </header>

    <main class="display">
        <div class="box">
            <h1>Acquista un menù</h1>
            <%
                List<Menu> menus = (List<Menu>) request.getAttribute("listaMenu");
                if (menus == null || menus.isEmpty()) {
            %>
                <p style="color: red">Non ci sono menù disponibili.</p>
            <%
            } else {
            %>
                <%
                    for (Menu menu: menus) {
                %>
                    <div class="card">
                        <a href="${pageContext.request.contextPath}/ShowInfoMenu?codiceMenu=<%=menu.getCodiceMenu()%>">
                            <div class="content">
                                <img src="${pageContext.request.contextPath}/covers/<%=menu.getImmagine()%>" alt="cover">
                                <div class="container">
                                    <h3><%=menu.getNome()%></h3>
                                    <p><%=menu.getDescrizione()%></p>
                                    <p><%=menu.getPrimo()%>, <%=menu.getSecondo()%>, <%=menu.getDessert()%></p>
                                </div>
                                <div class="price">
                                    <h2><%=menu.getPrezzo()%>€</h2>
                                </div>
                            </div>
                        </a>
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
