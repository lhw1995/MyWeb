function checkSubmit() {
    var registerFlag = [false, false, false];
    var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]{4,10}$/g;
    if (reg.test($("input[name='showName']").val())) {
        registerFlag[0] = true;
    } else {
        registerFlag[0] = false;
    }
    var reg2 = /^[0-9]{11}$/g;
    if (reg2.test($("input[name='phone']").val())) {
        registerFlag[1] = true;
    } else {
        registerFlag[1] = false;
    }
    if ($("#errorMessage").html() == "") {
        registerFlag[2] = true;
    } else {
        registerFlag[2] = false;
    }

    for (var i = 0; i < registerFlag.length; i++) {
        if (registerFlag[i] == false) {
            return false;
        }
    }
    console.log(registerFlag);
    return true;
}

function upload() {
    $.ajaxFileUpload({
        type: 'post',
        url: '/restaurant/uploadPreview?time=' + new Date().getTime(),
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
                    $("#ImgPr").attr('src', "/images/headPortrait/headPortraitPre/" + data[i]);
                    $("#errorMessage").html("");
                }
            }
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    })
}

