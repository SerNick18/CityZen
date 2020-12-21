$("document").ready(function(){
    $(".alert").hide();
    $("#loading").hide();
    $(".btn-success").attr("disabled", true);

    $("#form-recupera").submit(function(e){
        e.preventDefault();
        $("#loading").show();
        var email= $('#email').val();
        $.ajax({
            type:"POST",
            url: "manda-email",
            data:  {"email":email},
            dataType: "text",
            success: function(data){
                $("#loading").hide();
                $(".alert").hide();
                if(data.includes("ok")){
                    $("#email").val("");
                    //ripristina il colore iniziale del bordo
                    $("#email").css("border-color", "#ced4da");
                    $(".alert-success").show();
                }else if(data.includes("errore")){
                    $(".alert-danger").text("Non siamo riusciti ad inviarti una mail").show();
                }else if(data.includes("non-registrato")){
                    $(".alert-danger").text("L'indirizzo e-mail non risulta presente").show();
                    $("#email").css("border-color", "red");
                }
            },
            error: function () {
                alert("Errore");
                $("#loading").hide();
                $(".alert").hide();
            }
        });

    })

    //controllo pattern email
    var email=$("#email");
    var emailPattern=new RegExp("[A-Za-z.]+[0-9]*@[A-Za-z.]+");
    $("#email").keyup(function (){
        if(!emailPattern.test(email.val())){
            //imposta il colore del bordo a rosso
            email.css("border-color", "red");
            $(".btn-success").attr("disabled", true);
        }else{
            //imposta il colore del bordo a verde
            email.css("border-color", "green");
            $(".btn-success").attr("disabled", false);
        }
    })
})
