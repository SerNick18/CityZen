<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Segnalazioni Approvate</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <!-- Le prossime 3 righe sono state aggiunte perchè con bootstrap precedente il css non veniva mostrato correttamente -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 mx-auto">
            <div class="row mx-auto">
                <h1 class="boldFont">SEGNALAZIONI APPROVATE</h1>
            </div>
            <div class="row mx-auto">
                <div class="col"><a class="greyText" href="ListApprovate">Approvate</a></div>
                <div class="col"><a class="greyText" href="visualizzaChiuse">Chiuse</a></div>
                <input type="hidden" name="numeroButton" value="0">
            </div>
            <div class="table-responsive row align-items-center mx-auto panel panel-default">
                <table class="table rounded">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Oggetto</th>
                        <th scope="col">Segnalato da</th>
                        <th scope="col">Priorità</th>
                        <th scope="col">Segnalazione riaperta</th>
                        <th scope="col">Numero Solleciti</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${approvata}" var="i">
                        <tr>
                            <td><a href="dettagli?id=${i.id}">${i.oggetto}</a></td>
                            <td>${i.cittadino.nome}</td>
                            <td>${i.priorita}</td>
                            <c:choose>
                                <c:when test="${i.riaperta!=0}">
                                    <td><a href="dettagli?id=${i.riaperta}" class="greyText">Visualizza</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td>Non riaperta</td>
                                </c:otherwise>
                            </c:choose>
                            <td> ${i.numSolleciti} </td>
                            <td>
                                <!--<form class="form-group" action="inoltroSol" method="post">
                                    <button class="btn-Sol" type="submit" name="id" value="${i.id}">+</button>
                                </form>-->
                                <a style="text-decoration: none;" href="inoltroSol?id=${i.id}" class="btn-Sol" type="submit" >+</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button class="myBtnPink w-100">Carica Altro</button>
                <input type="hidden" name="stato" value="approvata">
                <input type="hidden" name="tipo" value="approvate-cittadino">
            </div>
        </div>
    </div>
</div>
<script src="./javascript/visualizza-altre-segnalazioni.js"></script>
</body>
</html>