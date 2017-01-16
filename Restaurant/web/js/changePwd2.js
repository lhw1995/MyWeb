/**
 * Created by lhw on 2016/12/11.
 */
$(document).ready(function () {
    $(".bubble-box").hide();
    $("#passwordIcon").hide();
    $("#confirmPasswordIcon").hide();

    //检查密码输入
    $("#changePwdForm input[name='password']").focus(function () {
        $("#passwordBox").html("密码长度为10~30");
        $("#passwordBox").show(500);
    })
    $("#changePwdForm input[name='password']").blur(function () {
        $("#passwordBox").hide(500);
    })
    $("#changePwdForm input[name='password']").keyup(function () {
        var reg = /^[0-9a-zA-Z]{10,30}$/g;
        if (reg.test($("#changePwdForm input[name='password']").val())){
            $("#passwordIcon").attr("src","/images/registerIcon/true.png");
            $("#passwordIcon").show();
        } else {
            $("#passwordIcon").attr("src","/images/registerIcon/false.png");
            $("#passwordIcon").show();
        }
    })

    //检查确认密码输入
    $("#changePwdForm input[name='confirmPassword']").focus(function () {
        $("#confirmPasswordBox").html("确认两次密码输入一致");
        $("#confirmPasswordBox").show(500);
    })
    $("#changePwdForm input[name='confirmPassword']").blur(function () {
        $("#confirmPasswordBox").hide(500);
    })
    $("#changePwdForm input[name='confirmPassword']").keyup(function () {
        if ($("#changePwdForm input[name='password']").val() == $("#changePwdForm input[name='confirmPassword']").val()){
            $("#confirmPasswordIcon").attr("src","/images/registerIcon/true.png");
            $("#confirmPasswordIcon").show();
        } else {
            $("#confirmPasswordIcon").attr("src","/images/registerIcon/false.png");
            $("#confirmPasswordIcon").show();
        }
    })
})