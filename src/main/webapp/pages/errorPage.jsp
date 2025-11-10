
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/main.css">

</head>
<body>

<header><h1>Error Page</h1></header>

<%
    Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
    if (exception != null) {
        out.println("<p style='font=24px ; font-color=red; display:flex;justify-content:center;align-items:center;height:100px;'><strong>Error: </strong> " + exception.getMessage() + "</p>");
    }else {
        out.println("<p style='display:flex;justify-content:center;align-items:center;height:100px;'><strong>Error </strong>Something went wrong</p>");
    }
%>

<footer><a href="/CinemManagement_war_exploded/pages/index.jsp">Home</a></footer>
</body>
</html>
