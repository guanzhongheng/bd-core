function check() {
    debugger
    if (notNull()) {
        var USERNAME = $("#USERNAME").val();
        var PASSWORD = $("#PASSWORD").val();
        var CODE =USERNAME + "," + PASSWORD;
        $.ajax({
            type : "POST",
            url : '/check',
            data : {
                userName:USERNAME,
                password:PASSWORD
            },
            dataType : 'json',
            cache : false,
            success : function(data) {
                if ("success" == data.result) {
                    $("#USERNAME").tips({
                        side : 2,
                        msg : '正在登录 , 请稍后 ...',
                        bg : '#68B500',
                        time : 10
                    });
                    window.location.href = "/index";
                } else if ("usererror" == data.result) {
                    $("#USERNAME").tips({
                        side : 2,
                        msg : "用户名或密码有误",
                        bg : '#FF5080',
                        time : 15
                    });
                    $("#USERNAME").focus();
                } else {
                    $("#USERNAME").tips({
                        side : 2,
                        msg : "缺少参数",
                        bg : '#FF5080',
                        time : 15
                    });
                    $("#USERNAME").focus();
                }
            }
        });
    }
}


//客户端校验
function notNull() {
    if ($("#USERNAME").val() == "") {
        $("#USERNAME").tips({
            side : 2,
            msg : '用户名不得为空',
            bg : '#AE81FF',
            time : 3
        });
        $("#USERNAME").focus();
        return false;
    } else {
        $("#USERNAME").val(jQuery.trim($('#USERNAME').val()));
    }
    if ($("#PASSWORD").val() == "") {
        $("#PASSWORD").tips({
            side : 2,
            msg : '密码不得为空',
            bg : '#AE81FF',
            time : 3
        });
        $("#PASSWORD").focus();
        return false;
    }
    return true;
}