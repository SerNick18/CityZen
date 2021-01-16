var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var emailOk = false;
var passwordOk = false;
var loginForm = document.getElementById("loginAccesso");
function submitLogin(){
    if (emailOk&&passwordOk){
        loginForm.submit();
    }else{
        validaEmail();
        validaPassword();
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
            '             * un numero.';
    }
}
