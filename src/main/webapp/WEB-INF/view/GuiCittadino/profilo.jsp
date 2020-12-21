<%@ page import="model.gestioneDati.modelObjects.Cittadino" %>
<%@ page import="controller.gestioneUtenza.MyServletException" %><%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 19/12/2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profilo</title>
    <link rel="stylesheet" href="css/styleProfilo.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <%
        Cittadino cittadino = (Cittadino) session.getAttribute("Cittadino");
        if (cittadino==null)
            throw new MyServletException("Non sei autorizzato a visualizzare questa pagina");
    %>
</head>
<body>
    <div class="container-fluid h-100">
        <div class="row h-50 text-center" id="bordo">
            <div class="col-12">
                <h1>I tuoi dati</h1>
                <div class="row">
                    <div class="col-6">
                        <b>Nome:</b> ${Cittadino.nome}
                    </div>
                    <div class="col-6">
                        <b>Cognome:</b> ${Cittadino.cognome}
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        <b>Email:</b> ${Cittadino.email}
                    </div>
                    <div class="col-6">
                        <b>Password: </b>
                        <form action="modificaPassword" class="form-inline">
                            <input type="hidden" name="provenienza" value="profilo">
                            <input type="submit" class="btn btn-success btn-sm" value="Modifica Password">
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        <b>Via:</b> ${Cittadino.via}
                    </div>
                    <div class="col-6">
                        <b>Civico:</b> ${Cittadino.civico}
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        <b>Segnalazioni effettuate:</b> ${Cittadino.numSegnalazioni}
                    </div>
                    <div class="col-6">
                        <b>Segnalazioni Approvate:</b> ${Cittadino.numSegnApp}
                    </div>
                </div>
            </div>
        </div>
        <div class="row h-50 text-center">
            <div class="col-6">
                <form action="visualizza-segnalazioni">
                    <input type="submit" class="btn btn-success btn-block" value="Visualizza Segnalazioni">
                </form>
            </div>
            <div class="col-6">
                <form action="elimina-profilo">
                    <input type="submit" class="btn btn-danger btn-block" value="Elimina Profilo">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
