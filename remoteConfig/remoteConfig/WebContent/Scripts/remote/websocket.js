var Control = {};
var curWwwPath = window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
var localhostPaht = curWwwPath.substring(0, pos);
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var root = localhostPaht + projectName;
var timer = null;

//var ws = new WebSocket("ws://121.40.165.18:8800");
// var ws = new WebSocket("ws://49.234.18.41:8866");
var ws = new WebSocket("ws://47.98.214.12:5257");
//var ws = new WebSocket("ws://47.98.214.12:7271");
// console.log(ws.readyState);
ws.onopen = function() {
    // Web Socket 已连接上，使用 send() 方法发送数据
    console.log("websocket on");
};
ws.onerror = function() {
    layer.msg("连接失败");
};

// 将后台返回的消息提示框显示
ws.onmessage = function(evt) {
    layer.close(controlLoading);
    var received_msg = evt.data;
    if (received_msg === "基础参数数据更新成功") {
        var basic = remoteDetail.basic(node.rid)[0];
        basicFill(basic);
        layer.msg("成功", {
            time: 1000
        });
    } else if (received_msg === "通道参数数据更新成功") {
        channelFill(node);
        layer.msg("成功", {
            time: 1000
        });
    } else if (received_msg === "报警参数数据更新成功") {
        avsFill(node);
        layer.msg("成功", {
            time: 1000
        });
    } else if (received_msg === "sessionID不存在!") {
        layer.msg("设备已离线", {
            time: 1000
        });
    } else {
        layer.msg(received_msg);
    }
    console.log(received_msg);
};

var flag = node.tns;

