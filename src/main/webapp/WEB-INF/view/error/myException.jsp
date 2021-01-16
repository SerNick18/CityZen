<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 18/12/2020
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <title>CityZen - Errore</title>
        <link rel="stylesheet" href="css/styleError.css">
        <link rel="stylesheet" href="css/styleL.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <!-- Google Poppins font -->
        <link rel="preconnect" href="https://fonts.gstatic.com"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/></head>
    <body bgcolor="#008b8b">
        <div id="loginContainer" class="container">
            <div id="loginRow" class="row justify-content-center">
                <div id="logoContainer" class="col-md-4 col-lg-4 col-sm-8 col-xl-4 col-8">
                    <img id="logoImg" class="img-fluid" src="resources/images/logo_md.png">
                </div>
                <div class="col-lg-1 col-md-1">
                    <hr class="solid d-lg-none d-md-none">
                    <div class="separator"></div>
                </div>
                <div class="col-md-3 col-lg-4 col-sm-8 col-xl-4 col-8">
                    <div class="row justify-content-center">
                        <h1 class="appTitle"><%= exception.getMessage() %></h1>
                        <button class="myBtnGreen mt-5">
                            <a onclick="history.go(-1)">Clicca qui per tornare alla pagina precedente</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
