<%--
  Created by IntelliJ IDEA.
  User: nicol
  Date: 20/12/2020
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica password</title>
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
        <div class="col-10 align-self-start">
            <div class="row align-items-start">
                <h1>Modifica password</h1>
            </div>
            <form class="form-horizontal" action="modificaPassword" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd2">Vecchia password:</label>
                    <div class="col-xs-3">
                        <input type="password" class="form-control" id="pwd2" name="oldPass" placeholder="Inserisci la vecchia password" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Nuova password:</label>
                    <div class="col-xs-3">
                        <input type="password" class="form-control" id="pwd" name="newPass" placeholder="Inserisci la nuova password" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
