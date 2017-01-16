/**
 * Created by lhw on 2016/12/2.
 */
$(document).ready(function () {
    $("#loginInfo ul").hide();

    $("#showName,#showNameUl").hover(function () {
        $("#showNameUl").show();
    }, function () {
        $("#showNameUl").hide();
        }
    )
    $("#manage,#manageUl").hover(function () {
            $("#manageUl").show();
        }, function () {
            $("#manageUl").hide();
        }
    )

    $("#login").click(function () {
        submit()
    })

})

function loginSubmit(e) {
    if(e.which == 13) {
        submit();
    }
}
function submit() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var remName = $("input[name='remName']:checked").val();
    var params = {
        'userName':userName,
        'password':password,
        'remName':remName
    }

    //检查用户输入
    if ($.trim(userName) == ''){
        $("#loginMessage").html("用户名不能为空");
    } else if ($.trim(password) == '') {
        $("#loginMessage").html("密码不能为空");
    } else {
        $.ajax({
            type: 'post',
            url: '/restaurant/login',
            data: JSON.stringify(params),
            contentType: "application/json",
            //  dataType:'json',
            async: true,
            success: function (data) {
                if (data.length == 0) {
                    $("#loginMessage").html("登录失败，密码错误");
                } else {
                    window.location.reload();
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    }
}