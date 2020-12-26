<%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 26/12/2020
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Segnalazioni Chiuse</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <!-- Le prossime 3 righe sono state aggiunte perchè con bootstrap precedente il css non veniva mostrato correttamente -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Le tue segnalazioni</h1>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-center mt-3">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Oggetto</th>
                        <th scope="col">Stato</th>
                        <th scope="col">Numero Solleciti</th>
                        <th scope="col">Priorità</th>
                        <th scope="col">Riaperto da</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${proprie}" var="i">
                        <tr>
                            <td><a href="dettagli?id=${i.id}">${i.oggetto}</a></td>
                            <td>${i.stato}</td>
                            <td>${i.numSolleciti}</td>
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
