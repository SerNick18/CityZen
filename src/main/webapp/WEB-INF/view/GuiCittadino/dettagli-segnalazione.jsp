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
                <div class="col-4">
                    <h1 class="boldFont">Dettagli Segnalazione</h1>
                </div>
                <div class="col-6 m-2" id="message">
                    <c:if test="${Segnalazione.riaperta>0}">
                        <div class="alert alert-danger" role="alert">
                            Questa segnalazione faceva già riferimento ad <a href="dettagli?id=${Segnalazione.riaperta}">un'altra chiusa!</a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="row align-items-start mt-3" style="margin-left: auto;">
                <!--col of photo-->
                <div class="col-md-11 col-sm-11 col-lg-4 col-xl-4 col-xxl-4 col-11">
                    <div class="row boldFont">Foto</div>
                    <div class="row m-3">
                        <img class="boxShadow" src="./resources/images/${Segnalazione.foto}">
                    </div>
                </div>
                <!--col of form-->
                <div class="col-md-11 col-sm-11 col-lg-7 col-xl-7 col-xxl-7 col-11">
                        <div class="row mb-3">
                            <label for="oggetto" class="col-form-label boldFont">Oggetto</label>
                            <input class="form-control boxShadow" type="text" name="oggetto" id="oggetto" placeholder="${Segnalazione.oggetto}" disabled>
                        </div>
                        <div class="row mb-3">
                            <label for="descrizione" class="col-form-label boldFont">Descrizione</label>
                            <textarea class="form-control boxShadow" type="text" name="descrizione" id="descrizione" rows="10" placeholder="${Segnalazione.descrizione}" disabled></textarea>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label for="via" class="form-label boldFont">Via</label>
                                <input class="form-control boxShadow" type="text" name="via" id="via" placeholder="${Segnalazione.via}" disabled>
                            </div>
                            <div class="col">
                                <label for="civico" class="form-label boldFont">Civico</label>
                                <input class="form-control boxShadow" type="text"  id="civico" placeholder="${Segnalazione.civico}" disabled>
                            </div>
                        </div>
                        <div class="row mb-4">
                                <%
                                    Segnalazione s = (Segnalazione)request.getSession().getAttribute("Segnalazione");
                                    if (s.getStato().equals("inoltrata")){
                                %>
                                <form class="form-group" action="modificaSegnalazione" method="post" >
                                    <input type="hidden" name="id" value="${Segnalazione.id}">
                                    <input type="submit" name="approva" value="Modifica">
                                </form>
                                <form class="form-group" action="eliminaSegnalazione" method="post" >
                                    <input type="hidden" name="id" value="${Segnalazione.id}">
                                    <input type="submit" name="elimina" value="Elimina">
                                </form>
                                <%
                                }else if(s.getStato().equals("chiusa")){
                                %>
                                <form class="form-group" action="riapriSegnalazione" method="post" >
                                    <input type="hidden" name="idSegnalazioneDaAprire" value="${Segnalazione.id}">
                                    <input type="submit" name="riapri" value="Riapri">
                                </form>
                                <%
                                    }
                                %>
                        </div>
                </div>
            </div>
                <div class="row">
                    <c:if test='<%=s.getStato().equals("chiusa")%>'>
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
                                                        <c:forEach begin="${f.valutazione+1}" end="5">
                                                            <i class="fa fa-star-o" aria-hidden="true"></i>
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
                                                        <c:forEach begin="${f.valutazione+1}" end="5">
                                                            <i class="fa fa-star-o" aria-hidden="true"></i>
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
                                                        <c:forEach begin="${f.valutazione+1}" end="5">
                                                            <i class="fa fa-star-o" aria-hidden="true"></i>
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
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
