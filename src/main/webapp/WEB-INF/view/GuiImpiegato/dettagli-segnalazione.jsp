<%@ page import="model.gestioneDati.modelObjects.Segnalazione" %><%--
  Created by IntelliJ IDEA.
  User: Francesco Sabia
  Date: 23/12/2020
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen-Segnalazione</title>
    <link rel="stylesheet" href="css/styleL.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>Segnalazione</h1>
            </div>
            <div class="row align-items-start">
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
                    <div class="row">
                        <label  class="col-form-label">Numero Solleciti: ${Segnalazione.numSolleciti}</label>
                    </div>
                </div>
                <div class="row">
                        <%
                            Segnalazione s = (Segnalazione)request.getSession().getAttribute("Segnalazione");
                            if (s.getStato().equals("inoltrata")){
                        %>
                    <form class="form-group" action="approva" method="post" >
                        <input type="hidden" name="id" value="${Segnalazione.id}">
                        <input type="submit" name="approva" value="Approva">
                    </form>
                    <form class="form-group" action="rifiutoSegnalazione" method="post" >
                        <input type="hidden" name="id" value="${Segnalazione.id}">
                        <input type="submit" name="rifiuta" value="Rifiuta">
                    </form>
                        <%
                            }else if (s.getStato().equals("approvata")){
                        %>
                        <form class="form-group" action="chiusuraSegnalazione" method="post">
                            <input type="hidden" name="id" value="${Segnalazione.id}">
                            <input type="submit" name="chiudi" value="Chiudi">
                        </form>
                        <%
                            }
                        %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
