var User = {};

User.InitGrid = function () {
    grid = $('#list_data').datagrid({
        title: '',
        iconCls: 'icon-view', //图标
        loadMsg: "数据加载中，请稍后......",
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false, //是否可折叠
        fitColumns: true,
        scrollbarSize: 0,
        fit: true, //自动大小  
        url: '../User/LoadList',
        //queryParams: queryData,
        pageNumber: 1, //默认显示第几页 
        pageSize: 30,
        pageList: [30, 50, 100],
        remoteSort: false,
        singleSelect: true,  //是否单选
        pagination: true,  //分页控件
        rownumbers: true,  //行号
        columns: [[
          { field: 'userName', title: '用户名', width: 50 },
          { field: 'password', title: '密码', width: 50 },
          { field: 'stationNum', title: '管理站点编号', width: 50 },
          { field: 'role', title: '用户级别', width: 60 },         
          { field: 'province', title: '省', width: 60 },         
          { field: 'city', title: '市', width: 60 },         
          { field: 'country', title: '城镇', width: 60 },         
          { field: 'location', title: '位置', width: 60 },         
          { field: 'longitude', title: '经度', width: 60 },         
          { field: 'latitude', title: '纬度', width: 60 },         
          { field: 'description', title: '备注', width: 60 },         
          { field: 'tree_text', title:'资源导航标签', width: 60},
          { field: 'id', title: '操作', width: 80, align: 'center', formatter: formatOper }
          
        ]]
    });

    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',
        afterPageText: '页     共{pages}页',
        displayMsg: '当前显示{from}-{to}条记录  共{total}条记录',
        buttons: [{
            iconCls: 'icon-export',
            text: '导出Excel',
            handler: function () { exportexcel(); }
        }]
    });
};

function exportexcel() {
    var url = "../Reperson/Export";
    $.ajax({
        type: "POST",
        url: url,  
        success: function (filename) {
            if (filename != "") {
                var downUrl = '/FileUpload/DownloadFile?file=' + filename;
                window.location = downUrl;
            } else {
                alert("暂无数据可供导出!");
            }
        }
    });
}
// 格式化 操作单元格,设置编辑和删除按钮
function formatOper(value, row, index) {
	//console.log("row="+row["userName"]);
    var e = '<a  title="编辑" href="javascript:;" onclick="member_edit(\'' + row["userName"] + '\')" class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6df;</i></a> ';
    var d = '<a  title="删除" href="javascript:;" onclick="member_del(\'' + row["userName"] + '\')" class="ml-5" style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a> ';
    if(row["stationNum"]){
    	if(row["stationNum"].search("JD") != -1) {e='<p style="color:#C0C0C0;">不可更改';d='</p>';}
    	if(row["stationNum"] == "0") {e='<p style="color:#C0C0C0;">不可更改';d='</p>';}
    	if(row["stationNum"] == "0028-GX") {e='<p style="color:#C0C0C0;">不可更改';d='</p>';}
    }
    return e + d;
}

// 添加信息
function member_add() {
    layer_show("添加用户", "../User/AddIndex.html", '', '630');
}

// 编辑信息
function member_edit(userName) {
    layer_show("编辑用户", "../User/EditIndex.html?userName=" + userName, '600', '500');
}

function member_del(username) {
    layer.confirm('确定要删除该用户吗？删除之后有关该用户的所有信息又会被清除，包括传感器信息、终端信息、预警信息等。', {
        btn: ['确定', '取消']
    }, function (r) {
        if (r) {
            $.ajax({
                type: "POST",
                async: false,
                cache: false,
                url: '../User/UserDel',
                data: { username: username },
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        layer.alert('删除成功');
                        $('#list_data').datagrid('reload');
                    } else {
                        layer.alert('删除失败');
                    }
                }
            });
        }
    });
   
}


