/**
 * Created by lhw on 2016/12/6.
 */
$(document).ready(function () {
    $(".bubble-box").hide();
    $("#showNameIcon").hide();
    $("#phoneIcon").hide();
    var registerFlag = [false, false,false];

    //检查显示名输入
    $("input[name='showName']").focus(function () {
        $("#showNameBox>.wrap").html("显示名只能包含汉字、数字和字母，长度为4~10位");
        $("#showNameBox").show(500);
    })
    $("input[name='showName']").blur(function () {
        $("#showNameBox").hide(500);
    })
    $("input[name='showName']").keyup(function () {
        var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{4,10}$/g;
        if (reg.test($("input[name='showName']").val())) {
            $("#showNameIcon").attr("src", "/images/registerIcon/true.png");
            $("#showNameIcon").show();
            registerFlag[0] = true;
        } else {
            $("#showNameIcon").attr("src", "/images/registerIcon/false.png");
            $("#showNameIcon").show();
            registerFlag[0] = false;
        }
    })

    //检查电话输入
    $("input[name='phone']").focus(function () {
        $("#phoneBox").html("请输入正确的11位电话号码");
        $("#phoneBox").show(500);
    })
    $("input[name='phone']").blur(function () {
        $("#phoneBox").hide(500);
    })
    $("input[name='phone']").keyup(function () {
        var reg = /^[0-9]{11}$/g;
        if (reg.test($("input[name='phone']").val())) {
            $("#phoneIcon").attr("src", "/images/registerIcon/true.png");
            $("#phoneIcon").show();
            registerFlag[1] = true;
        } else {
            $("#phoneIcon").attr("src", "/images/registerIcon/false.png");
            $("#phoneIcon").show();
            registerFlag[1] = false;
        }
    })


})

//下面用于图片上传预览功能
