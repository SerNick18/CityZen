var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var passwordOk = false;
var nomeOk = false;
var cognomeOk = false;
var cfOk = false;
var viaOk = false;
var civicoOk = false;
var cittaOk = false;
var emailOk = false;


function cambiaStatoRegistrami() {
    if (nomeOk && passwordOk && cognomeOk && emailOk && civicoOk && cittaOk && cfOk && viaOk){
        document.getElementById('registrami').disabled = false;
        document.getElementById('registramimessaggio').innerHTML = '';
    } else {
        document.getElementById('registrami').disabled = true;
//document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
    }
}


function validaNome() {
    var input = document.forms['loginForm']['nome'];
    if (input.value.trim().length > 0
        && input.value.match(/^[A-Za-z]+$/)){
        input.style.border = borderOk;
        nomeOk = true;
        document.getElementById('registramimessaggio').innerHTML = '';
    }else {
        input.style.border = borderNo;
        nomeOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Il nome deve contenere solo lettere.';
    }
    cambiaStatoRegistrami();

}

function validaCognome() {
    var input = document.forms['loginForm']['cognome'];
    if (input.value.trim().length > 0
        && input.value.match(/^[A-Za-z]+$/)){
        input.style.border = borderOk;
        cognomeOk = true;
        document.getElementById('registramimessaggio').innerHTML = '';
    }else {
        input.style.border = borderNo;
        cognomeOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Il cognome deve contenere solo lettere.';
    }
    cambiaStatoRegistrami();

}

function validaEmail() {
    var input = document.forms['loginForm']['email'];
    if (input.value.match(/[A-Za-z.]+[0-9]*@[A-Za-z.]+/)){
//verifica se esiste un utente con la stessa email
        var xmlHttpReq = new XMLHttpRequest();   //permette a javascript di interagire direttamente con il  server
        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>'){
                emailOk = true;
                input.style.border = borderOk;
                document.getElementById('registramimessaggio').innerHTML = '';
            } else {
                input.style.border = borderNo;
                emailOk = false;
                document.getElementById('registramimessaggio').innerHTML = 'Email già esistente .';
            }
            cambiaStatoRegistrami();

        }
        xmlHttpReq.open("GET", "VerificaEmail?email="
            + encodeURIComponent(input.value), true);
        xmlHttpReq.send(); //invia la richiesta al server
    } else {
        input.style.border = borderNo;
        emailOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Email non  valida.';
    }
    cambiaStatoRegistrami();

}

function validaCodiceFiscale() {
    var input = document.forms['loginForm']['cf'];
    if (input.value.trim().length > 0
        && input.value.match(/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/)) {
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>') {
                cfOk = true;
                input.style.border = borderOk;
                document.getElementById('registramimessaggio').innerHTML = '';
            } else {
                input.style.border = borderNo;
                cfOk = false;
                document.getElementById('registramimessaggio').innerHTML = 'Codice fiscale già esistente.';
            }
            cambiaStatoRegistrami();
        }
        xmlHttpReq.open("GET", "VerificaCodiceFiscale?cf="
            + encodeURIComponent(input.value), true);
        xmlHttpReq.send(); //invia la richiesta al server
    }else {
        input.style.border = borderNo;
        cfOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Codice fiscale non valido o già esistente';
    }
    cambiaStatoRegistrami();

}

function validaCivico() {
    var input = document.forms['loginForm']['civico'];
    if (input.value.trim().length > 0
        && input.value.match(/^[0-9]{1,3}$/)){
        input.style.border = borderOk;
        civicoOk = true;
        document.getElementById('registramimessaggio').innerHTML = '';
    }else {
        input.style.border = borderNo;
        civicoOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Civico non  valido.';
    }
    cambiaStatoRegistrami();

}

function validaVia() {
    var input = document.forms['loginForm']['via'];
    if (input.value.trim().length > 0
        && input.value.match(/^([A-Za-z]\s?)*$/)){
        input.style.border = borderOk;
        viaOk = true;
        document.getElementById('registramimessaggio').innerHTML = '';
    }else {
        input.style.border = borderNo;
        viaOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Via non  valida.';
    }
    cambiaStatoRegistrami();

}


function validaCitta() {
    var input = document.forms['loginForm']['citta'];
    if (input.value.trim().length > 0
        && input.value.match(/^[A-Za-z]+$/)){
        input.style.border = borderOk;
        cittaOk = true;
        document.getElementById('registramimessaggio').innerHTML = '';
    }else {
        input.style.border = borderNo;
        cittaOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'Città non  valida.';
    }
    cambiaStatoRegistrami();

}

function validaPassword() {
    var inputpw = document.forms['loginForm']['pwd1']
    var inputpwconf = document.forms['loginForm']['pwd2'];
    var password = inputpw.value;
    if (password.length >= 8 && password.toUpperCase() != password
        && password.toLowerCase() != password && /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password)){
        inputpw.style.border = borderOk;

        if (password == inputpwconf.value){
            inputpwconf.style.border = borderOk;
            passwordOk = true;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            inputpwconf.style.border = borderNo;
            passwordOk = false;
            document.getElementById('registramimessaggio').innerHTML = 'La password deve contenere almeno 8 caratteri, ' +
                'almeno una lettera maiuscola, una lettera minuscola,\n' +
                '             * un numero.';
        }
    } else {
        inputpw.style.border = borderNo;
        inputpwconf.style.border = borderNo;
        passwordOk = false;
        document.getElementById('registramimessaggio').innerHTML = 'La password deve contenere almeno 8 caratteri, ' +
            'almeno una lettera maiuscola, una lettera minuscola,\n' +
            '             * un numero.';
    }
    cambiaStatoRegistrami();

}
