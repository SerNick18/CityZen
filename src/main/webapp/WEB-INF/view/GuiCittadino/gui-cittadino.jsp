<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="model.gestioneDati.modelObjects.SegnalazioneInterface" %><%--
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

</head>
<body>
<%
    FacadeDAO facadeDAO = new FacadeDAO();
    Cittadino cittadino = (Cittadino) request.getSession().getAttribute("Cittadino");
    ArrayList<SegnalazioneInterface> segnalazioni = (ArrayList<SegnalazioneInterface>) facadeDAO.getSegnalazioniInoltrate(0);
%>
<h1>Benvenuto <%=cittadino.getEmail()%></h1>


<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>SEGNALAZIONI</h1>
            </div>
            <div class="row align-items-start">
                <div class="col"><a href="/profilo">Popolari</a></div>
                <div class="col"><a href="/profilo">Chiuse</a></div>
                <div class="col"><a href="/profilo">Recenti</a></div>
                <div class="col"><a href="/profilo">Approvate</a></div>
            </div>
            <div class="row align-items-center mt-3">
                <c:forEach items="<%=segnalazioni%>" var="s">
                    <div class="col-4 mt-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${s.oggetto}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">Priorità: ${s.priorita}</h6>
                                <p class="card-text">Numero solleciti: ${s.numSolleciti}</p>
                                <a href="#" class="card-link">Dettagli</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="mt-5">Inoltra Segnalazione</button>
        </div>
    </div>
</div>

</body>
</html>
