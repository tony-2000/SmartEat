<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Smart Eat</title>
</head>
<body>
<%
    Utente utente = (Utente) session.getAttribute("utenteSessione");
    if (utente == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    } else {
        response.sendRedirect(request.getContextPath() + "/toHome");
    }
%>
<%@include file="WEB-INF/partials/mainImports.jsp"%>
</body>
</html>