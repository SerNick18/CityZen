
$("document").ready(function(){
    $(".btn-success").attr("disabled", true);
    var password = $("#pwd1");
    var password2 = $("#pwd2");
    var oldPass = $("#oldPass")
    var passwordPattern=new RegExp("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");


    $("#oldPass").keyup(function () {
        console.log(oldPass.val());
        if(!passwordPattern.test(oldPass.val())){
            oldPass.css("border-color", "red");
        }else{
            oldPass.css("border-color", "green");
        }
    })

    $("#pwd1").keyup(function () {
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