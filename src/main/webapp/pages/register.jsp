<%--
  Created by IntelliJ IDEA.
  User: 77113132
  Date: 10/21/2025
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/main.css">
</head>
<body>
<header>
    <h1>
        Register User
    </h1>
</header>

<section>
    <p class="description">
        Fill the form and register!
    </p>
    <form action="/CinemManagement_war_exploded/register" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="email" name="email" placeholder="Your Email">
<%--        <label>role:--%>
<%--            <select name="role">--%>
<%--                <option value="">Select Role</option>--%>
<%--                <option value="ROLE_ADMIN">Admin</option>--%>
<%--                <option value="ROLE_USER">User</option>--%>
<%--            </select>--%>
<%--        </label>--%>
        <input class="register" type="submit" value="Register">

    </form>
</section>
<footer>
    <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home" >Back to Home</a>
</footer>
</body>
</html>