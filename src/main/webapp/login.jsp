<%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 15/12/2020
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen - Accedi</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body bgcolor="#008b8b">

<div id="loginContainer" class="container">
    <div id="loginRow" class="row">
        <div id="logoContainer" class="col">
            <img src="res/images/logo_md.png">
        </div>
        <div class="col align-items-center">
            <h1 class="appTitle">Accedi</h1>
            <form action="" method="post">
                <input class="appInput" id="email" name="email" type="text" placeholder="E-mail">
                <input class="appInput" id="pwd" name="pwd" type="password" placeholder="Password">
                <p class="appParagraph">Password dimenticata?</p>
                <button type="submit" class="appButtonBlack">Login</button>
                <p class="appParagraph">Non hai un account? Registrati</p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
