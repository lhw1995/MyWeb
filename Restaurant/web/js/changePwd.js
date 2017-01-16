/**
 * Created by lhw on 2016/12/11.
 */
function refreshCaptcha() {
    $("#captchaImg").attr("src", "/restaurant/captcha?=" + new Date().getTime());
}

$(function() {

    refreshCaptcha();

    $("#captchaImg").click(function (){
        refreshCaptcha();
    });
});

function checkForm() {
    if($("#captcha").val() == null || $("#captcha").val() == "") {
        $("#message").text("请填写验证码");
        return false;
    }
}