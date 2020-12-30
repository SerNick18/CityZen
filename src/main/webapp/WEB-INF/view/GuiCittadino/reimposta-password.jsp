<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 21/12/2020
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reimposta Password</title>
    <link rel="stylesheet" href="css/styleProfilo.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <h1>Reimposta Password</h1>
        <div class="col-12 align-self-center">
            <form action="reimposta-password" id="form-recupera">
                <div class="form-group">
                    <label for="pwd">Inserisci la password:</label>
                    <input type="password" name="pwd" class="form-control" id="pwd" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="pwd2">Conferma la password:</label>
                    <input type="password" name="pwd2" class="form-control" id="pwd2" aria-describedby="pwdHelper" placeholder="Conferma password">
                    <small id="pwdHelper" class="form-text text-muted"></small>
                </div>
                <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                <input type="hidden" name="utente" value="<%=request.getParameter("utente")%>">
                <input type="hidden" name="provenienza" value="reimposta-password">
                <button type="submit" class="btn btn-success">Reimposta</button>
            </form>
        </div>
    </div>
</div>
<script src="./javascript/controlli-recupera-password.js"></script>
</body>
</html>
