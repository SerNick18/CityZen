<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.gestioneDati.facadeDataAccess.FacadeDAO" %><%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 30/12/2020
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

</head>
<body>
<%
    FacadeDAO serviceC = new FacadeDAO();
    //recupero i cattadini con maggiori segnalazioni approvate
    ArrayList<Cittadino> cittadini = (ArrayList<Cittadino>) serviceC.getCittadiniOrderedBySegnalazioniApp();
    int posizioneClassifica = 1;
%>
<div class="row mb-3 mt-5">
    <h3 class="boldFont m-3" style="color: #F53478">Classifica cittandini più attivi</h3>
</div>
<!--classifica cittadini-->
<div class="row justify-content-center">
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
        <div class="row justify-content-center">
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
</body>
</html>
