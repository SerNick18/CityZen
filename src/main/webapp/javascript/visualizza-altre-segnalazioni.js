$("document").ready(function () {
    $(".btn-success").hide();
    var nSegnalazioni=document.getElementsByTagName("tbody")[0].childElementCount;
    if(nSegnalazioni==20){
        $(".btn-success").show();
    }
    var offset=20;
    $(".btn-success").click(function () {
        //recupera lo stato delle segnalazioni da caricare
        var stato=$('input[type=hidden]').val();
        //chiamata ajax
        $.ajax({
            type: "POST",
            url: "carica-altre-segnalazioni",
            data:  {"stato":stato, "offset": offset},
            dataType: "json",
            timeout: 100000,
            success: function(data){
                if(data.length==0)
                    return;
                offset+=20;
                var tbody=document.getElementsByTagName("tbody");
                for(var i=0; i<data.length; i++){
                    var tr=document.createElement("tr");

                    var tdOggetto=document.createElement("td");
                    var tdNome=document.createElement("td");
                    var tdSolleciti=document.createElement("td");
                    var tdPriorita=document.createElement("td");
                    var tdRiaperta=document.createElement("td");

                    //oggetto
                    var a=document.createElement("a");
                    a.setAttribute("href", "dettagli?id="+data[i].id);
                    a.innerText=data[i].oggetto;
                    tdOggetto.appendChild(a);
                    tr.appendChild(tdOggetto);

                    //nome
                    tdNome.innerText=data[i].c.nome;
                    tr.appendChild(tdNome);

                    //numero solleciti
                    tdSolleciti.innerText=data[i].numSolleciti;
                    tr.appendChild(tdSolleciti);

                    //priorita
                    tdPriorita.innerText=data[i].priorita;
                    tr.appendChild(tdPriorita);

                    //riaperta
                    if(data[i].segnalazione.riaperta==0){
                        tdRiaperta.innerText="Nessuno";
                    }else{
                        tdRiaperta.innerText=data[i].segnalazione.riaperta
                    }
                    tr.appendChild(tdRiaperta);

                    tbody[0].appendChild(tr);
                }
                if(data.length<20){
                    $(".btn-success").hide();
                }
            },
            error: function () {
                alert("Errore");
            }
        });
    })


})