<%@ page import="model.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Pietanza" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 22/12/2021
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi un menù</title>
</head>
<body>
    <%
        Utente utente = (Utente) session.getAttribute("utenteSessione");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

        List<Pietanza> pietanze = (List<Pietanza>) request.getAttribute("pietanze");
    %>

    <header>
        <%@include file="/WEB-INF/partials/navbar.jsp"%>
        <%@include file="/WEB-INF/partials/messages.jsp"%>
    </header>

    <main class="field">
        <form action="AddMenu" method="post">
            <h2>Aggiungi un menù</h2><hr>
            <%
                if (pietanze == null || pietanze.isEmpty()) {
            %>
            <p style="color: red">Non ci sono pietanze da aggiungere al menù. Riprovare.</p>
            <%
                } else {
            %>
            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nome" maxlength="25" required><br>

            <label for="primo">Primo</label>
            <select name="primo" id="primo" required>
                <%
                    for (Pietanza p: pietanze) {
                        if (p.getTipo() == 'P') {
                %>
                <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                <%
                        }
                    }
                %>
            </select><br>

            <label for="secondo">Secondo</label>
            <select name="secondo" id="secondo" required>
                <%
                    for (Pietanza p: pietanze) {
                        if (p.getTipo() == 'S') {
                %>
                <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                <%
                        }
                    }
                %>
            </select><br>

            <label for="dessert">Dessert</label>
            <select name="dessert" id="dessert" required>
                <%
                    for (Pietanza p: pietanze) {
                        if (p.getTipo() == 'D') {
                %>
                <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                <%
                        }
                    }
                %>
            </select><br>

            <label for="descrizione">Descrizione</label>
            <textarea name="descrizione" id="descrizione"
                      rows="5" cols="30" style="resize: none"
                      maxlength="500" required></textarea><br>

            <input type="hidden" name="immagine" id="immagine" value="">

            <label for="prezzo">Prezzo</label>
            <input type="number" name="prezzo" id="prezzo" step=".25" required><br><br>

            <input type="submit" value="Crea">
            <%
                }
            %><hr>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/partials/footer.jsp"%>
    </footer>
</body>
</html>
