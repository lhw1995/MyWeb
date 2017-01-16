/**
 * Created by lhw on 2016/12/31.
 */
function uploadAssessImg() {
    $.ajaxFileUpload({
        type: 'post',
        url: '/restaurant/assessimg?time=' + new Date().getTime(),
        fileElementId: 'assessPic',
        dataType: 'json',
        async: true,
        success: function (data) {
            for (var i in data) {
                if (i == 'error') {
                    $("#errorMsg").html(data[i]);
                    return;
                }
            }
            for (var i in data) {
                if (i == 'fileName') {
                    $("#picList").empty();
                    for (var j in data[i]){
                        console.log(data[i][j]);
                        var _li= "<img src='/images/assess/assessimgpre/"+data[i][j]+"' height='50px' width='50px'/>";
                        $("#picList").append(_li);
                    }
                    $("#errorMsg").html("");
                }
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}
