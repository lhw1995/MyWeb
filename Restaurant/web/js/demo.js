
//输入了数量
function countChange(obj) {
    var dishId = $(obj).parent().parent().find("td:eq(0)>input").val();
    var buyCount = $(obj).val();
    var params = {
        "dishId":dishId,
        "buyCount":buyCount
    }
    sendCount(params);
}

//点击了加号
function countAdd(obj) {
    var pre = $(obj).parent().find(".count-input").val();
    $(obj).parent().find(".count-input").val(parseInt(pre)+1);
    var buyCount = $(obj).parent().find(".count-input").val();
    var dishId = $(obj).parent().parent().find("td:eq(0)>input").val();
    var params = {
        "dishId":dishId,
        "buyCount":buyCount
    }
    sendCount(params);
}

//点击了减号
function countDelete(obj) {
    var pre = $(obj).parent().find(".count-input").val();
    if (pre == 0){
        return;
    }
    $(obj).parent().find(".count-input").val(parseInt(pre)-1);
    var buyCount = $(obj).parent().find(".count-input").val();
    var dishId = $(obj).parent().parent().find("td:eq(0)>input").val();
    var params = {
        "dishId":dishId,
        "buyCount":buyCount
    }
    sendCount(params);
}
//发送改变数量请求
function sendCount(obj) {
    $.ajax({
        type: 'PUT',
        url: '/restaurant/cart/'+obj.dishId,
        data: JSON.stringify(obj),
        contentType: "application/json",
        async: true,
        success: function (data) {
            window.location.reload();
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}
//结算
function formSettle() {
    var length = $("#cartTable tr").length;
    if (length > 1) {
        $("#_form").submit();
    }
}