var TRANSFERLIST = {

    initTable : function () {
        $('#userListTable').bootstrapTable({
            url : "/user/doFindUndealRewads",
            method : 'post',
            contentType : "application/x-www-form-urlencoded",
            singleSelect : false,
            striped : true, // 是否显示行间隔色
            cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination : false, // 是否显示分页（*）
            sortable : true, // 是否启用排序
            sortOrder : "asc", // 排序方式
            // queryParams: oTableInit.queryParams,//传递参数（*）,
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
                    title : "用户账号",
                    field : 'userName',
                    align : 'center'
                },
                {
                    title : "用户余额",
                    field : 'amount',
                    align : 'center'
                },
                {
                    title : "用户二维码",
                    field : 'attachUrl',
                    align : 'center',
                    formatter:function (value,row,index) {
                        return processPageDiv(row);
                    }
                },
                {
                    title : "操作",
                    field : 'operator',
                    align : 'center',
                    formatter:function (value,row,index) {
                        var edit = '<button style="width: 55px;height: 35px" type="button" class="btn btn-primary btn-xs" onclick="updateUser(' + row.id + ',' + row.amount + ',' + row.userId + ')">确认</button> ';
                        return edit;
                    }
                }]
        });
    }
}

function updateUser(id,amount,userId) {
    $.ajax({
        type: "POST",
        url: '/user/updateUserReward',
        data: {
            id: id,
            userId: userId,
            amount: amount
        },
        dataType: 'json',
        cache: false,
        success: function (data) {
            debugger;
            if (data.success) {
                $('#userListTable').bootstrapTable("refresh");
            } else {
                toastr.error("保存失败");
            }
        }
    });
}


function processPageDiv(row){
    var html =  '<div class="panel panel-info">' +
        '<div class="panel-body" style="text-align: center;">' +
        '<div class="row">' +
        '<div class="col-sm-12 col-md-12" id="image">' +
        '<img class="updateimg img-responsive" src="'+row+'" style="width: inherit;height: 210px;"/>' +
        '</div></div></div></div>';
    return html;
}

$(document).ready(function() {
    TRANSFERLIST.initTable();
});