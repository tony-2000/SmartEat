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
    <style>
        #error a:hover {
            cursor: pointer;
        }
    </style>

    <script>
        function hideError() {
            let element = document.getElementById("error");
            element.style.display = "none";
        }
    </script>

    <div style="color: white; background-color: red;
        padding: .25rem .5rem; margin: .5rem; border-radius: 5px;
        display: flex; flex-direction: row" id="error">
        <p><%=request.getAttribute("logError")%></p>
        <div style="flex: 1"></div>
        <p>
            <a onclick="hideError()">
                <img src="${pageContext.request.contextPath}/images/x-svgrepo-com.svg" alt="cross"
                     style="width: 1rem">
            </a>
        </p>
    </div>
<%
    }
%>

<%
    if (request.getAttribute("message") != null) {
%>
    <style>
        #message a:hover {
            cursor: pointer;
        }
    </style>

    <script>
        function hideMessagge() {
            let element = document.getElementById("message");
            element.style.display = "none";
        }
    </script>

    <div style="color: white; background-color: darkblue;
        padding: .25rem .5rem; margin: .5rem; border-radius: 5px;
        display: flex; flex-direction: row" id="message">
        <p><%=request.getAttribute("message")%></p>
        <div style="flex: 1"></div>
        <p>
            <a onclick="hideMessagge()">
                <img src="${pageContext.request.contextPath}/images/x-svgrepo-com.svg" alt="cross"
                    style="width: 1rem">
            </a>
        </p>
    </div>
<%
    }
%>