var broadcastCtrl = null;
var isInitFormStyle = false;
var isClickOpenVideo = '0';		// 是否单击打开视频，否则双击：0否、1是

/*
 * 广播的图标映射
 */
var broadcastIconMap = {
    '0': 'broadcast_0',
    '1': 'broadcast_1',
    '2': 'broadcast_2'
};

/*
 * 广播状态描述
 */
var broadcastSttsMap = {
    '0': '空闲',
    '1': '离线',
    '2': '使用中'
};

/*
 * 广播状态样式
 */
var broadcastSttsCssMap = {
    '0': '',
    '1': {color: 'rgb(235,0,0)'},
    '2': {color: 'rgb(180,180,180)'}
};

/*
 * 广播状态对应颜色
 */
var broadcastColorMap = {
    '0': '',
    '1': 'rgb(235,0,0)',
    '2': 'rgb(180,180,180)'
};

//区域摄像头树查询
function regionBroadcastSearch(e, data) {
    //回车时触发
    if (e.keyCode == 13) {
        $("#regionBroadcastTree").tree("filterNodesByParam", {
            name: data.value
        });
    }
}

//公共群组摄像头树查询
function groupCommonSearch(e, data) {
    //回车时触发
    if (e.keyCode == 13) {
        $("#groupCommonTree").tree("filterNodesByParam", {
            name: data.value
        });
    }
}

//自定义群组摄像头树查询
function groupDIYSearch(e, data) {
    //回车时触发
    if (e.keyCode == 13) {
        $("#groupDIYTree").tree("filterNodesByParam", {
            name: data.value
        });
    }
}

//区域摄像头树展开折叠
function regionBroadcastTreeExpendAll(event, ui) {
    if ("展开" == ui.label) {
        $('#regionBroadcastTree').tree("expandAll", true);
        $("#" + ui.id).button('option', 'label', '折叠');
    } else {
        $("#" + ui.id).button('option', 'label', '展开');
        $('#regionBroadcastTree').tree("expandAll", false);
    }
}

//公共群组摄像头树展开折叠
function groupCommonTreeExpendAll(event, ui) {
    if ("展开" == ui.label) {
        $('#groupCommonTree').tree("expandAll", true);
        $("#" + ui.id).button('option', 'label', '折叠');
    } else {
        $("#" + ui.id).button('option', 'label', '展开');
        $('#groupCommonTree').tree("expandAll", false);
    }
}

//自定义群组摄像头树展开折叠
function groupDIYTreeExpendAll(event, ui) {
    if ("展开" == ui.label) {
        $('#groupDIYTree').tree("expandAll", true);
        $("#" + ui.id).button('option', 'label', '折叠');
    } else {
        $("#" + ui.id).button('option', 'label', '展开');
        $('#groupDIYTree').tree("expandAll", false);
    }
}

/**
 * 打开/隐藏布局窗口
 */
function toggleLayout(obj) {
    $(obj).toggleClass('layout-set-btn-hover');
    $(obj).toggleClass('layout-set-btn-click');
    $('#divLayout').toggleClass('div-layout-open');
    setClose($('#divLayout').hasClass('div-layout-open'));
}

/**
 * 关闭所有布局、设置面板
 */
var isClose = false;// 标识
function setClose(bl) {
    setTimeout(function () {
        isClose = bl;
    }, 80);
}

/*-----------------------------假的videoClient by zk start-----------------------------*/

/** ******************************************************************* **
 *  ********************* 广播列表树翻页控制对象 *********************
 ** ******************************************************************* **/
function BroadcastCtrl() {
    this.list = [];
    this.length = 0;
    this.beginIndex = 0;
    this.endIndex = 0;
    this.selectedIndex = -1;
    this.curBroadcasts = [];
    this.autoTimerId = null;// 自动翻页的定时器ID
    this.isAuto = false;
}

/**
 * 初始化广播列表控制
 */
