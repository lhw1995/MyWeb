/**
 * Created by lhw on 2016/12/31.
 */
$(document).ready(function () {
    var dishId = $("#dishId").val();
    $.ajax({
        type: 'get',
        url: '/restaurant/assess/'+dishId,
        data: {
        },
        async: true,
        success: function (data) {
            $("#assessCount td").html("累计评价数:"+data.assessCount);
            $("#avgScore td").html("<b>评分:</b>"+data.score);
            for (var i = 0; i < data.multiAssessDtoList.length; i ++) {
                var _img = "";
                for (var j in data.multiAssessDtoList[i].assessImageList){
                    _img = _img + "<img src='"+data.multiAssessDtoList[i].assessImageList[j].image+"' height='50' width='50'/>"
                }

                var score = parseInt(data.multiAssessDtoList[i].assess.item.score);
                var star2Flag = false;
                if (score%2 != 0){
                    star2Flag = true;
                }
                var _star = "";
                for (var k = 0; k < parseInt(score/2);k ++) {
                    _star = _star+"<li><img src='/images/star.png' width='30' height='30'/></li>";
                }
                if (star2Flag) {
                    _star = _star+"<li><img src='/images/star2.png' width='30' height='30'/></li>";
                }
                var _tr = "<tr>" +
                    "<td id='assessHead'><img src='"+data.multiAssessDtoList[i].consumer.headPortrait+"' width='50' height='50'></td>" +
                    "<td id='singleScore'><span style='float: left'><b>评分:</b>"/*+data.multiAssessDtoList[i].assess.item.score*/+
                    "</span><ul>" +
                    _star+
                    "</ul>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td id='assessUser'>"+data.multiAssessDtoList[i].consumer.showName+"</td>" +
                    "<td id='assessContent'>"+
                    data.multiAssessDtoList[i].assess.content+"<br>" +
                        _img+
                    "</td>" +
                    "<td id='assessDate'>"+data.multiAssessDtoList[i].assess.time+"</td>" +
                    "</tr><tr><td colspan='3'><hr/></td></tr>";
                $("#assessTable").append(_tr);
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
})