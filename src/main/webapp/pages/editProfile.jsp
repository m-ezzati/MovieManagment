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
    <title>Edit Profile</title>
    <link rel="stylesheet" href="/CinemManagement_war_exploded/css/main.css">
</head>
<body>
<header>
    <h1>
        Edit Profile
    </h1>
</header>

<div class="img">
    <img src="data:image/png;base64,${profilePicture}" alt="No picture uploaded">
</div>
<h2>
    Edit Profile
</h2>
<section>
    <form action="/CinemManagement_war_exploded/edit_profile" method="post" enctype="multipart/form-data">
        <input type="text" name="username" value="${username}" readonly>
        <input type="text" name="email" value="${email}" required>
        <label class="form-label">Change Profile Picture</label>
        <input type="file" class="form-control" name = "file">
        <h2>Change Password(optional)</h2>

        <input type="password" name="currentPassword" placeholder="Current Password">
        <input type="password" name="newPassword" placeholder="New Password">
        <input type="password" name="confirmNewPassword" placeholder="Confirm New Password">

        <input class="register" type="submit" value="Save">
    </form>
</section>
<footer>
    <a href="/CinemManagement_war_exploded/pages/index.jsp" class="back-home" >Back to Home</a>
</footer>
</body>
</html>
