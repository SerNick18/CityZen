<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="controller.gestioneUtenza.MyServletException" %><%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 20/12/2020
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen - Elimina Profilo</title>
    <link rel="stylesheet" href="css/styleProfilo.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <%
        Cittadino cittadino = (Cittadino) session.getAttribute("Cittadino");
        if (cittadino==null)
            throw new MyServletException("Non sei autorizzato a visualizzare questa pagina");
    %>
</head>
<body>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-center">
                <h1>ELIMINA PROFILO</h1>
            </div>
            <div class="row align-items-center">
                <h3>Sei sicuro di voler eliminare il tuo profilo?</h3>
            </div>
            <div class="row align-items-center mt-3">
                <div class="col-1">
                    <a href="elimina-profilo-servlet">SI</a>
                </div>
                <div class="col-1">
                    <a href="profilo">NO</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
