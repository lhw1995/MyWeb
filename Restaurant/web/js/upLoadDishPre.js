function checkSubmit() {
    var registerFlag = [false, false, false,false,false];
    var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
    if (reg.test($("input[name='dishName']").val())) {
        registerFlag[0] = true;
    } else {
        registerFlag[0] = false;
    }
    var reg2 = /^[0-9.]{1,6}$/g;
    if (reg2.test($("input[name='price']").val())) {
        registerFlag[1] = true;
    } else {
        registerFlag[1] = false;
    }
    var reg3 = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
    if (reg3.test($("input[name='classify']").val())) {
        registerFlag[2] = true;
    } else {
        registerFlag[2] = false;
    }
    var reg4 = /^[0-9]{1,5}$/g;
    if (reg4.test($("input[name='inventory']").val())) {
        registerFlag[3] = true;
    } else {
        registerFlag[3] = false;
    }
    if ($("#errorMessage").html() == "") {
        registerFlag[4] = true;
    } else {
        registerFlag[4] = false;
    }

    for (var i = 0; i < registerFlag.length; i++) {
        if (registerFlag[i] == false) {
            return false;
        }
    }
    console.log(registerFlag);
    return true;
}

function checkSubmit2() {
    var registerFlag = [false, false,false, false,true];
    var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
    if (reg.test($("input[name='dishName']").val())) {
        registerFlag[0] = true;
    } else {
        registerFlag[0] = false;
    }
    var reg2 = /^[0-9.]{1,6}$/g;
    if (reg2.test($("input[name='price']").val())) {
        registerFlag[1] = true;
    } else {
        registerFlag[1] = false;
    }
    var reg3 = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}$/g;
    if (reg3.test($("input[name='classify']").val())) {
        registerFlag[2] = true;
    } else {
        registerFlag[2] = false;
    }
    var reg4 = /^[0-9]{1,5}$/g;
    if (reg4.test($("input[name='inventory']").val())) {
        registerFlag[3] = true;
    } else {
        registerFlag[3] = false;
    }
    if ($("#errorMessage").html() == "") {
        registerFlag[4] = true;
    } else {
        registerFlag[4] = false;
    }

    for (var i = 0; i < registerFlag.length; i++) {
        if (registerFlag[i] == false) {
            return false;
        }
    }
    console.log(registerFlag);
    return true;
}
//上传封面预览
function upload() {
    $.ajaxFileUpload({
        type: 'post',
        url: '/restaurant/uploadDishPre?time=' + new Date().getTime(),
        fileElementId: 'up',
        dataType: 'json',
        async: true,
        success: function (data) {
            for (var i in data) {
                if (i == 'error') {
                    $("#errorMessage").html(data[i]);
                    return;
                }
            }
            for (var i in data) {
                if (i == 'fileName') {
                    $("#coverImagPr").attr('src', "/images/food/pre/" + data[i]);
                    $("#errorMessage").html("");
                }
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}

//上传预览图预览
function uploadMulti() {
    $.ajaxFileUpload({
        type: 'post',
        url: '/restaurant/multiPre?time=' + new Date().getTime(),
        fileElementId: 'uploadMul',
        dataType: 'json',
        async: true,
        success: function (data) {
            console.log(data);
            for (var i in data) {
                if (i == 'error') {
                    $("#errorMessage2").html(data[i]);
                    return;
                }
            }
            for (var i in data) {
                if (i == 'fileName') {
                    $("#imgList ul").empty();
                    for (var j in data[i]){
                        console.log(data[i][j]);
                        var _li= "<li>" +
                                    "<img src='/images/food/multipic/multipicPre/"+data[i][j]+"' height='200px' width='200px'/>"+
                                 "</li>";
                        $("#imgList ul").append(_li);
                    }
                    $("#errorMessage").html("");
                }
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}
