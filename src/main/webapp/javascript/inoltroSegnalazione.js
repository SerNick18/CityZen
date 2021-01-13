var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var oggettoOK = false;
var descrizioneOK = false;
var viaOK = false;
var civicoOK = false;
var fotoOK = false;

var inoltroForm = document.getElementById("inoltroForm");

function submitForm(){
    if (oggettoOK&&descrizioneOK&&viaOK&&civicoOK&&fotoOK){
        inoltroForm.submit();
    }else {
        validateOggetto();
        validateDescrizione();
        validateCivico();
        validateVia();
        validateFoto();
    }
}

function validateOggetto(){
    var oggetto = document.getElementById("oggetto");
    var messageOggetto = document.getElementById("messageOggetto");
    if (oggetto.value.match(/^([A-Za-z0-9']\s*){4,25}$/)){
        oggetto.style.border = borderOk;
        oggettoOK = true;
        messageOggetto.innerHTML = "";
    }else {
        oggetto.style.border = borderNo;
        oggettoOK = false;
        messageOggetto.innerHTML = "L'oggetto deve essere lungo minimo 4 e massimo 25 caratteri. " +
            "Non può contenere caratteri speciali.";
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

function validateVia(){
    var via = document.getElementById("via");
    var messageVia = document.getElementById("messageVia");
    if (via.value.match(/^([A-Za-z0-9]\s*){2,200}$/)){
        via.style.border = borderOk;
        viaOK = true;
        messageVia.innerHTML = ""
    }else {
        via.style.border = borderNo;
        viaOK = false;
        messageVia.innerHTML = "La via deve essere lunga minimo 2 e massimo 200 caratteri. " +
        "Non può contenere caratteri speciali"
    }
}

function validateCivico(){
    var civico = document.getElementById("civico");
    var messageCivico = document.getElementById("messageCivico");
    if (civico.value.match(/^[0-9]{1,5}$/)){
        civico.style.border = borderOk;
        messageCivico.innerHTML = ""
        civicoOK = true;
    }else {
        civico.style.border = borderNo;
        civicoOK = false;
        messageCivico.innerHTML = "Il numero civico non è valido."
    }
}

function validateFoto(){
    var foto = document.getElementById("foto");
    var messageFoto = document.getElementById("messageFoto");
    if (foto.files.length>0){
        foto.style.border = borderOk;
        messageFoto.innerHTML = ""
        fotoOK = true;
        setPhotoThumbnail(foto.files);
    }else {
        foto.style.border = borderNo;
        fotoOK = false;
        messageFoto.innerHTML = "E' obbligatorio allegare una foto alla segnalazione";
    }
}

function setPhotoThumbnail(files) {
    if (FileReader && files){
        var fr = new FileReader();
        fr.onload = function (){
            document.getElementById("fotoImg").src = fr.result;
        }
        fr.readAsDataURL(files[0]);
    }else {

    }

}
