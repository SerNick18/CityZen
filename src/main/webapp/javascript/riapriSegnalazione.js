var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var descrizioneOK = false;
var fotoOK = false;

var inoltroForm = document.getElementById("inoltroForm");

function submitForm(){
    if (descrizioneOK&&fotoOK){
        inoltroForm.submit();
    }else {
        validateDescrizione();
        validateFoto();
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
