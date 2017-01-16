/**
 * Created by lhw on 2016/12/22.
 */
function search(e) {
    // 回车键事件
    if(e.which == 13) {
        console.log($("input[name='search']").val());
        $.ajax({
            type: 'get',
            url: '/restaurant/dish/'+$("input[name='search']").val(),
            data: {
                currentPage: parseInt($("#currentPage").html().substring(5)) - 1
            },
            async: true,
            success: function (data) {
                useData(data)

            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    }
};

//获得推荐菜品
$(document).ready(function () {
    $.ajax({
        type:'get',
        url:'/restaurant/dish/recmd',
        data:{},
        async:true,
        success: function (data) {
            $("#recmdTable table").append("<tr><td style='text-align: center'><b>推荐菜品</b></td></tr>");
            for (var i in data) {
                var _tr = "<tr>" +
                    "<td><a href='/restaurant/singledish/"+data[i].id+"'>" +
                    "<img src='"+data[i].cover+"' height='120' width='120'/>" +
                    "</a></td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>"+data[i].dishName+"</td>" +
                    "</tr>";
                $("#recmdTable table").append(_tr);
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
})
