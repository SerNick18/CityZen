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
    <link rel="stylesheet" href="css/styleL.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <!-- Google Poppins font -->
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/>

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
                <div class="row">
                    <div class="col-4">
                        <h1 class="boldFont">I tuoi dati</h1>
                    </div>
                </div>
                <div class="row justify-content-center mt-5">
                    <div class="form-group col-6 align-self-center col-md-6 col-lg-6 col-xl-6">
                        <label for="nome" class="boldFont">Nome</label>
                        <input type="email" class="form-control boxShadow" id="nome" placeholder="${Cittadino.nome}" disabled>
                    </div>
                    <div class="form-group col-6 align-self-center col-md-6 col-lg-6 col-xl-6">
                        <label for="cognome" class="boldFont">Cognome</label>
                        <input type="password" class="form-control boxShadow" id="cognome" placeholder=" ${Cittadino.cognome}" disabled>
                    </div>
                </div>
                <div class="row justify-content-center mt-1">
                    <div class="form-group col-11 col-sm-11 col-md-8 col-lg-8 col-xl-8 mt-3">
                        <label for="inputEmail4" class="boldFont">Email</label>
                        <input type="email" class="form-control boxShadow" id="inputEmail4" placeholder="${Cittadino.email}" disabled>
                    </div>
                    <div class="form-group col-11 col-sm-11 col-md-4 col-lg-4 col-xl-4 mt-3">
                        <label class="boldFont">Password</label>
                        <div class="input-group mb-3">
                                <form action="modificaPassword" class="form-inline">
                                    <input type="hidden" name="provenienza" value="profilo">
                                    <input type="submit" class="btn btn-success btn-sm" value="Modifica Password">
                                </form>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center mt-1">
                    <div class="form-group col-11 col-sm-11 col-md-9 col-lg-9 col-xl-9 mt-3">
                        <label for="via" class="boldFont">Via</label>
                        <input type="email" class="form-control boxShadow" id="via" placeholder="${Cittadino.via}" disabled>
                    </div>
                    <div class="form-group col-11 col-sm-11 col-md-3 col-lg-3 col-xl-3 mt-3">
                        <label for="civico" class="boldFont">Civico</label>
                        <input type="password" class="form-control boxShadow" id="civico" placeholder=" ${Cittadino.civico}" disabled>
                    </div>
                </div>
                <div class="row justify-content-center mt-5">
                    <div class="form-group col-11 col-sm-11 col-md-6 col-lg-6 col-xl-6">
                        <label class="boldFont">Segnalazioni effettuate: ${Cittadino.numSegnalazioni}</label>
                        <form action="visualizza-segnalazioni" method="post" class="inline">
                            <input type="hidden" name="cf" value="${Cittadino.CF}">
                            <input type="submit" class="btn btn-success btn-block" value="Visualizza Segnalazioni">
                        </form>
                    </div>
                    <div class="form-group col-11 col-sm-11 col-md-6 col-lg-6 col-xl-6 mt-3">
                        <label class="boldFont">Segnalazioni Approvate: ${Cittadino.numSegnApp}</label>
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
