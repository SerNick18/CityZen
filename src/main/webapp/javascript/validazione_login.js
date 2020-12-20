var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var emailOk = false;
var passwordOk = false;
function cambiaStatoRegistrami() {
    if (emailOk && passwordOk){
        document.getElementById('loginId').disabled = false;
        document.getElementById('loginmessaggio').innerHTML = '';
    } else {
        document.getElementById('loginId').disabled = true;
        document.getElementById('loginmessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
    }
}
function validaEmail() {
    var input = document.forms['loginAccesso']['email'];
    if (input.value.match(/[A-Za-z.]+[0-9]*@[A-Za-z.]+/)){
        input.style.border = borderOk;
        emailOk = true;
    } else {
        input.style.border = borderNo;
        emailOk = false;
        document.getElementById('loginmessaggio').innerHTML = 'Email non  valida.';
    }
    cambiaStatoRegistrami();
}
function validaPassword() {
    var inputpw = document.forms['loginAccesso']['pwd']
    var password = inputpw.value;
    if (password.length >= 8 && password.toUpperCase() != password
        && password.toLowerCase() != password && /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/.test(password)){
        inputpw.style.border = borderOk;
        passwordOk = true;
    } else {
        inputpw.style.border = borderNo;
        passwordOk = false;
        document.getElementById('loginmessaggio').innerHTML = 'La password deve contenere almeno 8 caratteri, ' +
            'almeno una lettera maiuscola, una lettera minuscola,\n' +
            '             * un numero ed un carattere speciale.';
    }
    cambiaStatoRegistrami();
}
