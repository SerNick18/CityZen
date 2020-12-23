<%@ page import="model.gestioneDati.modelObjects.Impiegato" %>
<%@ page import="controller.gestioneUtenza.MyServletException" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %>
<%@ page import="model.gestioneDati.modelObjects.SegnalazioneInterface" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 22/12/2020
  Time: 21:13
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
    <!-- Le prossime 3 righe sono state aggiunte perchè con bootstrap precedente il css non veniva mostrato correttamente -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
    Impiegato impiegato = (Impiegato) session.getAttribute("Impiegato");
    if (impiegato==null)
        throw new MyServletException("Non sei autorizzato a visualizzare questa pagina");
    FacadeDAO facadeDAO = new FacadeDAO();
    ArrayList<SegnalazioneInterface> segnalazioni = (ArrayList<SegnalazioneInterface>) facadeDAO.getSegnalazioniInoltrate(0);
%>
<h1>Ciao <%=impiegato.getNome()%></h1>


<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>Segnalazioni</h1>
            </div>
            <div class="row align-items-start">
                <div class="col"><a href="visualizza-segnalazioni-inoltrate">Inoltrate</a></div>
                <div class="col"><a href="#">Approvate</a></div>
                <div class="col"><a href="#">Chiuse</a></div>
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
        </div>
    </div>
</div>

</body>
</html>
