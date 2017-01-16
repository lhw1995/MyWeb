/**
 * Created by lhw on 2016/12/6.
 */
$(document).ready(function () {
    $(".bubble-box").hide();
    $("#dishNameIcon").hide();
    $("#priceIcon").hide();
    $("#typeIcon").hide();
    $("#inventoryIcon").hide();

    //检查菜品名输入
    $("input[name='dishName']").focus(function () {
        $("#dishNameBox>.wrap").html("菜品名只能包含汉字、数字和字母，长度为2~10位");
        $("#dishNameBox").show(500);
    })
    $("input[name='dishName']").blur(function () {
        $("#dishNameBox").hide(500);
    })
    $("input[name='dishName']").keyup(function () {
        var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
        if (reg.test($("input[name='dishName']").val())) {
            $("#dishNameIcon").attr("src", "/images/registerIcon/true.png");
            $("#dishNameIcon").show();
        } else {
            $("#dishNameIcon").attr("src", "/images/registerIcon/false.png");
            $("#dishNameIcon").show();
        }
    })

    //检查价格输入
    $("input[name='price']").focus(function () {
        $("#priceBox").html("请输入正确的价格");
        $("#priceBox").show(500);
    })
    $("input[name='price']").blur(function () {
        $("#priceBox").hide(500);
    })
    $("input[name='price']").keyup(function () {
        var reg = /^[0-9.]{1,6}$/g;
        if (reg.test($("input[name='price']").val())) {
            $("#priceIcon").attr("src", "/images/registerIcon/true.png");
            $("#priceIcon").show();
        } else {
            $("#priceIcon").attr("src", "/images/registerIcon/false.png");
            $("#priceIcon").show();
        }
    })

    //检查类型输入
    $("input[name='classify']").focus(function () {
        $("#typeBox").html("菜品类型名只能包含汉字、数字和字母，长度为2~10位");
        $("#typeBox").show(500);
    })
    $("input[name='classify']").blur(function () {
        $("#typeBox").hide(500);
    })
    $("input[name='classify']").keyup(function () {
        var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
        if (reg.test($("input[name='classify']").val())) {
            $("#typeIcon").attr("src", "/images/registerIcon/true.png");
            $("#typeIcon").show();
        } else {
            $("#typeIcon").attr("src", "/images/registerIcon/false.png");
            $("#typeIcon").show();
        }
    })

    //检查库存输入
    $("input[name='inventory']").focus(function () {
        $("#inventoryBox").html("库存只能输入数字，长度为1~5位");
        $("#inventoryBox").show(500);
    })
    $("input[name='inventory']").blur(function () {
        $("#inventoryBox").hide(500);
    })
    $("input[name='inventory']").keyup(function () {
        var reg = /^[0-9]{1,5}$/g;
        if (reg.test($("input[name='inventory']").val())) {
            $("#inventoryIcon").attr("src", "/images/registerIcon/true.png");
            $("#inventoryIcon").show();
        } else {
            $("#inventoryIcon").attr("src", "/images/registerIcon/false.png");
            $("#inventoryIcon").show();
        }
    })

})

