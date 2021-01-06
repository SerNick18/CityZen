$("document").ready(function () {
    $(".myBtnPink").hide();
    var nSegnalazioni=document.getElementsByTagName("tbody")[0].childElementCount;
    if(nSegnalazioni==20){
        $(".myBtnPink").show();
    }
    var offset=20;
    $(".myBtnPink").click(function () {
        console.log("cliccato");
        var tipo=$('input[name=tipo]').val();
        //recupera lo stato delle segnalazioni da caricare
        var stato=$('input[name=stato]').val();
        console.log(tipo);
        if(tipo!=null && tipo.includes("proprie-segnalazioni")){
            var json_to_send={"offset": offset, "tipo": tipo};
        }else{
            var json_to_send={"stato":stato, "offset": offset};
        }
        //chiamata ajax
        $.ajax({
            type: "POST",
            url: "carica-altre-segnalazioni",
            data:  json_to_send,
            dataType: "json",
            timeout: 100000,
            success: function(data){
                console.log(data);
                if(data.length==0)
                    return;
                offset+=20;
                var tbody=document.getElementsByTagName("tbody");
                for(var i=0; i<data.length; i++){
                    var tr=document.createElement("tr");

                    var tdOggetto=document.createElement("td");
                    var tdStato=document.createElement("td");
                    var tdNome=document.createElement("td");
                    var tdSolleciti=document.createElement("td");
                    var tdPriorita=document.createElement("td");
                    var tdRiaperta=document.createElement("td");
                    var tdFeedback=document.createElement("td");
                    var tdSollecitoButton=document.createElement("td");

                    //oggetto
                    var a=document.createElement("a");
                    a.setAttribute("href", "dettagli?id="+data[i].id);
                    a.setAttribute("class", "greyText");
                    a.innerText=data[i].oggetto;
                    tdOggetto.appendChild(a);
                    tr.appendChild(tdOggetto);

                    //proprie segnalazioni cittadino
                    if(tipo!=null && tipo.includes("proprie-segnalazioni")){
                        //stato
                        tdStato.innerText=data[i].stato;
                        tr.appendChild(tdStato);

                        //numero solleciti
                        tdSolleciti.innerText=data[i].numSolleciti;
                        tr.appendChild(tdSolleciti);

                        //priorita
                        tdPriorita.innerText=data[i].priorita;
                        tr.appendChild(tdPriorita);

                        //riaperta
                        if(data[i].riaperta==0){
                            tdRiaperta.innerText="Non riaperta";
                        }else{
                            var a=document.createElement("a");
                            a.setAttribute("href", "dettagli?id="+data[i].riaperta);
                            a.innerText="Visualizza";
                            tdRiaperta.appendChild(a);
                        }
                        tr.appendChild(tdRiaperta);
                    }else{ //altre segnalazioni cittadino e impiegato
                        //nome
                        tdNome.innerText=data[i].c.nome;
                        tr.appendChild(tdNome);
                        //modifica per tabella approvate del cittadino
                        if(tipo!=null && tipo.includes("approvate-cittadino")){
                            console.log("aaa");
                            //priorita
                            tdPriorita.innerText=data[i].priorita;
                            tr.appendChild(tdPriorita);

                            //riaperta
                            if(data[i].segnalazione.riaperta==0){
                                tdRiaperta.innerText="Non riaperta";
                            }else{
                                var a=document.createElement("a");
                                a.setAttribute("href", "dettagli?id="+data[i].segnalazione.riaperta);
                                a.innerText="Visualizza";
                                tdRiaperta.appendChild(a);
                            }
                            tr.appendChild(tdRiaperta);

                            //numero solleciti
                            tdSolleciti.innerText=data[i].numSolleciti;
                            tr.appendChild(tdSolleciti);

                            var a=document.createElement("a");
                            a.setAttribute("href", "inoltroSol?id="+data[i].id);
                            a.setAttribute("style", "text-decoration: none;");
                            a.setAttribute("class", "btn-Sol");
                            a.innerText="+";
                            tdSollecitoButton.appendChild(a);
                            tr.appendChild(tdSollecitoButton);

                        }else{ //tabella standard impiegato (oggetto, nome, numero solleciti, priorità, riaperto da)
                            console.log("bbb");
                            //numero solleciti
                            tdSolleciti.innerText=data[i].numSolleciti;
                            tr.appendChild(tdSolleciti);

                            //priorita
                            tdPriorita.innerText=data[i].priorita;
                            tr.appendChild(tdPriorita);

                            //riaperta
                            if(data[i].segnalazione.riaperta==0){
                                tdRiaperta.innerText="Non riaperta";
                            }else{
                                var a=document.createElement("a");
                                a.setAttribute("href", "dettagli?id="+data[i].segnalazione.riaperta);
                                a.innerText="Visualizza";
                                tdRiaperta.appendChild(a);
                            }
                            tr.appendChild(tdRiaperta);
                        }
                        //modifica per tabella chiuse del cittadino
                        if(tipo!=null && tipo.includes("chiuse-cittadino")){
                            var a=document.createElement("a");
                            a.setAttribute("href", "inserimentoFeedback?id="+data[i].id+"&provenienza=listaChiuse");
                            a.innerText="Inserisci feedback";
                            tdFeedback.appendChild(a);
                            tr.appendChild(tdFeedback);
                        }
                    }

                    tbody[0].appendChild(tr);
                }
                if(data.length<20){
                    $(".myBtnPink").hide();
                }
            },
            error: function () {
                alert("Errore");
            }
        });
    })


})