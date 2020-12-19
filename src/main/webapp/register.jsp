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
    <title>CityZen - Registrati</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>

<div id="loginContainer" class="container">
    <div id="loginRow" class="row">
        <div id="logoContainer" class="col-md-3 col-lg-2">
            <img id="logoImg" class="img-fluid" src="resources/images/logo_sm.png">
        </div>
        <div class="col-lg-8 col-md-5">
            <form id="loginForm" action="register" method="post" class="form-group">
                <div class="row mb-3">
                    <h1 class="appTitle">Registrati</h1>
                </div>
                <div class="row mb-3">
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="nome" name="nome" type="text" placeholder="Nome">
                    </div>
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="cognome" name="cognome" type="text" placeholder="Cognome">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="email" name="email" type="text" placeholder="Email">
                    </div>
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="cf" name="cf" type="text" placeholder="Codice Fiscale">
                    </div>
                </div>
                <div class="row row-cols-3 mb-3">
                    <div class="col-6">
                        <input class="appInput form-control" id="via" name="via" type="text" placeholder="via">
                    </div>
                    <div class="col-1">
                        <input class="appInput form-control" id="civico" name="civico" type="text" placeholder="N.">
                    </div>
                    <div class="col-5">
                        <input class="appInput form-control" id="citta" name="citta" type="text" placeholder="Città">
                    </div>
                </div>
                <div class="row row-cols-2 mb-3">
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd1" name="pwd1" type="password" placeholder="Password">
                    </div>
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd2" name="pwd2" type="password" placeholder="Conferma Password">
                    </div>
                </div>
                <div class="row mb-3">
                    <button type="submit" class="appButtonBlack">Registrati</button>
                </div>
                <div class="row mb-3">
                    <a href="login.jsp" class="appLink">Hai già un account? Accedi</a>
                </div>
            </form>
        </div>
    </div>
</div>



</body>
</html>
