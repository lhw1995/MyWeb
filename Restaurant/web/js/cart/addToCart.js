/**
 * Created by lhw on 2016/12/24.
 */

function deleteCartDish (obj) {
    var dishId = $(obj).parent().parent().find("td:eq(0)>input").val();
    console.log(dishId);
    $.ajax({
        type: 'post',
        url: '/restaurant/cart/'+dishId,
        data: {
            _method: 'DELETE'
        },
        async: true,
        success: function (data) {
            cartData(data);
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}

function deleteCartDish2 (obj) {
    var dishId = $(obj).parent().parent().find("td:eq(0)>input").val();
    console.log(dishId);
    $.ajax({
        type: 'post',
        url: '/restaurant/cart/'+dishId,
        data: {
            _method: 'DELETE'
        },
        async: true,
        success: function (data) {
            window.location.reload();
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}

function addToCart() {
    var dishId = $("#dishId").val();
    var buyCount = $("input[name='buyCount']").val();
    var params = {
        'dishId':dishId,
        'buyCount':buyCount
    }
    $.ajax({
        type: 'post',
        url: '/restaurant/cart/',
        data: JSON.stringify(params),
        contentType: "application/json",
        async: true,
        success: function (data) {
            cartData(data);
        },
        error: function (jqXHR) {
          //  window.location.href="/restaurant/index";
        }
    })
}


function cartData(data) {
    $("#cart table").empty();
    $("#cart table").append("<tr>"+
        "<td>菜品图</td>"+
        "<td>菜名</td>"+
        "<td>价格</td>"+
        "<td>购买数量</td>"+
        "</tr>");
    for (var i in data) {
        var _tr = '<tr class="cartDish">' +
            // 商品图片
            '<td class="cartDishCover"><input id="cartDishId" type="hidden" value="'+data[i].dishId+'"/><a href="#"><img height="50" width="50" src='+data[i].cover+' /></a></td>' +
            '<td class="cartDishName"><a href="#" class="cart_item_name">'+data[i].dishName+'</a></td>' +
            '<td class="cartDishPrice"><span class="cart_price">￥'+data[i].price+'</span></td>' +
            '<td class="cartDishBuyCount">&nbsp;&nbsp;<span class="cart_count">'+data[i].buyCount+'</span></td>' +
            '<td><img id="deleteCartDishImg" onclick="deleteCartDish(this)" src="/images/food/delete.jpg" width="15" height="15"/></td>' +
            '</tr>';
        $("#cart table").append(_tr)
    }
    $("#cart table").append(
        '<tr>'+
        '<td><input id="settle" type="button" onclick="settle()" value="结算"/></td>'+
        '</tr>');

}
