<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 23/12/2020
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>CityZen-Segnalazione</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styleFeedback.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>Segnalazione</h1>
            </div>
            <div class="row mx-auto">
                <div class="col-4 ml-3">
                    <div class="row">
                        <div class="row">
                            <img src="./resources/images/${Segnalazione.foto}">
                    </div>
                </div>
                </div>
                <div class="col-6">
                        <div class="row">
                            <label class="col-form-label">${Segnalazione.oggetto}</label>
                        </div>
                        <div class="row">
                            <label for="descrizione" class="col-form-label">Descrizione</label>
                            <textarea type="text" name="descrizione" id="descrizione" rows="5" placeholder="${Segnalazione.descrizione}" disabled></textarea>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label class="col-form-label">Via: ${Segnalazione.via}</label>
                            </div>
                            <div class="col">
                                <label  class="col-form-label">Civico: ${Segnalazione.civico}</label>
                            </div>
                        </div>
                        <div class="row">
                            <label  class="col-form-label">Stato: ${Segnalazione.stato}</label>
                        </div>
                        <div class="row">
                            <label class="col-form-label">Priorità: ${Segnalazione.priorita}</label>
                        </div>
                    <div class="row">
                        <label  class="col-form-label">Numero Solleciti: ${Segnalazione.numSolleciti}</label>
                    </div>
                </div>
                <div class="row">
                    <h2>Feedback</h2>
                    <c:choose>
                        <c:when test="${feedbacks.size()!=0}">
                            <c:forEach items="${feedbacks}" var="f">
                                <c:choose>
                                    <c:when test = "${f.valutazione>=1 && f.valutazione<=2}">
                                        <div class="alert alert-danger" role="alert">
                                            <div class="row">
                                                <div class="col-10">
                                                        ${f.cittadino.nome} ${f.cittadino.cognome}: ${f.descrizione}
                                                </div>
                                                <div class="col-2">
                                                    <c:forEach begin="1" end="${f.valutazione}">
                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="row feedback-data">
                                                <div class="col-12">
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${f.dataFeedback}" />
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>

                                    <c:when test = "${f.valutazione==3}">
                                        <div class="alert alert-warning" role="alert">
                                            <div class="row">
                                                <div class="col-10">
                                                        ${f.cittadino.nome} ${f.cittadino.cognome}: ${f.descrizione}
                                                </div>
                                                <div class="col-2">
                                                    <c:forEach begin="1" end="${f.valutazione}">
                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="row feedback-data">
                                                <div class="col-12">
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${f.dataFeedback}" />
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-success" role="alert">
                                            <div class="row">
                                                <div class="col-10">
                                                        ${f.cittadino.nome} ${f.cittadino.cognome}: ${f.descrizione}
                                                </div>
                                                <div class="col-2">
                                                    <c:forEach begin="1" end="${f.valutazione}">
                                                        <i class="fa fa-star" aria-hidden="true"></i>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="row feedback-data">
                                                <div class="col-12">
                                                    <fmt:formatDate pattern="dd-MM-yyyy" value="${f.dataFeedback}" />
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3>Non ci sono feedback</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
