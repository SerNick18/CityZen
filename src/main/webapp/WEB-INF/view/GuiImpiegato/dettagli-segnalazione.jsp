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
                <h1>Dettagli Segnalazione</h1>
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
                        <form class="form-group" action="approva" method="post" >
                            <input type="hidden" name="id" value="${Segnalazione.id}">
                            <input type="submit" class="myBtnPink boldFont boxShadow" name="approva" value="Approva">
                        </form>
                        <form class="form-group" action="rifiutoSegnalazione" method="post" >
                            <input type="hidden" name="id" value="${Segnalazione.id}">
                            <input type="submit" class="myBtnPink boldFont boxShadow" name="rifiuta" value="Rifiuta">
                        </form>
                        <%
                        }else if (s.getStato().equals("approvata")){
                        %>
                        <form class="form-group" action="chiusuraSegnalazione" method="post">
                            <input type="hidden" name="id" value="${Segnalazione.id}">
                            <input type="submit" class="myBtnPink boldFont boxShadow" name="chiudi" value="Chiudi">
                        </form>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
    </div>
</div>
</div>
</body>
</html>
