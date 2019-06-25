function savePassword() {
    var newPassword = $("#newPassword").val();
    var newPassword2 = $("#newPassword2").val();
    if (newPassword == null || newPassword2 == null) {
        toastr.warn("两次密码不一样！");
        return;
    }

    $.ajax({
        type: "POST",
        url: '/user/updatePassword',
        data: {
            password: newPassword
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            if (data.success) {
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
        url: '/user/update',
        data: {
            realName: realName,
            phone: phone,
            attachUrl: imgAddress
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            debugger;
            if (data.success) {
                debugger;
                toastr.success("保存成功");
                document.getElementById("rfinxInfo").reset();
            } else {
                toastr.error("保存失败");
            }
        }
    });
};

function saveAddress() {
    var receiverName = $("#receiverName").val();
    var receiverPhone = $("#receiverPhone").val();
    var recieverAddress = $("#receiverAddress").val();

    $.ajax({
        type: "POST",
        url: '/user/updateRecieverInfo',
        data: {
            recieverName: receiverName,
            recieverPhone: receiverPhone,
            recieverAddress: recieverAddress
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            debugger;
            if (data.success()) {
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
            url: "/pic/upload",
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
    },

    initNewImg : function () {

        $("#uploadFile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: "/pic/upload", //上传的地址
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            allowedFileTypes:/(gif|jpe?g|png)$/i,
            showPreview : false, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true
        }).on('filepreupload', function(event, data, previewId, index) {
            debugger;
            $("#picUrl").val();
        });
    }
}



$(document).ready(function () {
   //  USERMANAGE.initImg();
    USERMANAGE.initNewImg();
})