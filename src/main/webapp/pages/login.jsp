<%--
  Created by IntelliJ IDEA.
  User: 77113132
  Date: 10/20/2025
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/main.css">
</head>
<body>
<header>
    <h1>
        Login
    </h1>
</header>

<section>
    <p class="description">
        Enter username and passwrod!
    </p>
    <form action="/CinemManagement_war_exploded/login" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <input class="register" type="submit" value="Login">
    </form>
</section>
<footer>
    <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home" >Back to Home</a>
</footer>
</body>
</html>