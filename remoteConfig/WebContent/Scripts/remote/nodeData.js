$(function () {
	//window.alert(node.terminalNum);
    //if (node.terminalNum) {
    //    node = remoteDetail.basic(window.parent.node.terminaNum)[0];
   //     window.alert(node.terminalNum);
    //} else {
    //    node = window.parent.node;
    //}
    
    
    //系统时间
    Date.prototype.format = function (fmt) {
        var o = {
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds() //秒
        };
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    document.getElementById('systemtime').value = (new Date()).format('hh:mm:ss');
    setInterval("document.getElementById('systemtime').value = (new Date()).format('hh:mm:ss');", 1000);
    // 在线状态
    if (node.isonline === "1") {
	 
        $("#isonline")[0].innerHTML = "在线";
        $("#isonline").css("color","green");
    } else {
        $("#isonline")[0].innerHTML = "离线";
    }
    
    // 设备基本信息
    basicFill = function (node) {
        $("#IP")[0].innerHTML = "IP: "+node.ip;
    	$("#Port")[0].innerHTML = "Port: "+node.port;
    	$("#terminalNum")[0].innerHTML = "设备编号: "+node.terminalNum;
    	$("#description")[0].innerHTML = "备注: "+node.description;
    	$("#rid")[0].innerHTML = node.rid;
        //$("#svr")[0].innerHTML = "软件版本：" + node.svr +"<i class='layui-icon layui-icon-refresh' style='margin-left:10px'></i>";
        $(".layui-icon-refresh").on("click",function(){
            Control.svr();
        })
        window.alert(node);
        layui.use('form', function () {
            var form = layui.form;
            
            form.val("basicParams", JSON.parse(JSON.stringify(node)));
            console.log(node.value);
            form.render();
        });
        //其它平台参数
        var dms = $("#dms input");
        dms.each(function () {
            $(this).attr("checked", false);
        })
        $(dms[parseInt(node.dms) - 1]).attr("checked", true);
        //实时采集数据
        if(node.clre!=undefined){
            var clre = node.clre.split(":");
            $("#clre1")[0].innerHTML = clre[0];
            $("#clre2")[0].innerHTML = clre[1];
        }else{
            $("#clre1")[0].innerHTML = undefined;
            $("#clre2")[0].innerHTML = undefined;
        };
    }
    basicFill(node);


    //通道参数
    channelFill = function (node) {
        var channelParams = remoteDetail.channel(node.rid)[0];
        layui.use('form', function () {
            var form = layui.form;
            form.val("channelParams", channelParams);
            form.render();
        });
    }
    channelFill(node);


    //报警参数
    avsFill = function (node) {
        var avsParams = remoteDetail.avs(node.rid)[0];
        layui.use('form', function () {
            var form = layui.form;
            form.val("avsParams", avsParams);
            form.render();
        });
        layer.close(nodeLoading);
    };
    avsFill(node);

    //电压
    voltageFill = function(node){
        var voltage = remoteDetail.voltage(node.tns)[0].voltage;
        $("#voltage")[0].innerHTML= "电池电压：" + voltage + "V";
    }
    voltageFill(node);
});