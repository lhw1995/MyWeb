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
                var score = parseInt(data.dishCurList[i].score);
                var star2Flag = false;
                if (data.dishCurList[i].score%2 != 0){
                    star2Flag = true;
                }
                var _star = "";
                for (var j = 0; j < parseInt(score/2);j ++) {
                    _star = _star+"<li><img src='/images/star.png' width='30' height='30'/></li>";
                }
                if (star2Flag) {
                    _star = _star+"<li><img src='/images/star2.png' width='30' height='30'/></li>";
                }

                var _tr = "<tr>" +
                    "<td id='dishName'><a href='/restaurant/singledish/"+data.dishCurList[i].id+"'>" + data.dishCurList[i].dishName + "</a></td>" +
                    "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
                    "<td style='color: #ff6e19;font-size: 23px;'>￥" + data.dishCurList[i].price + "</td>" +
                    "<td>" + data.dishCurList[i].classify + "</td>" +
                    "<td>" + parseFloat(data.dishCurList[i].score).toFixed(1) +
                    "<ul>" +
                    _star+
                    "</ul>" +
                    "</td>" +
                    "<td><input type='hidden' value='"+data.dishCurList[i].id+"'/></td>" +
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
                useData(data)

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
                useData(data)

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
                useData(data)

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
               useData(data)
            },
            error: function (jqXHR) {
                alert(jqXHR.status);
            }
        })
    })
})

function useData(data) {
    $("#currentPage").html("当前页面:" + data.page.currPageNo);
    $("#totalPageCount").html("页面总数:" + data.page.totalPageCount);
    $("#dishTable").empty();
    $("#dishTable").append( "<tr>" +
        "<td><b>菜品名</b></td>" +
        "<td><b>菜品图</b></td>" +
        "<td><b>菜品价格</b></td>" +
        "<td><b>菜品类型</b></td>" +
        "<td><b>菜品评分</b></td>" +
        "</tr>" +
        "<tr><td colspan='8'><hr/></td></tr>");

    for (var i in data.dishCurList) {
        var score = parseInt(data.dishCurList[i].score);
        var star2Flag = false;
        if (data.dishCurList[i].score%2 != 0){
            star2Flag = true;
        }
        var _star = "";
        for (var j = 0; j < parseInt(score/2);j ++) {
            _star = _star+"<li><img src='/images/star.png' width='30' height='30'/></li>";
        }
        if (star2Flag) {
            _star = _star+"<li><img src='/images/star2.png' width='30' height='30'/></li>";
        }


        var _tr = "<tr>" +
            "<td id='dishName'><a href='/restaurant/singledish/"+data.dishCurList[i].id+"'>" + data.dishCurList[i].dishName + "</a></td>" +
            "<td><img src='" + data.dishCurList[i].cover + "' height='100px' width='100px'/></td>" +
            "<td style='color: #ff6e19;font-size: 23px;'>￥" + data.dishCurList[i].price + "</td>" +
            "<td>" + data.dishCurList[i].classify + "</td>" +
            "<td>" + parseFloat(data.dishCurList[i].score).toFixed(1) +
            "<ul>" +
            _star+
            "</ul>" +
            "</td>" +
            "<td><input type='hidden' value='"+data.dishCurList[i].id+"'/></td>" +
            "</tr>" +
            "<tr><td colspan='8'><hr/></td></tr>";
        $("#dishTable").append(_tr);
    }
}
