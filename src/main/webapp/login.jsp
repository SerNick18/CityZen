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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body bgcolor="#008b8b">


<div id="loginContainer" class="container">
    <div id="loginRow" class="row justify-content-center">
        <div id="logoContainer" class="col-md-3 col-lg-4 col-sm-8 col-xl-4 col-8">
            <img id="logoImg" class="img-fluid" src="resources/images/logo_md.png">
        </div>
        <div class="col-sm-8 d-lg-none d-md-none">
            <hr class="solid">
        </div>
        <div class="col-md-3 col-lg-4 col-sm-8 col-xl-4 col-8">
            <form id="loginAccesso" action="login" method="post" class="form">
                <div class="row md-3">
                    <h1 class="appTitle">Accedi</h1>
                </div>
                <div class="row md-3">
                    <input class="appInput" id="email" name="email" type="text" placeholder="E-mail" oninput="validaEmail()">
                </div>
                <div class="row md-3">
                        <input class="appInput" id="pwd" name="pwd" type="password" placeholder="Password" oninput="validaPassword()">
                </div>
                <div class="row mb-3">
                        <a href="" class="appLink">Password dimenticata?</a>
                </div>
                <div class="row md-3">
                    <button type="submit" class="appButtonBlack" id="loginId" disabled>Login</button>
                    <span id="loginmessaggio">${notifica}</span><br>
                </div>
                <div class="row mb-3">
                    <a href="register.jsp" class="appLink">Non hai un account? Registrati</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="./javascript/validazione_login.js"></script>

</body>
</html>
