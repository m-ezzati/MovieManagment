<%--
  Created by IntelliJ IDEA.
  User: 77113132
  Date: 10/21/2025
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Watchlist</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/userDashboard.css">
</head>
<body>
<header>
  <h1>
    User Watchlist
  </h1>
</header>
    <h2 class="title">
        My Watchlist
    </h2>
<section class="user-info">
    Login as ${username} (${role})
</section>


<section class="gallery">
    <c:forEach var="movie" items="${watchList}">
        <figure>
            <a>
                <img src="data:image/png;base64,${movie.pic}" alt="${movie.title}">
                <figcaption><h3>${movie.title}</h3></figcaption>
            </a>
            <form action="/CinemManagement_war_exploded/movieDetail" method="post">
                <input type="hidden" name="movieId" value="${movie.id}"/>
                <input type="submit" value="Movie Detail"/>
            </form>
        </figure>
    </c:forEach>
</section>

<footer>
        <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home">Back to Home</a>
    </footer>
</body>
</html>
