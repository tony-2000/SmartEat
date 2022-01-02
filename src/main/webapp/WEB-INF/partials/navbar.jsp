<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 21/12/2021
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="mainImports.jsp"%>

<div class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/toHome">Home</a></li>
        <li style="flex: 1"></li>
        <li><a href="${pageContext.request.contextPath}/ShowAllMenus">Menù</a></li>
        <li><a href="${pageContext.request.contextPath}/ShowPurchases">Acquisti</a></li>
        <li><a href="${pageContext.request.contextPath}/ShowProfile">Profilo</a></li>
        <%
            Utente u = (Utente) session.getAttribute("utenteSessione");
            if (u != null && u.isAmministratore().isAdmin()) {
        %>
        <li><a href="${pageContext.request.contextPath}/AdminArea">Ammin.</a></li>
        <%
            }
        %>
        <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
    </ul>
</div>