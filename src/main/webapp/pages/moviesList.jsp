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
        <c:if test="${all == true}">
            All Movies
        </c:if>
        <c:if test="${all != true}">
            User Watchlist
        </c:if>
    </h1>
</header>

<section class="search-bar">
    <form action="/CinemManagement_war_exploded/searchMovies" method="get">
        <input type="text" name="title" placeholder="Search by title..."/>

        <select name="genre">
            <option value="">All Genres</option>
            <option value="ACTION">Action</option>
            <option value="DRAMA">Drama</option>
            <option value="COMEDY">Comedy</option>
            <option value="HORROR">Horror</option>
            <option value="ROMANCE">Romance</option>
        </select>
        <input type="submit" value="Search"/>
    </form>
</section>

<c:if test="${all != true}">
    <h2 class="title">My Watchlist</h2>
</c:if>

<section class="user-info">
    Login as ${username} (${role})
</section>

<!-- 🎞️ Movies Section -->
<section class="gallery">
    <c:forEach var="movie" items="${movies}">
        <figure>
            <a>
                <img src="data:image/png;base64,${movie.pic}" alt="${movie.title}">
                <figcaption>
                    <h3>${movie.title}</h3>
                    <p class="genre">Genre: ${movie.genre}</p>
                </figcaption>
            </a>

            <div class="buttons">
                <form action="/CinemManagement_war_exploded/addToWatchlist" method="post">
                    <input type="hidden" name="movieId" value="${movie.id}"/>
                    <input type="submit" value="Add to Watchlist" class="btn add-btn"/>
                </form>

                <c:if test="${role == 'ROLE_ADMIN'}">
                    <form action="/CinemManagement_war_exploded/deleteMovie" method="post">
                        <input type="hidden" name="movieId" value="${movie.id}"/>
                        <input type="submit" value="Delete Movie" class="btn delete-btn"/>
                    </form>
                </c:if>

                <form action="/CinemManagement_war_exploded/movieDetail" method="post">
                    <input type="hidden" name="movieId" value="${movie.id}"/>
                    <input type="submit" value="Movie Detail" class="btn detail-btn"/>
                </form>
            </div>
        </figure>
    </c:forEach>
</section>

<footer>
    <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home">Back to Home</a>
</footer>
</body>
</html>
