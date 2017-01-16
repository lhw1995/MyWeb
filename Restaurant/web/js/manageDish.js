$(document).ready(function () {

    //跳转到首页
    $.ajax({
        type: 'get',
        url: '/restaurant/dish',
        data: {
            currentPage: 1
        },
        async: true,
        success: function (data) {
            $("#currentPage").html("当前页面:" + data.page.currPageNo);
            $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
            for (var i in data.dishCurList) {
                var _tr = "<tr>" +
                    "<td>" + data.dishCurList[i].id + "</td>" +
                    "<td>" + data.dishCurList[i].dishName + "</td>" +
                    "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                    "<td>￥" + data.dishCurList[i].price + "</td>" +
                    "<td>" + data.dishCurList[i].classify + "</td>" +
                    "<td>" + data.dishCurList[i].score + "</td>" +
                    "<td>" + data.dishCurList[i].inventory + "</td>" +
                    "<td><input type='button' value='删除' onclick='deleteDish(this)'/></td>" +
                    "<td><input type='button' value='修改' onclick='changeDish(this)'/></td>" +
                    "<td id='note' style='color: red'></td>" +
                    "</tr>" +
                    "<tr><td colspan='8'><hr/></td></tr>";
                $("#dishTable").append(_tr);
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
    //跳转到首页
    $("#firstPage").click(function () {
        $.ajax({
            type: 'get',
            url: '/restaurant/dish',
            data: {
                currentPage: 1
            },
            async: true,
            success: function (data) {
                $("#currentPage").html("当前页面:" + data.page.currPageNo);
                $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
                $("#dishTable").empty();
                $("#dishTable").append( "<tr>" +
                    "<td>菜品id</td>" +
                    "<td>菜品名</td>" +
                    "<td>菜品图</td>" +
                    "<td>菜品价格</td>" +
                    "<td>菜品类型</td>" +
                    "<td>菜品评分</td>" +
                    "<td>菜品库存</td>" +
                    "</tr>" +
                    "<tr><td colspan='8'><hr/></td></tr>");

                for (var i in data.dishCurList) {
                    var _tr = "<tr>" +
                        "<td>" + data.dishCurList[i].id + "</td>" +
                        "<td>" + data.dishCurList[i].dishName + "</td>" +
                        "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                        "<td>￥" + data.dishCurList[i].price + "</td>" +
                        "<td>" + data.dishCurList[i].classify + "</td>" +
                        "<td>" + data.dishCurList[i].score + "</td>" +
                        "<td>" + data.dishCurList[i].inventory + "</td>" +
                        "<td><input type='button' value='删除' onclick='deleteDish(this)'/></td>" +
                        "<td><input type='button' value='修改' onclick='changeDish(this)'/></td>" +
                        "<td id='note' style='color: red'></td>" +
                        "</tr>" +
                        "<tr><td colspan='8'><hr/></td></tr>";
                    $("#dishTable").append(_tr);
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    })

    //跳转到上一页
    $("#prePage").click(function () {
        $.ajax({
            type: 'get',
            url: '/restaurant/dish',
            data: {
                currentPage: parseInt($("#currentPage").html().substring(5)) - 1
            },
            async: true,
            success: function (data) {
                $("#currentPage").html("当前页面:" + data.page.currPageNo);
                $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
                $("#dishTable").empty();
                $("#dishTable").append( "<tr>" +
                    "<td>菜品id</td>" +
                    "<td>菜品名</td>" +
                    "<td>菜品图</td>" +
                    "<td>菜品价格</td>" +
                    "<td>菜品类型</td>" +
                    "<td>菜品评分</td>" +
                    "<td>菜品库存</td>" +
                    "</tr>" +
                    "<tr><td colspan='8'><hr/></td></tr>");

                for (var i in data.dishCurList) {
                    var _tr = "<tr>" +
                        "<td>" + data.dishCurList[i].id + "</td>" +
                        "<td>" + data.dishCurList[i].dishName + "</td>" +
                        "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                        "<td>￥" + data.dishCurList[i].price + "</td>" +
                        "<td>" + data.dishCurList[i].classify + "</td>" +
                        "<td>" + data.dishCurList[i].score + "</td>" +
                        "<td>" + data.dishCurList[i].inventory + "</td>" +
                        "<td><input type='button' value='删除' onclick='deleteDish(this)'/></td>" +
                        "<td><input type='button' value='修改' onclick='changeDish(this)'/></td>" +
                        "<td id='note' style='color: red'></td>" +
                        "</tr>" +
                        "<tr><td colspan='8'><hr/></td></tr>";
                    $("#dishTable").append(_tr);
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    })

    //跳转到下一页
    $("#nextPage").click(function () {
        $.ajax({
            type: 'get',
            url: '/restaurant/dish',
            data: {
                currentPage: parseInt($("#currentPage").html().substring(5)) + 1
            },
            async: true,
            success: function (data) {
                $("#currentPage").html("当前页面:" + data.page.currPageNo);
                $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
                $("#dishTable").empty();
                $("#dishTable").append( "<tr>" +
                    "<td>菜品id</td>" +
                    "<td>菜品名</td>" +
                    "<td>菜品图</td>" +
                    "<td>菜品价格</td>" +
                    "<td>菜品类型</td>" +
                    "<td>菜品评分</td>" +
                    "<td>菜品库存</td>" +
                    "</tr>" +
                    "<tr><td colspan='8'><hr/></td></tr>");

                for (var i in data.dishCurList) {
                    var _tr = "<tr>" +
                        "<td>" + data.dishCurList[i].id + "</td>" +
                        "<td>" + data.dishCurList[i].dishName + "</td>" +
                        "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                        "<td>￥" + data.dishCurList[i].price + "</td>" +
                        "<td>" + data.dishCurList[i].classify + "</td>" +
                        "<td>" + data.dishCurList[i].score + "</td>" +
                        "<td>" + data.dishCurList[i].inventory + "</td>" +
                        "<td><input type='button' value='删除' onclick='deleteDish(this)'/></td>" +
                        "<td><input type='button' value='修改' onclick='changeDish(this)'/></td>" +
                        "<td id='note' style='color: red'></td>" +
                        "</tr>" +
                        "<tr><td colspan='8'><hr/></td></tr>";
                    $("#dishTable").append(_tr);
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    })

    //跳转到尾页
    $("#lastPage").click(function () {
        $.ajax({
            type: 'get',
            url: '/restaurant/dish',
            data: {
                currentPage: parseInt($("#totalPageCount").html().substring(5))
            },
            async: true,
            success: function (data) {
                $("#currentPage").html("当前页面:" + data.page.currPageNo);
                $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
                $("#dishTable").empty();
                $("#dishTable").append( "<tr>" +
                    "<td>菜品id</td>" +
                    "<td>菜品名</td>" +
                    "<td>菜品图</td>" +
                    "<td>菜品价格</td>" +
                    "<td>菜品类型</td>" +
                    "<td>菜品评分</td>" +
                    "<td>菜品库存</td>" +
                    "</tr>" +
                    "<tr><td colspan='8'><hr/></td></tr>");

                for (var i in data.dishCurList) {
                    var _tr = "<tr>" +
                        "<td>" + data.dishCurList[i].id + "</td>" +
                        "<td>" + data.dishCurList[i].dishName + "</td>" +
                        "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                        "<td>￥" + data.dishCurList[i].price + "</td>" +
                        "<td>" + data.dishCurList[i].classify + "</td>" +
                        "<td>" + data.dishCurList[i].score + "</td>" +
                        "<td>" + data.dishCurList[i].inventory + "</td>" +
                        "<td><input type='button' value='删除' onclick='deleteDish(this)'/></td>" +
                        "<td><input type='button' value='修改' onclick='changeDish(this)'/></td>" +
                        "<td id='note' style='color: red'></td>" +
                        "</tr>" +
                        "<tr><td colspan='8'><hr/></td></tr>";
                    $("#dishTable").append(_tr);
                }
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    })
})
function deleteDish(obj) {
    var id = $(obj).parent().parent().find("td").eq(0).html();
    $.ajax({
        type: 'post',
        url: '/restaurant/dish/'+id,
        data: {
            _method: 'DELETE'
        },
        async: true,
        success: function (data) {
            window.location.reload()
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}
function changeDish(obj) {
    var id = $(obj).parent().parent().find("td").eq(0).html();
    console.log("aaa");
    window.location.href='/restaurant/dishinfo/'+id;
}