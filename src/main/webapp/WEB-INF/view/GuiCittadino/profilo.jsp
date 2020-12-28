<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="controller.gestioneUtenza.MyServletException" %><%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 19/12/2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo</title>
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
    <div class="container-fluid h-100">
        <div class="row h-100" id="bordo">
                <jsp:include page="header.jsp"></jsp:include>
            <div class="col-10">
                <h1>I tuoi dati</h1>
                <div class="row">
                    <div class="form-group col-3">
                        <label for="nome">Nome</label>
                        <input type="email" class="form-control" id="nome" placeholder="${Cittadino.nome}" disabled>
                    </div>
                    <div class="form-group col-3">
                        <label for="cognome">Cognome</label>
                        <input type="password" class="form-control" id="cognome" placeholder=" ${Cittadino.cognome}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-4">
                        <label for="inputEmail4">Email</label>
                        <input type="email" class="form-control" id="inputEmail4" placeholder="${Cittadino.email}" disabled>
                    </div>
                    <div class="form-group col-4">
                        <label>Password</label>
                        <div class="input-group mb-3">
                                <form action="modificaPassword" class="form-inline">
                                    <input type="hidden" name="provenienza" value="profilo">
                                    <input type="submit" class="btn btn-success btn-sm" value="Modifica Password">
                                </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-4">
                        <label for="via">Via</label>
                        <input type="email" class="form-control" id="via" placeholder="${Cittadino.via}" disabled>
                    </div>
                    <div class="form-group col-1">
                        <label for="civico">Civico</label>
                        <input type="password" class="form-control" id="civico" placeholder=" ${Cittadino.civico}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-3">
                        <label>Segnalazioni effettuate: ${Cittadino.numSegnalazioni}</label>
                        <form action="visualizza-segnalazioni" method="post" class="inline">
                            <input type="hidden" name="cf" value="${Cittadino.CF}">
                            <input type="submit" class="btn btn-success btn-block" value="Visualizza Segnalazioni">
                        </form>
                    </div>
                    <div class="form-group col-3">
                        <label>Segnalazioni Approvate: ${Cittadino.numSegnApp}</label>
                        <form action="elimina-profilo" class="inline">
                            <input type="submit" class="btn btn-danger btn-block" value="Elimina Profilo">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