Control.svr = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":SVR" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.tnsRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":TNS" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.tnsWrite = function(tns) {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":TNS:" + tns + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.systemtimeWrite = function(systemtime) {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":TIME:" + systemtime + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.dateRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":DATE" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.dateWrite = function(date) {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":DATE:" + date + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.timeRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":TIME" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.timeWrite = function(time) {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":TIME:" + time + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.wmsRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":WMS" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.wmsWrite = function(wms) {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":WMS:" + wms + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.cstRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":CST" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.cstWrite = function(cst) {
    controlLoading = layer.load(2);
    cst = cst.split(":");
    var control = "NO:" + flag + ":CST:H:" + cst[0] + ":M:" + cst[1] + ":S:" + cst[2] + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.channelRead = function(checked) {
    controlLoading = layer.load(2);
    var control = new Array();
    checked.each(function(index, item) {
        control.push(item.name.substring(0, 3).toUpperCase() + ":0" + item.name.substring(3, 4));
    });
    var length = control.length;
    control = "NO:" + flag + ":" + control.join("~") + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
        return;
    } else if (length === 0) {
        layer.msg("请至少选择一个通道", {
            time: 1500
        });
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};

Control.channelWrite = function(params) {
    controlLoading = layer.load(2);
    var control = new Array();
    if (params.csp1 === "on") {
        control.push("CSP:01" + ":S:" + params.csp1s + ":A:" + params.csp1a + ":T:0:P:02" + params.csp1p + ":K:" + params.csp1k + ":B:" + params.csp1b)
    }
    if (params.csp2 === "on") {
        control.push("CSP:02" + ":S:" + params.csp2s + ":A:" + params.csp2a + ":T:0:P:02" + params.csp2p + ":K:" + params.csp2k + ":B:" + params.csp2b)
    }
    if (params.csp3 === "on") {
        control.push("CSP:03" + ":S:" + params.csp3s + ":A:" + params.csp3a + ":T:0:P:02" + params.csp3p + ":K:" + params.csp3k + ":B:" + params.csp3b)
    }
    if (params.csp4 === "on") {
        control.push("CSP:04" + ":S:" + params.csp4s + ":A:" + params.csp4a + ":T:0:P:02" + params.csp4p + ":K:" + params.csp4k + ":B:" + params.csp4b)
    }
    if (params.csp5 === "on") {
        control.push("CSP:05" + ":S:" + params.csp5s + ":A:" + params.csp5a + ":T:0:P:02" + params.csp5p + ":K:" + params.csp5k + ":B:" + params.csp5b)
    }
    if (params.csp6 === "on") {
        control.push("CSP:06" + ":S:" + params.csp6s + ":A:" + params.csp6a + ":T:0:P:02" + params.csp6p + ":K:" + params.csp6k + ":B:" + params.csp6b)
    }
    if (params.csm1 === "on") {
        control.push("CSM:01" + ":S:" + params.csm1s + ":A:" + params.csm1a + ":P:" + params.csm1p0 + "|" + params.csm1p1 + "|" + params.csm1p2 + "|" + params.csm1p3 + "|" + params.csm1p4 + ":B:" + params.csm1b + ":M:" + params.csm1m + ":R:" + params.csm1r + ":N:" + params.csm1n)
    }
    if (params.csm2 === "on") {
        control.push("CSM:02" + ":S:" + params.csm2s + ":A:" + params.csm2a + ":P:" + params.csm2p0 + "|" + params.csm2p1 + "|" + params.csm2p2 + "|" + params.csm2p3 + "|" + params.csm2p4 + ":B:" + params.csm2b + ":M:" + params.csm2m + ":R:" + params.csm2r + ":N:" + params.csm2n)
    }
    if (params.csm3 === "on") {
        control.push("CSM:03" + ":S:" + params.csm3s + ":A:" + params.csm3a + ":P:" + params.csm3p0 + "|" + params.csm3p1 + "|" + params.csm3p2 + "|" + params.csm3p3 + "|" + params.csm3p4 + ":B:" + params.csm3b + ":M:" + params.csm3m + ":R:" + params.csm3r + ":N:" + params.csm3n)
    }
    if (params.csm4 === "on") {
        control.push("CSM:04" + ":S:" + params.csm4s + ":A:" + params.csm4a + ":P:" + params.csm4p0 + "|" + params.csm4p1 + "|" + params.csm4p2 + "|" + params.csm4p3 + "|" + params.csm4p4 + ":B:" + params.csm4b + ":M:" + params.csm4m + ":R:" + params.csm4r + ":N:" + params.csm4n)
    }
    var length = control.length;
    control = "NO:" + flag + ":" + control.join("~") + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
        return;
    } else if (length === 0) {
        layer.msg("请至少选择一个通道", {
            time: 1500
        });
        layer.close(controlLoading);
    } else {
        ws.send(control);
    }
};

Control.avsRead = function(checked) {
    controlLoading = layer.load(2);
    var control = new Array();
    checked.each(function(index, item) {
        control.push(item.name.substring(0, 3).toUpperCase() + ":0" + item.name.substring(3, 4));
    });
    var length = control.length;
    control = "NO:" + flag + ":" + control.join("~") + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
        return;
    } else if (length === 0) {
        layer.msg("请至少选择一个通道", {
            time: 1500
        });
    } else {
        ws.send(control);
    };
};
Control.avsWrite = function(params) {
    controlLoading = layer.load(2);
    var control = new Array();
    if (params.avs1 === "on") {
        control.push("AVS:01:" + params.avs1h + ":" + params.avs1l + ":" + params.avs1a + ":" + params.avs1s)
    }
    if (params.avs2 === "on") {
        control.push("AVS:02:" + params.avs2h + ":" + params.avs2l + ":" + params.avs2a + ":" + params.avs2s)
    }
    if (params.avs3 === "on") {
        control.push("AVS:03:" + params.avs3h + ":" + params.avs3l + ":" + params.avs3a + ":" + params.avs3s)
    }
    if (params.avs4 === "on") {
        control.push("AVS:04:" + params.avs4h + ":" + params.avs4l + ":" + params.avs4a + ":" + params.avs4s)
    }
    if (params.avs5 === "on") {
        control.push("AVS:05:" + params.avs5h + ":" + params.avs5l + ":" + params.avs5a + ":" + params.avs5s)
    }
    if (params.avs6 === "on") {
        control.push("AVS:06:" + params.avs6h + ":" + params.avs6l + ":" + params.avs6a + ":" + params.avs6s)
    }
    var length = control.length;
    control = "NO:" + flag + ":" + control.join("~") + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
        return;
    } else if (length === 0) {
        layer.msg("请至少选择一个通道", {
            time: 1500
        });
    } else {
        ws.send(control);
    }
};
Control.dmsRead = function() {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":DMS" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.dmsWrite = function(dms) {
    controlLoading = layer.load(2);
    if (dms.dizhi === "on") {
        dms = "01";
    } else if (dms.shuili === "on") {
        dms = "02";
    } else {
        dms = "00";
    }
    var control = "NO:" + flag + ":DMS:" + dms + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.clreRead = function () {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":CLRE" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.clreClear = function () {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":CLRE:00" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};
Control.clrsClear = function () {
    controlLoading = layer.load(2);
    var control = "NO:" + flag + ":CLRS:00" + ":END" + "\r\n";
    console.log(control);
    if (ws.readyState != 1) {
        layer.msg("连接失败");
        layer.close(controlLoading);
    } else {
        ws.send(control);
    };
};

function writeCurrentDate() {
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth(); //得到月份
    var date = now.getDate(); //得到日期
    var day = now.getDay(); //得到周几
    var hour = now.getHours(); //得到小时
    var minu = now.getMinutes(); //得到分钟
    var sec = now.getSeconds(); //得到秒

    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;


    var time = "";
    time = year + "-" + month + "-" + date + " " + hour + ":" + minu + ":" + sec;
    return time;
};