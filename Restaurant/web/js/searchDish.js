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

