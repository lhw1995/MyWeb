/**
 * Created by lhw on 2016/12/23.
 */
$(document).ready(function () {
    var dishId = $("#dishId").val();
    $.ajax({
        type: 'get',
        url: '/restaurant/multiPic/'+dishId,
        data: {
        },
        async: true,
        success: function (data) {
            console.log(data);
            $("#imgList ul").empty();
            for (var i in data) {
                console.log(data[i]);
                var _li= "<li>" +
                    "<img src='"+data[i].image+"' height='200px' width='200px'/>"+
                    "</li>";
                $("#imgList ul").append(_li);
                $("#errorMessage").html("");
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
})