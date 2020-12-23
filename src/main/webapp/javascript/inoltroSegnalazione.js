var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';

function validateOggetto(){
    var oggetto = document.getElementById("oggetto");
    if (oggetto.value.match(/[A-Za-z0-9]{4,25}/)){
        oggetto.style.border = borderOk;
    }else {

    }
}