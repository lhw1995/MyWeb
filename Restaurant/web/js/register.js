/**
 * Created by lhw on 2016/12/4.
 */
$(document).ready(function () {
    $(".bubble-box").hide();
    $("#registerForm img").hide();
    var registerFlag = [false,false,false,false,false,false];

    //检查用户名输入
    $("#registerForm input[name='userName']").focus(function () {
        $("#userNameBox").html("用户名只能包含数字和大小写字母,长度为8~15");
        $("#userNameBox").show(500);
    })
    $("#registerForm input[name='userName']").blur(function () {
        $("#userNameBox").html("用户名只能包含数字和大小写字母,长度为8~15");
        var hideFlag = true;

        if ($("#registerForm input[name='userName']").val()!='') {
            $.ajax({
                type: 'post',
                url: '/restaurant/checkExists',
                data: $.trim($("#registerForm input[name='userName']").val()),
                async: false,
                success: function (data) {
                    if (data == false) {
                        $("#userNameBox").html("用户名已存在");
                        hideFlag = false;
                        registerFlag[0] = false;
                        $("#userNameBox").show();
                        $("#userNameIcon").attr("src", "/images/registerIcon/false.png");
                        $("#userNameIcon").show();
                        return;
                    }
                },
                error: function (jqXHR) {
                    alert(jqXHR.status);
                }
            })
        }
        if (hideFlag){
            $("#userNameBox").hide();
        }
    })
    $("#registerForm input[name='userName']").keyup(function () {
        var reg = /^[0-9a-zA-Z]{8,15}$/g;
        if (reg.test($("#registerForm input[name='userName']").val())){
            $("#userNameIcon").attr("src","/images/registerIcon/true.png");
            $("#userNameIcon").show();
            registerFlag[0] = true;
        } else {
            $("#userNameIcon").attr("src","/images/registerIcon/false.png");
            $("#userNameIcon").show();
            registerFlag[0] = false;
        }
    })

    //检查显示名输入
    $("#registerForm input[name='showName']").focus(function () {
        $("#showNameBox").html("显示名只能包含汉字、数字和字母，长度为4~10位");
        $("#showNameBox").show(500);
    })
    $("#registerForm input[name='showName']").blur(function () {
        $("#showNameBox").hide(500);
    })
    $("#registerForm input[name='showName']").keyup(function () {
        var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{4,10}$/g;
        if (reg.test($("#registerForm input[name='showName']").val())){
            $("#showNameIcon").attr("src","/images/registerIcon/true.png");
            $("#showNameIcon").show();
            registerFlag[1] = true;
        } else {
            $("#showNameIcon").attr("src","/images/registerIcon/false.png");
            $("#showNameIcon").show();
            registerFlag[1] = false;
        }
    })

    //检查密码输入
    $("#registerForm input[name='password']").focus(function () {
        $("#passwordBox").html("密码长度为10~30");
        $("#passwordBox").show(500);
    })
    $("#registerForm input[name='password']").blur(function () {
        $("#passwordBox").hide(500);
    })
    $("#registerForm input[name='password']").keyup(function () {
        var reg = /^[0-9a-zA-Z]{10,30}$/g;
        if (reg.test($("#registerForm input[name='password']").val())){
            $("#passwordIcon").attr("src","/images/registerIcon/true.png");
            $("#passwordIcon").show();
            registerFlag[2] = true;
        } else {
            $("#passwordIcon").attr("src","/images/registerIcon/false.png");
            $("#passwordIcon").show();
            registerFlag[2] = false;
        }
    })

    //检查确认密码输入
    $("#registerForm input[name='confirmPassword']").focus(function () {
        $("#confirmPasswordBox").html("确认两次密码输入一致");
        $("#confirmPasswordBox").show(500);
    })
    $("#registerForm input[name='confirmPassword']").blur(function () {
        $("#confirmPasswordBox").hide(500);
    })
    $("#registerForm input[name='confirmPassword']").keyup(function () {
        if ($("#registerForm input[name='password']").val() == $("#registerForm input[name='confirmPassword']").val()){
            $("#confirmPasswordIcon").attr("src","/images/registerIcon/true.png");
            $("#confirmPasswordIcon").show();
            registerFlag[3] = true;
        } else {
            $("#confirmPasswordIcon").attr("src","/images/registerIcon/false.png");
            $("#confirmPasswordIcon").show();
            registerFlag[3] = false;
        }
    })

    //检查邮箱输入
    $("#registerForm input[name='email']").focus(function () {
        $("#emailBox").html("注意邮箱格式，例：XXX@XX.XX");
        $("#emailBox").show(500);
    })
    $("#registerForm input[name='email']").blur(function () {
        $("#emailBox").hide(500);
    })
    $("#registerForm input[name='email']").keyup(function () {
        var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;;
        if (reg.test($("#registerForm input[name='email']").val())){
            $("#emailIcon").attr("src","/images/registerIcon/true.png");
            $("#emailIcon").show();
            registerFlag[4] = true;
        } else {
            $("#emailIcon").attr("src","/images/registerIcon/false.png");
            $("#emailIcon").show();
            registerFlag[4] = false;
        }
    })
    //检查电话输入
    $("#registerForm input[name='phone']").focus(function () {
        $("#phoneBox").html("请输入正确的11位电话号码");
        $("#phoneBox").show(500);
    })
    $("#registerForm input[name='phone']").blur(function () {
        $("#phoneBox").hide(500);
    })
    $("#registerForm input[name='phone']").keyup(function () {
        var reg = /^[0-9]{11}$/g;
        if (reg.test($("#registerForm input[name='phone']").val())){
            $("#phoneIcon").attr("src","/images/registerIcon/true.png");
            $("#phoneIcon").show();
            registerFlag[5] = true;
        } else {
            $("#phoneIcon").attr("src","/images/registerIcon/false.png");
            $("#phoneIcon").show();
            registerFlag[5] = false;
        }
    })

    //提交注册信息
    $("input[name='registerButton']").click(function () {

        for (var i = 0; i < registerFlag.length; i ++){
            if (registerFlag[i] == false){
                return;
            }
        }

        var userName = $.trim($("#registerForm input[name='userName']").val());
        var showName = $.trim($("#registerForm input[name='showName']").val());
        var password = $.trim($("#registerForm input[name='password']").val());
        var confirmPassword = $.trim($("#registerForm input[name='confirmPassword']").val());
        var email = $.trim($("#registerForm input[name='email']").val());
        var phone = $.trim($("#registerForm input[name='phone']").val());

        var params = {
            'userName': userName,
            'showName': showName,
            'password': password,
            'email': email,
            'phone': phone
        }
        console.log(params);

        $.ajax({
            type: 'post',
            url: '/restaurant/consumer',
            data: JSON.stringify(params),
            contentType: "application/json",
            async: true,
            success: function (data) {
                if (data.length == 0) {
                    $("#userNameBox").html("用户名已存在");
                    $("#userNameBox").show();
                    $("#userNameIcon").attr("src","/images/registerIcon/false.png");
                    $("#userNameIcon").show();
                    return;
                } else {
                    window.location.href="/restaurant/message?message=registersuc";
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })

    })
})