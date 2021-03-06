<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Segnalazioni Chiuse</title>
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
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 mx-auto">
            <div class="row mx-auto">
                <h1 class="boldFont">SEGNALAZIONI CHIUSE</h1>
            </div>
            <div class="row mx-auto">
                <div class="col"><a class="greyText" href="ListApprovate">Approvate</a></div>
                <div class="col"><a class="greyText" href="visualizzaChiuse">Chiuse</a></div>
            </div>
            <div class="table-responsive align-items-center mt-3 panel panel-default">
                <table class="table rounded">
                    <thead>
                    <tr>
                        <th scope="col">Oggetto</th>
                        <th scope="col">Segnalato da</th>
                        <th scope="col">Numero Solleciti</th>
                        <th scope="col">Priorità</th>
                        <th scope="col">Segnalazione riaperta</th>
                        <th scope="col">Feedback</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${chiuse}" var="i">
                        <tr>
                            <td><a href="dettagli?id=${i.id}">${i.oggetto}</a></td>
                            <td>${i.cittadino.nome}</td>
                            <td>${i.numSolleciti}</td>
                            <td>${i.priorita}</td>
                            <c:choose>
                                <c:when test="${i.riaperta!=0}">
                                    <td><a href="dettagli?id=${i.riaperta}" class="greyText">Visualizza</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td>Non riaperta</td>
                                </c:otherwise>
                            </c:choose>
                            <td><a href="inserimentoFeedback?id=${i.id}&provenienza=listaChiuse">Inserisci feedback</a> </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button class="myBtnPink w-100">Carica Altro</button>
                <input type="hidden" name="stato" value="chiusa">
                <input type="hidden" name="tipo" value="chiuse-cittadino">
            </div>
        </div>
    </div>
</div>
<script src="./javascript/visualizza-altre-segnalazioni.js"></script>
</body>
</html>
