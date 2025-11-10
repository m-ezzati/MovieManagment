<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${movie.title} - Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/movie.css">
</head>
<body>
<header>
    <h1>${movie.title}</h1>
</header>

<section class="movie-detail-container">
    <!-- 🎬 Movie Header -->
    <div class="movie-header">
        <img src="data:image/png;base64,${movie.pic}" alt="${movie.title}">
        <div class="movie-title-duration">
            <h2>${movie.title}</h2>
            <p><strong>Duration:</strong> ${movie.duration} min</p>
            <p><strong>Genre:</strong> ${movie.genre}</p>
        </div>
    </div>

    <!-- ⭐ Average Rating -->
    <div class="avg-rating">
        Average Rating:
        <c:choose>
            <c:when test="${averageRating > 0}">
                <c:forEach var="i" begin="1" end="${averageRating}">
                    <span class="avg-stars">★</span>
                </c:forEach>
                <c:forEach var="i" begin="1" end="${5 - averageRating}">
                    <span class="avg-stars">☆</span>
                </c:forEach>
                (${averageRating}/5)
            </c:when>
        </c:choose>
        <%--        <button class="btn small-btn" id="openRateModal">Your Rate</button>--%>
        <!-- ⭐ User Rating -->
        <div class="user-rating">
            <c:choose>
                <c:when test="${userRate > 0}">
                    <p>Your Rate:
                        <c:forEach var="i" begin="1" end="${userRate}">
                            ★
                        </c:forEach>
                        <c:forEach var="i" begin="1" end="${5 - userRate}">
                            ☆
                        </c:forEach>
                        (${userRate}/5)
                    </p>
                </c:when>
                <c:otherwise>
                    <button class="btn small-btn" id="openRateModal">Your Rate</button>
                </c:otherwise>
            </c:choose>
        </div>

    </div>

    <!-- 📝 Description -->
    <div class="movie-description">
        <h3>Description</h3>
        <p>${movie.description}</p>
    </div>

    <hr>

    <!--  Comments Section -->
    <h3>Add a Comment</h3>
    <form action="${pageContext.request.contextPath}/addComment" method="post">
        <input type="hidden" name="movieId" value="${movie.id}">
        <textarea name="comment" placeholder="Write your comment..." required></textarea>
        <input type="submit" value="Post Comment" class="btn register">
    </form>
    <div class="comments-section">
        <h2>Comments</h2>
        <c:if test="${empty movie.comments}">
            <p>No comments yet.</p>
        </c:if>
        <c:forEach var="comment" items="${movie.comments}">
            <div class="comment">
                <p><strong>${comment.user.username}</strong> says:</p>
                <p>${comment.comment}</p>
                <small>Posted at: ${comment.createdAt}</small>
            </div>
        </c:forEach>
    </div>
</section>

<footer>
    <a href="${pageContext.request.contextPath}/allMovies" class="back-home">Back to All Movies</a>
</footer>

<div id="rateModal" class="modal">
    <div class="modal-content">
        <span class="close" id="closeRateModal">&times;</span>
        <h2>Rate "${movie.title}"</h2>
        <form action="${pageContext.request.contextPath}/rateMovie" method="post">
            <input type="hidden" name="movieId" value="${movie.id}"/>
            <div class="stars">
                <input type="radio" id="star5-${movie.id}" name="rating" value="5"/>
                <label
                        for="star5-${movie.id}">★</label>
                <input type="radio" id="star4-${movie.id}" name="rating" value="4"/>
                <label
                        for="star4-${movie.id}">★</label>
                <input type="radio" id="star3-${movie.id}" name="rating" value="3"/><label
                    for="star3-${movie.id}">★</label>
                <input type="radio" id="star2-${movie.id}" name="rating" value="2"/><label
                    for="star2-${movie.id}">★</label>
                <input type="radio" id="star1-${movie.id}" name="rating" value="1"/><label
                    for="star1-${movie.id}">★</label>
            </div>
            <input type="submit" value="Rate" class="btn rate-btn"/>
        </form>
    </div>
</div>
<script>
    const modal = document.getElementById("rateModal");
    const openBtn = document.getElementById("openRateModal");
    const closeBtn = document.getElementById("closeRateModal");

    openBtn.onclick = () => modal.style.display = "flex";
    closeBtn.onclick = () => modal.style.display = "none";
    window.onclick = (e) => {
        if (e.target === modal) modal.style.display = "none";
    };
</script>
</body>
</html>
