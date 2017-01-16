/**
 * Created by lhw on 2016/12/24.
 */
$(document).ready(function () {
    $("#imgList li").hover(function () {
        var pic = $(this).find("img").attr("src");
        $("#mainImg img").attr("src",pic);
    })
})

function settle() {
    window.location="/restaurant/cartpage"
}

//限制只能输入数字
function checkKey(e) {
    var code = parseInt(e.keyCode);
    if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
        return true;
    } else {
        return false;
    }
}