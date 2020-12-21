$("document").ready(function(){
    $(".alert").hide();
    $("#loading").hide();

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
                    $(".alert-success").show();
                }else if(data.includes("errore")){
                    $(".alert-danger").text("Non siamo riusciti ad inviarti una mail").show();
                }else if(data.includes("non-registrato")){
                    $(".alert-danger").text("L'indirizzo e-mail non risulta presente").show();
                }
            },
            error: function () {
                alert("Errore");
                $("#loading").hide();
                $(".alert").hide();
            }
        });

    })
})
