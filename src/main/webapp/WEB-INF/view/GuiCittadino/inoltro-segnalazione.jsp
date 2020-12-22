<%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 22/12/2020
  Time: 20:14
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

</head>
<body>
<div class="container-fluid">
    <div class="row h-100">
        <jsp:include page="header.jsp"></jsp:include>
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>Nuova Segnalazione</h1>
            </div>
            <div class="row align-items-start">
                <div class="col-4 ml-3">
                    <div class="row">Aggiungi Foto</div>
                    <div class="row">
                        <!-- foto -->
                    </div>
                </div>
                <div class="col-6">
                    <form class="form-group" action="inoltroSegnalazione" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <label for="oggetto" class="col-form-label">Oggetto</label>
                            <input type="text" name="oggetto" id="oggetto">
                        </div>
                        <div class="row">
                            <label for="descrizione" class="col-form-label">Descrizione</label>
                            <textarea type="text" name="descrizione" id="descrizione" rows="20"></textarea>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="via" class="col-form-label">Via</label>
                                <input type="text" name="via" id="via">
                            </div>
                            <div class="col">
                                <label for="civico" class="col-form-label">Civico</label>
                                <input type="text" name="civico" id="civico">
                            </div>
                        </div>
                        <div class="row">
                            <input class="form-control" type="file" id="foto" name="foto" multiple>
                        </div>
                        <div class="row">
                            <button class="btn btn-dark" type="submit">Inoltra Segnalazione</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
