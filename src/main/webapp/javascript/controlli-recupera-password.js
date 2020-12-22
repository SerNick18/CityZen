$("document").ready(function(){
    $(".btn-success").attr("disabled", true);
    var password = $("#pwd");
    var password2 = $("#pwd2");
    var passwordPattern=new RegExp("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");

    $("#pwd").keyup(function () {
        console.log(password.val());
        if(!passwordPattern.test(password.val())){
            password.css("border-color", "red");
        }else{
            password.css("border-color", "green");
        }
    })

    $("#pwd2").keyup(function () {
        if(password.val()!=password2.val()){
            password2.css("border-color", "red");
        }else{
            password2.css("border-color", "green");
            $(".btn-success").attr("disabled", false);
        }
    })
})
