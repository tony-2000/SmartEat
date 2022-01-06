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

    <script>
        function validateNome() {
            let nome = document.forms["addMenu"]["nome"].value;
            let nomeAlert = document.getElementById("nomeAlert");
            let pattern = /^[a-zàèéìòù\d ]+/gi;
            if (nome.length < 1) {
                nomeAlert.innerHTML = "Il nome non può essere nullo.";
                return false;
            } else if (nome.length > 25) {
                nomeAlert.innerHTML = "Il nome non può avere una lunghezza superiore ai 25 caratteri.";
                return false;
            } else if (!pattern.test(nome)) {
                nomeAlert.innerHTML = "Il nome non rispetta il formato.";
                return false;
            } else {
                nomeAlert.innerHTML = "";
                return true;
            }
        }

        function validatePrimo() {
            let primo = document.forms["addMenu"]["primo"].value;
            let primoAlert = document.getElementById("primoAlert");
            let pattern = /^[a-zàèéìòù ]+/gi;
            if (primo.length < 1) {
                primoAlert.innerHTML = "Il primo non può essere nullo.";
                return false;
            } else if (primo.length > 25) {
                primoAlert.innerHTML = "Il primo non può avere una lunghezza superiore ai 25 caratteri.";
                return false;
            } else if (!pattern.test(primo)) {
                primoAlert.innerHTML = "Il primo non rispetta il formato.";
                return false;
            } else {
                primoAlert.innerHTML = "";
                return true;
            }
        }

        function validateSecondo() {
            let secondo = document.forms["addMenu"]["secondo"].value;
            let secondoAlert = document.getElementById("secondoAlert");
            let pattern = /^[a-zàèéìòù ]+/gi;
            if (secondo.length < 1) {
                secondoAlert.innerHTML = "Il secondo non può essere nullo.";
                return false;
            } else if (secondo.length > 25) {
                secondoAlert.innerHTML = "Il secondo non può avere una lunghezza superiore ai 25 caratteri.";
                return false;
            } else if (!pattern.test(secondo)) {
                secondoAlert.innerHTML = "Il secondo non rispetta il formato.";
                return false;
            } else {
                secondoAlert.innerHTML = "";
                return true;
            }
        }

        function validateDessert() {
            let dessert = document.forms["addMenu"]["dessert"].value;
            let dessertAlert = document.getElementById("dessertAlert");
            let pattern = /^[a-zàèéìòù ]+/gi;
            if (dessert.length < 1) {
                dessertAlert.innerHTML = "Il dessert non può essere nullo.";
                return false;
            } else if (dessert.length > 25) {
                dessertAlert.innerHTML = "Il dessert non può avere una lunghezza superiore ai 25 caratteri.";
                return false;
            } else if (!pattern.test(dessert)) {
                dessertAlert.innerHTML = "Il dessert non rispetta il formato.";
                return false;
            } else {
                dessertAlert.innerHTML = "";
                return true;
            }
        }

        function validateDescrizione() {
            let descrizione = document.forms["addMenu"]["descrizione"].value;
            let descrizioneAlert = document.getElementById("descrizioneAlert");
            if (descrizione.length < 1) {
                descrizioneAlert.innerHTML = "La descrizione non può essere nulla.";
                return false;
            } else if (descrizione.length > 500) {
                descrizioneAlert.innerHTML = "La descrizione non può avere una lunghezza superiore ai 500 caratteri.";
                return false;
            } else {
                descrizioneAlert.innerHTML = "";
                return true;
            }
        }

        function validatePrezzo() {
            let prezzo = document.forms["addMenu"]["prezzo"].value;
            let prezzoAlert = document.getElementById("prezzoAlert");
            let pattern = /^\d{1,3}\.\d{1,2}$/;
            if (prezzo.length < 3) {
                prezzoAlert.innerHTML = "Il prezzo non può avere una lunghezza minore di 3 caratteri.";
                return false;
            } else if (prezzo.length > 6) {
                prezzoAlert.innerHTML = "Il prezzo non può avere una lunghezza maggiore di 6 caratteri.";
                return false;
            } else if (!pattern.test(prezzo)) {
                prezzoAlert.innerHTML = "Il prezzo non rispetta il formato.";
                return false;
            } else {
                prezzoAlert.innerHTML = "";
                return true;
            }
        }

        function validateForm() {
            return (validateNome() &&
                validatePrimo() &&
                validateSecondo() &&
                validateDessert() &&
                validateDescrizione() &&
                validatePrezzo());
        }
    </script>
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
        <form name="addMenu" action="AddMenu" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
            <h2>Aggiungi un menù</h2><hr>
            <%
                if (pietanze == null || pietanze.isEmpty()) {
            %>
                <p style="color: red">Non ci sono pietanze da aggiungere al menù. Riprovare.</p>
            <%
                } else {
            %>
                <label for="nome">Nome</label>
                <input type="text" name="nome" id="nome">
                <a id="nomeAlert" style="color: red"></a><br>

                <label for="primo">Primo</label>
                <select name="primo" id="primo">
                    <%
                        for (Pietanza p: pietanze) {
                            if (p.getTipo() == 'P') {
                    %>
                    <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <a id="primoAlert" style="color: red"></a><br>

                <label for="secondo">Secondo</label>
                <select name="secondo" id="secondo">
                    <%
                        for (Pietanza p: pietanze) {
                            if (p.getTipo() == 'S') {
                    %>
                    <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <a id="secondoAlert" style="color: red"></a><br>

                <label for="dessert">Dessert</label>
                <select name="dessert" id="dessert">
                    <%
                        for (Pietanza p: pietanze) {
                            if (p.getTipo() == 'D') {
                    %>
                    <option value="<%=p.getNome()%>"><%=p.getNome()%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <a id="dessertAlert" style="color: red"></a><br>

                <label for="descrizione">Descrizione</label>
                <textarea name="descrizione" id="descrizione"
                          rows="5" cols="30" style="resize: none"></textarea>
                <a id="descrizioneAlert" style="color: red"></a><br>

                <label for="image">Immagine</label>
                <input type="file" name="image" id="image">

                <label for="prezzo">Prezzo</label>
                <input type="number" name="prezzo" id="prezzo" step=".25">
                <a id="prezzoAlert" style="color: red"></a><br>

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
