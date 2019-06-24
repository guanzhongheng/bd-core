var USERLIST = {

    initTable : function () {
        $('#userListTable').bootstrapTable({
            url : "/user/transferInfo",
            method : 'post',
            contentType : "application/x-www-form-urlencoded",
            singleSelect : false,
            striped : true, // 是否显示行间隔色
            cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination : false, // 是否显示分页（*）
            sortable : true, // 是否启用排序
            sortOrder : "asc", // 排序方式
            // queryParams: oTableInit.queryParams,//传递参数（*）,
            queryParams:function queryParams(params) {   //设自定义查询参数
                var param = {
                    status: $("#status").val()//菜单标题
                };
                return param;
            },
            sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
            pageNumber : 1, // 初始化加载第一页，默认第一页
            pageSize : 10, // 每页的记录行数（*）
            //pageList : [ 5, 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
            search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch : true,
            showColumns : false, // 是否显示所有的列
            showRefresh : false, // 是否显示刷新按钮
            minimumCountColumns : 2, // 最少允许的列数
            clickToSelect : true, // 是否启用点击选中行
            uniqueId : "id", // 每一行的唯一标识，一般为主键列
            showToggle : false, // 是否显示详细视图和列表视图的切换按钮
            cardView : false, // 是否显示详细视图
            detailView : false, // 是否显示父子表
            columns : [
                {
                    title : "序号",
                    align : 'center',
                    formatter : function(value, row, index) {
                        return index + 1;
                    }
                },
                {
                    title : "R账号",
                    field : 'userName',
                    align : 'center'
                },
                {
                    title : "用户姓名",
                    field : 'realName',
                    align : 'center'
                },
                {
                    title : "操作",
                    field : 'createTime',
                    align : 'center',
                    formatter:function (value,row,index) {
                        debugger;
                        if(row.status=='0'){
                            var edit = '<button style="width: 55px;height: 35px" type="button" class="btn btn-primary btn-xs" onclick="jhClick(' + row.userId + ')">激活</button> ';
                            return edit;
                        }

                    }
                }]
        });
    }
}

function excelUploadFilesForAccount() {
    window.location.href = "/export/bscList?status=1";
}

function excelUploadFilesForAddress() {
    window.location.href = "/export/recieverAdressList";
}

function queryIncomeTrade() {
    $('#userListTable').bootstrapTable("refresh")
}

function jhClick(userId) {
    debugger;
    if(userId != null && userId != '' && userId != undefined){
        $.ajax({
            type: "POST",
            url: '/user/updateStatus',
            data: {
                userId: userId
            },
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.success) {
                    toastr.success("激活成功");
                    $('#userListTable').bootstrapTable("refresh")
                } else {
                    toastr.error("保存失败");
                }
            }
        });
    }
}


$(document).ready(function() {
    USERLIST.initTable();
});