<%--
  Created by IntelliJ IDEA.
  User: aless
  Date: 18/12/2020
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>CityZen - Errore</title>
    <link rel="stylesheet" href="css/styleError.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head><!--
<h1>Errore ${requestScope['javax.servlet.error.status_code']}</h1>
 Scegliere una delle due opzioni per stampare l'eccezione:
la prima stampa un breve messaggio d'errore, la seconda lo stacktrace completo.
<pre>${requestScope['javax.servlet.error.status_code']}</pre>-->
<body>
    <div class="container-fluid">
        <div class="col-12 d-inline-flex justify-content-center h-100 align-items-start">
            <div class="jumbotron jumbotron-fluid">
                <div class="container">
                    <c:choose>
                        <c:when test="${requestScope['javax.servlet.error.status_code']==404}">
                            <h1 class="display-4">Errore ${requestScope['javax.servlet.error.status_code']}</h1>
                            <p class="lead">Pagina non trovata.</p>
                            <a class="greyText" href="guiCittadino">Torna alla home</a>
                        </c:when>
                        <c:when test="${requestScope['javax.servlet.error.status_code']==500}">
                            <h1 class="display-4">Errore ${requestScope['javax.servlet.error.status_code']}</h1>
                            <p class="lead">Ops. qualcosa è andato storto.</p>
                            <a class="greyText" href="guiCittadino">Torna alla home</a>
                        </c:when>
                        <c:otherwise>
                            <h1 class="display-4">Errore</h1>
                            <p class="lead">C'è stato un errore, riprova più tardi.</p>
                            <a class="greyText" href="guiCittadino">Torna alla home</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
