<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.gestioneDati.modelObjects.SegnalazioneInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.gestioneDati.modelObjects.Cittadino" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 23/12/2020
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen - Benvenuto</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styleL.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <!-- Google Poppins font -->
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/>

</head>
<body>
<%
    FacadeDAO service = new FacadeDAO();
    List<SegnalazioneInterface> chiuse =
            service.getSegnalazioniByStato("chiusa", 0);
%>

<div class="container">
    <div class="row w-100 justify-content-center">
        <div class="col">
            <div class="row mt-5">
                <h1 class="boldFont textAlignCenter">Benvenuto in CityZen</h1>
            </div>
            <%@include file="classifica.jsp"%>
            <div class="row mt-5 boxShadow" id="rowAccessOspite">
                <div class="col-11 col-sm-11 col-md-6 col-lg-6 col-xl-6 textAlignCenter">
                    <div class="row">
                        <label class="boldFont">Non sei ancora registrato a CityZen?</label>
                    </div>
                    <div class="row mt-3 m-2">
                        <a href="register.jsp" class="myBtnGreen">Registrati</a>
                    </div>
                </div>
                <div class="col-11 col-sm-11 col-md-6 col-lg-6 col-xl-6 textAlignCenter">
                    <div class="row">
                        <label class="boldFont">Hai già un account?</label>
                    </div>
                    <div class="row mt-3 m-2">
                        <a href="login.jsp" class="myBtnPink">Accedi</a>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <!--segnalazioni  chiuse-->
                <h2 class="boldFont textAlignCenter mt-4">Segnalazioni ad oggi chiuse</h2>
                <div class="row align-items-center mt-3">
                    <c:forEach items="<%=chiuse%>" var="s">
                        <div class="col-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 mt-3 textAlignCenter">
                            <div class="card">
                                <div class="card-body boxShadow cardSegnalazione">
                                    <h5 class="card-title boldFont">${s.oggetto}</h5>
                                    <hr class="mb-3">
                                    <h6 class="card-subtitle mb-3 text-muted">Priorità: ${s.priorita}</h6>
                                    <p class="card-text mb3">Numero solleciti: ${s.numSolleciti}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
