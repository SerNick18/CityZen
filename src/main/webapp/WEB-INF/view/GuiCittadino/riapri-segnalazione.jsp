<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %><%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 26/12/2020
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
        <title>CityZen - Riapri Segnalazione</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <!-- Google Poppins font -->
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/>
</head>
<body>
<%
    FacadeDAO service = new FacadeDAO();
    String idSegnalazioneDaAprire = (String)request.getAttribute("idSegnalazioneDaAprire");
    Segnalazione segnalazione = (Segnalazione) service.getSegnalazioneById(Integer.parseInt(idSegnalazioneDaAprire));
%>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <div class="col-4">
                    <h1 class="boldFont">Riapri Segnalazione</h1>
                </div>
                <div class="col-6" id="message">
                    <c:if test="${Segnalazione.riaperta>0}">
                        <div class="alert alert-danger mt-2" role="alert">
                            Questa segnalazione faceva già riferimento ad <a href="dettagli?id=${Segnalazione.riaperta}">un'altra chiusa!</a>
                        </div>
                    </c:if>
                    <p id="messageDescrizione"></p>
                    <p id="messageFoto"></p>
                </div>
            </div>
            <!--row of content-->
            <div class="row align-items-start mt-3" style="margin-left: auto;">
                <!--col of photo-->
                <div class="col-md-11 col-sm-11 col-lg-4 col-xl-4 col-xxl-4 col-11">
                    <div class="row boldFont">Aggiungi Foto</div>
                    <div class="row m-3">
                        <img id="fotoImg" class="boxShadow">
                    </div>
                </div>
                <!--col of form-->
                <div class="col-md-11 col-sm-11 col-lg-7 col-xl-7 col-xxl-7 col-11">
                    <form id="inoltroForm" class="form-group" action="riapriSegnalazione" method="post" enctype="multipart/form-data" novalidate>
                        <div class="row mb-3">
                            <label for="oggetto" class="col-form-label boldFont">Oggetto</label>
                            <input class="form-control boxShadow" type="text" value="<%=segnalazione.getOggetto()%>" name="oggetto" id="oggetto" required disabled>
                            <input type="hidden" value="<%=segnalazione.getId()%>" name="idSegnalazione">
                        </div>
                        <div class="row mb-3">
                            <label for="descrizione" class="col-form-label boldFont">Descrizione</label>
                            <textarea class="form-control boxShadow" type="text" name="descrizione" id="descrizione" rows="10" oninput="validateDescrizione()"></textarea>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="via" class="form-label boldFont">Via</label>
                                <input class="form-control boxShadow" type="text" name="via" value="<%=segnalazione.getVia()%>" id="via" disabled>
                            </div>
                            <div class="col">
                                <label for="civico" class="form-label boldFont">Civico</label>
                                <input class="form-control boxShadow" type="text" value="<%=segnalazione.getCivico()%>" name="civico" id="civico" disabled>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <input class="form-control boxShadow" oninput="validateFoto()" class="form-control" type="file" id="foto" name="foto" multiple>
                        </div>
                        <div class="row mb-4">
                            <button type="button" id="buttonInoltro" class="myBtnPink boldFont boxShadow" onclick="submitForm()">Riapri Segnalazione</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="./javascript/riapriSegnalazione.js"></script>
</body>
</html>
