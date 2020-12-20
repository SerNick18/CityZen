<%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 15/12/2020
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CityZen - Registrati</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
<div id="loginContainer" class="container">
    <div id="loginRow" class="row justify-content-center">
        <div id="logoContainer" class="col-md-3 col-lg-4 col-sm-8 col-xl-4 col-4 align-self-center">
            <img id="logoImg" class="img-fluid" src="resources/images/logo_sm.png">
        </div>
        <div class="col-sm-8 col-lg-1 col-md-1 my-auto">
            <hr class="solid d-md-none">
            <div class="verticalLine"></div>
        </div>
        <div class="col-md-5 col-lg-5 col-sm-8 col-xl-4 col-8">
            <form id="loginForm" action="register" method="post" class="form-group">
                <div class="row md-3">
                    <h1 class="appTitle">Registrati</h1>
                </div>
                <div class="row md-3">
                    <div class="col-12 col-sm-12 col-md-6 col-lg-6">
                        <input class="appInput form-control" id="nome" name="nome" type="text" placeholder="Nome" oninput="validaNome()">
                    </div>
                    <div class="col-12 col-sm-12 col-md-6 col-lg-6">
                        <input class="appInput form-control" id="cognome" name="cognome" type="text" placeholder="Cognome" oninput="validaCognome()">
                    </div>
                </div>
                <div class="row md-3">
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="email" name="email" type="text" placeholder="Email" oninput="validaEmail()">
                    </div>
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="cf" name="cf" type="text" placeholder="Codice Fiscale" oninput="validaCodiceFiscale()">
                    </div>
                </div>
                <div class="row row-cols-3 md-3">
                    <div class="col-6">
                        <input class="appInput form-control" id="via" name="via" type="text" placeholder="via" oninput="validaVia()">
                    </div>
                    <div class="col-2">
                        <input class="appInput form-control" id="civico" name="civico" type="text" placeholder="N." oninput="validaCivico()">
                    </div>
                    <div class="col-4">
                        <input class="appInput form-control" id="citta" name="citta" type="text" placeholder="Città" oninput="validaCitta()">
                    </div>
                </div>
                <div class="row row-cols-2 mb-3">
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd1" name="pwd1" type="password" placeholder="Password" oninput="validaPassword()">
                    </div>
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd2" name="pwd2" type="password" placeholder="Conferma Password" oninput="validaPassword()">
                    </div>
                </div>
                <div class="row md-3">
                    <button type="submit" class="appButtonBlack" id="registrami" disabled>Registrati</button>
                   <!-- <input id="registrami"  class="appButtonBlack" type="submit" value="Registrami" disabled> -->
                    <span id="registramimessaggio">${notifica}</span><br>
                </div>
                <div class="row md-3">
                    <a href="login.jsp" class="appLink">Hai già un account? Accedi</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="./javascript/validazione_registrazione.js"></script>
</body>
</html>
