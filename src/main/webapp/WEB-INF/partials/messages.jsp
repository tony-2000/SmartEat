<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 28/12/2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>

<%
    if (request.getAttribute("logError") != null) {
%>
    <p style="color: white; background-color: darkblue">
        <%=request.getAttribute("logError")%>
    </p>
<%
    }
%>

<%
    if (request.getAttribute("message") != null) {
%>
    <p style="color: white; background-color: darkblue">
        <%=request.getAttribute("message")%>
    </p>
<%
    }
%>