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
    <title>Movies</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/main.css">
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/movie.css">

</head>
<body>
<header>
    <h1>
        Add Movie
    </h1>
</header>
<section>
    <p class="description">
        Fill the moive information and register!
    </p>
    <form action="/CinemManagement_war_exploded/movies" method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Movie Title" required>
        <input type="text" name="description" placeholder="Movie Description">
        <input type="number" name="duration" placeholder="Duration" step="any">
        <div class="form-row">
            <label>Genre:</label>
            <select name="genre">
                <option value="ACTION">ACTION</option>
                <option value="DRAMA">DRAMA</option>
                <option value="COMEDY">COMEDY</option>
                <option value="HORROR">HORROR</option>
                <option value="ROMANCE">ROMANCE</option>
            </select>
        </div>
        <label class="form-label">Movie picture</label>
        <input type="file" class="form-control" name = "picture">
        <input class="register" type="submit" value="Register">
    </form>
</section>
<footer>
    <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home" >Back to Home</a>
</footer>
</body>
</html>