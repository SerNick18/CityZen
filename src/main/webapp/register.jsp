<%--
  Created by IntelliJ IDEA.
  User: leoca
  Date: 15/12/2020
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CityZen - Registrati</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>

<div id="loginContainer" class="container">
    <div id="loginRow" class="row">
        <div id="logoContainer" class="col-md-3 col-lg-2">
            <img id="logoImg" class="img-fluid" src="resources/images/logo_sm.png">
        </div>
        <div class="col-lg-8 col-md-5">
            <form id="loginForm" action="register" method="post" class="form-group">
                <div class="row mb-3">
                    <h1 class="appTitle">Registrati</h1>
                </div>
                <div class="row mb-3">
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="nome" name="nome" type="text" placeholder="Nome">
                    </div>
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="cognome" name="cognome" type="text" placeholder="Cognome">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="email" name="email" type="text" placeholder="Email">
                    </div>
                    <div class="col m-auto w-50">
                        <input class="appInput form-control" id="cf" name="cf" type="text" placeholder="Codice Fiscale">
                    </div>
                </div>
                <div class="row row-cols-3 mb-3">
                    <div class="col-6">
                        <input class="appInput form-control" id="via" name="via" type="text" placeholder="via">
                    </div>
                    <div class="col-1">
                        <input class="appInput form-control" id="civico" name="civico" type="text" placeholder="N.">
                    </div>
                    <div class="col-5">
                        <input class="appInput form-control" id="citta" name="citta" type="text" placeholder="Città">
                    </div>
                </div>
                <div class="row row-cols-2 mb-3">
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd1" name="pwd1" type="password" placeholder="Password">
                    </div>
                    <div class="col w-50">
                        <input class="appInput form-control" id="pwd2" name="pwd2" type="password" placeholder="Conferma Password">
                    </div>
                </div>
                <div class="row mb-3">
                   <!-- <button type="submit" class="appButtonBlack">Registrati</button> -->
                    <input id="registrami" type="submit" value="Registrami" disabled><span id="registramimessaggio"></span><br>
                </div>
                <div class="row mb-3">
                    <a href="login.jsp" class="appLink">Hai già un account? Accedi</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var passwordOk = false;
    var nomeOk = false;
    var emailOk = false;


    function cambiaStatoRegistrami() {
        if (usernameOk && passwordOk && nomeOk && emailOk){
            document.getElementById('registrami').disabled = false;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            document.getElementById('registrami').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }

    function validaPassword() {
        var inputpw = document.forms['register']['pwd1']
        var inputpwconf = document.forms['register']['pwd2'];
        var password = inputpw.value;
        if (password.length >= 8 && password.toUpperCase() != password
            && password.toLowerCase() != password && /[0-9]/.test(password)){
            inputpw.style.border = borderOk;

            if (password == inputpwconf.value){
                inputpwconf.style.border = borderOk;
                passwordOk = true;
            } else {
                inputpwconf.style.border = borderNo;
                passwordOk = false;
            }
        } else {
            inputpw.style.border = borderNo;
            inputpwconf.style.border = borderNo;
            passwordOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaNome() {
        var input = document.forms['register']['nome'];
        if (input.value.trim().length > 0
            && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            input.style.border = borderOk;
            nomeOk = true;
        }else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaEmail() {
        var input = document.forms['register']['email'];
        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)){
            //verifica se esiste un utente con la stessa email
            var xmlHttpReq = new XMLHttpRequest();   //permette a javascript di interagire direttamente con il  server
            xmlHttpReq.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200
                    && this.responseText == '<ok/>'){
                    emailOk = true;
                    input.style.border = borderOk;
                } else {
                    input.style.border = borderNo;
                    emailOk = false;
                }
                cambiaStatoRegistrami();
            }
            xmlHttpReq.open("GET", "VerificaEmail?email="
                + encodeURIComponent(input.value), true);
            xmlHttpReq.send(); //invia la richiesta al server
        } else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

</script>


</body>
</html>
