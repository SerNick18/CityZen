<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 23/12/2020
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Segnalazioni Inoltrate</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Google Poppins font -->
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"/>
</head>
    <body>
        <div class="container-fluid">
            <div class="row h-100">
                <jsp:include page="header.jsp"></jsp:include>
                <div class="col-10 align-self-start">
                    <div class="row align-items-start">
                        <h1 class="boldFont">Segnalazioni Approvate</h1>
                    </div>
                    <div class="row mx-auto">
                        <div class="col"><a href="visualizza-segnalazioni-inoltrate" class="greyText">Inoltrate</a></div>
                        <div class="col"><a href="ListApprovate" class="greyText">Approvate</a></div>
                        <div class="col"><a href="visualizzaChiuse" class="greyText">Chiuse</a></div>
                    </div>
                    <div class="row align-items-center mt-3 panel panel-default">
                        <div class="col">
                            <div class="table-responsive panel panel-default table-wrap h-auto">
                                <table class="table boxShadow">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col" style="border-top-left-radius: 20px;">Oggetto</th>
                                        <th scope="col">Segnalato da</th>
                                        <th scope="col">Numero Solleciti</th>
                                        <th scope="col">Priorità</th>
                                        <th scope="col" style="border-top-right-radius: 20px;">Riaperto da</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${inoltrate}" var="i">
                                        <tr>
                                            <td><a href="dettagli?id=${i.id}" class="greyText">${i.oggetto}</a></td>
                                            <td>${i.cittadino.nome}</td>
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
                                <button class="myBtnPink w-100">Carica Altro</button>
                                <input type="hidden" name="stato" value="inoltrata">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./javascript/visualizza-altre-segnalazioni.js"></script>
    </body>
</html>
