$(function() {
    tree = function(data) {
        layui.use(['tree', 'jquery'], function() {
            var tree = layui.tree;
            var $ = layui.jquery;
            //渲染
            var inst1 = tree.render({
                elem: '#deviceTree',
                data: data,
                showLine: false,
                click: function(obj) {
                    node = obj.data;
                    $(obj.elem).siblings().find(".layui-tree-txt").removeClass("tree-node-checked tree-node-checked:hover");
                    $(obj.elem).find(".layui-tree-txt").addClass("tree-node-checked tree-node-checked:hover");
                    $("#content_index").attr("src", "config.html");
                }
            });
        });
        // 节点左侧小圆点显示在线状态
        $(function() {
            var txt = $(".layui-tree-txt");
            for (var i = 0; i < data.length; i++) {
                if (data[i].isonline === "1") {
                    $(txt[i]).before('<span class="layui-badge-dot" style="margin:-1px -12px 0px;background-color:#3de46c"></span>');
                } else {
                    $(txt[i]).before('<span class="layui-badge-dot" style="margin:-1px -12px 0px;background-color:rgb(255,255,255,0.5)"></span>');
                }
            }
        });
    };
    // 初始化导航树
    var basic = remoteDetail.basic();
    tree(basic);
    // 左侧导航标签切换筛选条件
    $(".tree-type-btn").on("click", function() {
        $(this).siblings().removeClass("tree-type-btn-checked");
        $(this).addClass("tree-type-btn-checked");
        if ($(this).index() === 0) {
            for (var i = 0; i < basic.length; i++) {
                basic[i].title = basic[i].tns;
            }
            $("#deviceTree").css("padding-left","50px");
        } else if ($(this).index() === 1) {
            for (var i = 0; i < basic.length; i++) {
                basic[i].title = basic[i].rid;
            }
            $("#deviceTree").css("padding-left","0px");
        }
        $("#search").val("");
        tree(basic);
    });
    // 搜索框筛选
    $("#search").on("keyup", function() {
        var input = $(this).val().toUpperCase();
        var result = $.extend(true, [], basic);
        regexp = new RegExp(input, "i");
        for (var i = 0; i < result.length; i++) {
            if (!regexp.test(result[i].title)) {
                result.splice(i, 1);
                i--;
            }
        }
        tree(result);
        $(function() {
            $(".layui-tree-txt").each(function(index, item) {
                var str = $(item).html().replace(input, "<span>" + input + "</span>");
                $(item).html(str);
            })
        })
    })
    layer.close(treeLoading);
})