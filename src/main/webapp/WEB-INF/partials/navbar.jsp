<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="mainImports.jsp"%>

<style>
    .navbar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: black;
        display: flex;
    }

    .navbar li {
        flex-direction: row;
    }

    .navbar a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    .navbar a:hover {
        color: black;
        background-color: darkgray;
    }
</style>

<div class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/toHome">Home</a></li>
        <li style="flex: 1"></li>
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