<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 26/12/2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen - Nuova Segnalazione</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
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
                <div class="col-4">
                    <h1 class="boldFont">Inserisci feedback</h1>
                </div>
            </div>
            <div class="col-6" id="message">
                <p id="messageDescrizione"></p>
                <p id="messageRadio"></p>
            </div>
            <!--row of content-->
            <div class="row align-items-start mt-3" style="margin-left: auto;">
                <!--col of form-->
                <div class="col-md-11 col-sm-11 col-lg-7 col-xl-7 col-xxl-7 col-11">
                    <form id="inoltroForm" class="form-group" action="inserimentoFeedback" method="post" novalidate>
                        <div class="row mb-3">
                            <label for="descrizione" class="col-form-label boldFont">Descrizione</label>
                            <textarea class="form-control boxShadow" type="text" name="descrizione" id="descrizione" rows="10" oninput="validateDescrizione()"></textarea>
                        </div>
                        <label class="form-check-label" for="valutation">Valutazione</label>
                        <div id="valutation" class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="inlineCheckbox1" name="valutazione" value="1">
                            <label class="form-check-label" for="inlineCheckbox1">1</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="inlineCheckbox2"name="valutazione" value="2">
                            <label class="form-check-label" for="inlineCheckbox2">2</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="inlineCheckbox3" name="valutazione" value="3">
                            <label class="form-check-label" for="inlineCheckbox3">3</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="inlineCheckbox4"name="valutazione" value="4">
                            <label class="form-check-label" for="inlineCheckbox4">4</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="inlineCheckbox5" name="valutazione" value="5">
                            <label class="form-check-label" for="inlineCheckbox5">5</label>
                        </div>
                        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <div class="row mb-4">
                            <button type="button" id="buttonInoltro" class="myBtnPink boldFont boxShadow" onclick="submitForm()">Inoltra Feedback</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="./javascript/validazione_inserimentoFeedback.js"></script>
</body>
</html>
