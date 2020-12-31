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
<h1>Segnalazioni Approvate</h1>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row max-auto">
                <div class="col"><a href="ListApprovate">Approvate</a></div>
                <div class="col"><a href="visualizzaChiuse">Chiuse</a></div>
                <input type="hidden" name="numeroButton" value="0">
            </div>
            <div class="table-responsive row align-items-center mt-3 panel panel-default">
                <table class="table rounded">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Oggetto</th>
                        <th scope="col">Segnalato da</th>
                        <th scope="col">Numero Solleciti</th>
                        <th scope="col">Priorità</th>
                        <th scope="col">Riaperto da</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${approvata}" var="i">
                        <tr>
                            <td><a href="dettagli?id=${i.id}">${i.oggetto}</a></td>
                            <td>${i.cittadino.nome}</td>
                            <td>
                                <form class="form-group" action="inoltroSol" method="post">
                                    ${i.numSolleciti}
                            <button class="btn-Sol" type="submit" name="id" value="${i.id}">+</button>
                                </form>
                            <td>${i.priorita}</td>
                            <c:choose>
                                <c:when test="${i.riaperta!=0}">
                                    <td>${i.riaperta}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>Nessuno</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>