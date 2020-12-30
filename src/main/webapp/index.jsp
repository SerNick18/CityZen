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
    //recupero i cattadini con maggiori segnalazioni approvate
    ArrayList<Cittadino> cittadini = (ArrayList<Cittadino>) service.getCittadiniOrderedBySegnalazioniApp();
    int posizioneClassifica = 1;
%>

<div class="container">
    <div class="row w-100 justify-content-center">
        <div class="col">
            <div class="row mt-5">
                <h1 class="boldFont textAlignCenter">Benvenuto in CityZen</h1>
            </div>
            <div class="row mb-3 mt-5">
                <h3 class="boldFont" style="color: #F53478">Classifica cittandini più attivi</h3>
            </div>
            <!--classifica cittadini-->
            <div class="row">
                <div class="col-1">
                    <p class="boldFont">#</p>
                </div>
                <div class="col-4">
                    <p class="boldFont">Nome</p>
                </div>
                <div class="col-4">
                    <p class="boldFont">Cognome</p>
                </div>
                <div class="col-2">
                    <p class="boldFont">Approvate</p>
                </div>
            </div>
            <c:forEach items="<%=cittadini%>" var="cittadino">
                <c:if test="${cittadino.numSegnApp>0}">
                    <div class="row">
                        <div class="col-1">
                            <p><%=posizioneClassifica++%></p>
                        </div>
                        <div class="col-4">
                            <p>${cittadino.nome}</p>
                        </div>
                        <div class="col-4">
                            <p>${cittadino.cognome}</p>
                        </div>
                        <div class="col-2">
                            <p>${cittadino.numSegnApp}</p>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
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