BroadcastCtrl.prototype.init = function (broadcasts) {
    console.log("-------------init------------");
    this.list = broadcasts;
    this.length = broadcasts.length;
    this.beginIndex = 0;
    this.endIndex = 0;
    this.selectedIndex = -1;
    this.curBroadcasts = [];
};

/*
 * 初始化设置
 */
function initSet() {
    $('ul.sys-set >li').click(function () {
        $(this).find('span.chkbox').toggleClass('checked');
        switch ($(this).attr('eventType')) {
            case '1':
                setFormStyle();
                break;
            case '2':
                useDefAutoTime();
                break;
            case '3':
                broadcastClickAlert();
                break;
        }
        setConfigs();
    });
}

/*
 * 广播单/双击提示
 */
function broadcastClickAlert() {
    if (isChecked('broadcastOnClick', 1, 0)) {
        _alert('操作提示：<br>单击【左键】打开视频! <br>单击【右键】选择视频!');
    } else {
        _alert('操作提示：<br>【双击】左键打开视频! <br>【单击】左键选择视频!');
    }
}

/*
 * 是否存在样式
 */
function isChecked(id, arg1, arg2) {
    if ($('#' + id + ' >span.chkbox').hasClass('checked')) {
        return arg1 == undefined ? true : arg1;
    } else {
        return arg2 == undefined ? false : arg2;
    }
}

/*
 * 设置复选框
 */
function setChecked(id, checked) {
    if (checked == "1") {
        $('#' + id + ' >span.chkbox').addClass('checked');
    } else {
        $('#' + id + ' >span.chkbox').removeClass('checked');
    }
}


/*
 * 首页
 */
BroadcastCtrl.prototype.first = function () {
    var num = this.getNum();
    this.endIndex = num;
    this.beginIndex = 0;
    this.excute();
    stopAuto();
    hideAuto();
}

/*-------------------------update by zk start-------------------------*/

//摄像头格式化
function broadcastFormatter(e, node) {
    console.log(node);
    if (node.nodeType == "broadcast") {
        var status = node.status;

        node.iconSkin = (broadcastIconMap[status] || '');
        node.title = node.name;
        node.name = node.name + '[' + (broadcastSttsMap[status])+ ']';
    }
    return node.name;
}

/*-------------------------update by zk start-------------------------*/
/*
 * 查询匹配
 */
BroadcastCtrl.prototype.findOf = function (handle) {
    for (var i = 0, I = this.list.length; i < I; i++) {
        if (handle(i, this.list[i])) {
            return true;
        }
    }
}

/*
 * 收缩面板
 */
function expandCollapse() {
    $('.vedio-panel .panel-toolbar').toggle();
    $('.sperator').toggle();
    $('#divWaitTime').toggle();
}

function _confirm(msg, callback_p) {
    $.confirm({
        message: msg,
        iframePanel: true,
        callback: callback_p,
        position: {
            my: "right top",   //此为右上角
            at: "right top",
            of: window
        }
    });
    //$.messager.confirm('确认', msg, callback);
    $('div.panel.window, div.window-shadow').css({
        'left': 8,
        'width': 280
    });
    $('div.window-header').css('width', '280px');
    $('div.window-body').css('width', '258px');
}

//add by zk
function _alert(msg) {
    $.messageBox({
        message: msg,
        position: {
            my: "right top",   //与上述类似，此为右上角
            at: "right top",
            of: window
        }
    });
}

function _warn(msg, type, width, obj) {
    var type = type || "info",
        width = width || "auto",
        obj = obj || window;
    $.messageToast({
        message: msg,
        type: type,
        width: width,
        position: {
            my: "center top+10",   //与上述类似，此为右上角
            at: "center top",
            of: obj
        }
    });
}

function _showWaiting(msg) {
    hzMask.show(msg);
}

function _hideWaiting() {
    hzMask.hide();
}

/*
 * 初始化广播树
 */
function initBroadcastTree(broadcastList) {
    console.log("initBroadcastTree");
    // 初始化广播控制对象
    broadcastCtrl = new BroadcastCtrl();
    broadcastCtrl.init(broadcastList);
}

/*----------------update by zk end--------------------*/