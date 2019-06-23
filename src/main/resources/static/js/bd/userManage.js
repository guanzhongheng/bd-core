function savePassword() {
    var newPassword = $("#newPassword").val();
    var newPassword2 = $("#newPassword2").val();
    if (newPassword == null || newPassword2 == null) {
        toastr.warn("两次密码不一样！");
        return;
    }

    $.ajax({
        type: "POST",
        url: '/check',
        data: {
            newPassword: newPassword
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            if ("success" == data.result) {
                toastr.success("保存成功");
            } else {
                toastr.error("保存失败");
            }
        }
    });
}

function saveUser() {
    var realName = $("#realName").val();
    var phone = $("#phone").val();
    var imgAddress = $("#imgAddress").val();

    $.ajax({
        type: "POST",
        url: '/check',
        data: {
            realName: realName,
            phone: phone,
            imgAddress: imgAddress
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            if ("success" == data.result) {
                toastr.success("保存成功");
            } else {
                toastr.error("保存失败");
            }
        }
    });
};

function saveAddress() {
    var realName = $("#realName").val();
    var phone = $("#phone").val();
    var imgAddress = $("#imgAddress").val();

    $.ajax({
        type: "POST",
        url: '/check',
        data: {
            realName: realName,
            phone: phone,
            imgAddress: imgAddress
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            if ("success" == data.result) {
                toastr.success("保存成功");
            } else {
                toastr.error("保存失败");
            }
        }
    });
};


var USERMANAGE = {

    initImg: function () {
        var url = $("#imgAddress").val();
        $("#uploadImage").fileinput({
            url: "/security/company/uploadFile",
            dataType: 'json',
            autoUpload: false,
            acceptFileTypes: /(gif|jpe?g|png)$/i,
            maxFileSize: 1000000, // 1 MB
            imageMaxWidth: 100,
            imageMaxHeight: 100,
            messages: {
                acceptFileTypes: '文件类型不匹配',
                maxFileSize: '文件过大',
                minFileSize: '文件过小'
            }
        }).on("fileuploadadd", function (e, data) {
            // 当文件添加上去时候调用
            debugger;
            $("#imgAddress").val(data);
        }).on("fileuploaddone", function (e, data) {
            // 上传完成时调用
            debugger;
            if (data.result.returnState == "ERROR") {
                alert("上传失败")
                return;
            }
        });
    }
}



$(document).ready(function () {
    USERMANAGE.initImg();
})