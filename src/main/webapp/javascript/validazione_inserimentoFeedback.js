var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var radioOK = false;
var descrizioneOK = false;


var inoltroForm = document.getElementById("inoltroForm");

function submitForm(){
    if (radioOK&&descrizioneOK){
        inoltroForm.submit();
    }else {
        validateDescrizione();
        validateRadioButton();
    }
}

function validateDescrizione(){
    var descrizione = document.getElementById("descrizione");
    var messageDescrizione = document.getElementById("messageDescrizione");
    if (descrizione.value.length >= 10 && descrizione.value.length <= 500){
        descrizione.style.border = borderOk;
        descrizioneOK = true;
        messageDescrizione.innerHTML = "";
    }else {
        descrizione.style.border = borderNo;
        descrizioneOK = false;
        messageDescrizione.innerHTML = "La descrizione deve essere lunga minimo 10 caratteri e massimo 500.";
    }
}

function validateRadioButton(){
    var radio = document.getElementsByName("valutazione");
    var messageRadio = document.getElementById("messageRadio");

    var i = 0;
    while (!radioOK && i < radio.length) {
        if (radio[i].checked) radioOK = true;
        i++;
    }
    if(!radioOK)
        messageRadio.innerHTML="Devi selezionare una valutazione."
}