<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="model.gestioneDati.modelObjects.SegnalazioneInterface" %>
<%@ page import="controller.gestioneUtenza.MyServletException" %><%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 19/12/2020
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>CityZen - Homepage</title>
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
    Cittadino cittadino = (Cittadino) session.getAttribute("Cittadino");
    if (cittadino==null)
        throw new MyServletException("Non sei autorizzato a visualizzare questa pagina");
    FacadeDAO facadeDAO = new FacadeDAO();
    ArrayList<SegnalazioneInterface> segnalazioni = (ArrayList<SegnalazioneInterface>) facadeDAO.getSegnalazioniInoltrate(0);
%>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <c:choose>
                <c:when test="${notificaErrore!=null}">
                    <div class="alert alert-danger" role="alert">
                            ${notificaErrore}
                    </div>
                </c:when>
                <c:when test="${notificaSuccesso!=null}">
                    <div class="alert alert-success" role="alert">
                            ${notificaSuccesso}
                    </div>
                </c:when>
            </c:choose>
            <div class="row align-items-start">
                <h1 class="boldFont">SEGNALAZIONI</h1>
            </div>
            <div class="row align-items-start">
                <div class="col"><a href="visualizzaChiuse" class="greyText">Chiuse</a></div>
                <div class="col"><a href="ListApprovate" class="greyText">Approvate</a></div>
            </div>
            <div class="row align-items-center mt-3">
                <c:forEach items="<%=segnalazioni%>" var="s">
                    <div class="col-11 col-sm-10 col-md-6 col-lg-4 col-xl-4 mt-3">
                        <div class="card">
                            <div class="card-body boxShadow cardSegnalazione">
                                <h5 class="card-title boldFont">${s.oggetto}</h5>
                                <hr class="mb-3">
                                <h6 class="card-subtitle mb-3 text-muted">Priorità: ${s.priorita}</h6>
                                <p class="card-text mb3">Numero solleciti: ${s.numSolleciti}</p>
                                <a href="dettagli?id=${s.id}" class="greyText">Dettagli</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col w-100">
                    <form action="inoltroSegnalazione" method="post" style="text-align: center;">
                        <button class="mt-5 myBtnPink w-100 boxShadow">Inoltra Segnalazione</button>
                        <input type="hidden" name="fromGui" value="true">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
