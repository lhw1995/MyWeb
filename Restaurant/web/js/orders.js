/**
 * Created by lhw on 2016/12/29.
 */
function confirmOrder(obj) {
    var orderId = $(obj).parent().parent().parent().find("#orderIdSpan").html();
    $.ajax({
        type:'PUT',
        url:'/restaurant/order/'+orderId,
        contentType: "application/json",
        async: true,
        success: function (data) {
            $(obj).parent().html("已确认收货");
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}

function submit(obj) {
    $(obj).parent().parent().find("._form").submit();
  //  console.log($(obj).parent().html());
}