<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    ul.navbar {
        list-style-type: none;
    }

    ul.navbar li {
        float: left;
        margin: 0 0.5vw;
    }
</style>

<div>
    <ul class="navbar">
        <li><a href="${pageContext.request.contextPath}/toHome">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/ShowAllMenus">Lista menù</a></li>
        <li><a href="${pageContext.request.contextPath}/ShowPurchases">Acquisti</a></li>
        <li><a href="${pageContext.request.contextPath}/ShowProfile">Profilo</a></li>
        <%
            Utente u = (Utente) session.getAttribute("utenteSessione");
            if (u != null && u.isAmministratore().isAdmin()) {
        %>
            <li><a href="${pageContext.request.contextPath}/AdminArea">Area amministratore</a></li>
        <%
            }
        %>
        <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
    </ul>
</div>