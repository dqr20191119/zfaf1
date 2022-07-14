<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${ctx}/static/module/video/css/hzmask.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/module/video/css/rightVideoPlan.css"/>

<div id="broadcastGroup" class="broadcastGroup" style="height:98%;width:98%">

    <div id="divLayout" class="div-layout div-layout-transition">
        <div class="inner-panel"></div>
    </div>

    <cui:accordion id="accordion_broadcastPlan" heightStyle="fill" onActivate="broadcastPlan_onActivate">
        <h3>广播列表</h3>
        <div class="vedio-panel" style="width:100%">
            <div class="tree-box">
                <div style="width:100%;height:100%;" class="videotree-tabs-box">
                    <!-- 查询框 -->
                    <div>
                    	<cui:input width="150" id="filterBroadcastTree" texticons="搜索广播" placeholder="搜索广播" onEnter="filterBroadcastTree"></cui:input>
						<cui:button id="bfBtn" label="播放" onClick="bfHandler"></cui:button>
					</div>
                    <!-- 广播区域树 -->
                    <cui:tree id="regionBroadcastTree" asyncEnable="true" keepParent="true"
                              asyncType="post" simpleDataEnable="true" onLoad="regionBroadcastTreeOnLoad"
                              asyncUrl=""
                              onDblClick="regionBroadcastTreeOnDblClick" onClick="regionBroadcastTreeOnClick" 
                              checkable="true" chkboxType="chkboxType" chkStyle="checkbox" 
                              onRightClick="regionBroadcastTreeOnRightClick" formatter="broadcastFormatter"
                              asyncAutoParam="id,name" rootNode="true"
                              showRootNode="true">
                    </cui:tree>
                </div>
            </div>
        </div>
        <%--<h3>广播预案</h3>
        <div class="auto-body auto-body-transition auto-body-close">
            <div class="tree-box">
                <div style="width:100%;height:100%;" class="videotree-tabs-box">
                    <cui:tree id="groupCommonTree" asyncEnable="true" keepParent="true"
                              asyncType="post" showLine="true" simpleDataEnable="true"
                              asyncUrl=""
                              asyncAutoParam="id,name" onDblClick="group_onDblClick" rootNode="true"
                              formatter="broadcastFormatter"
                              showRootNode="true">
                    </cui:tree>
                </div>
            </div>
        </div>--%>
    </cui:accordion>
	<cui:dialog id="plbfDialog" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
</div>

<script type="text/javascript" src="${ctx}/static/module/video/js/rightBroadcastPlan.js"></script>
<script type="text/javascript">

    var chkboxType = {
        'Y': 'ps',
        'N': 'ps'
    }
    //面板第一次触发时置为true，同时加载树，此后不在重复加载
    var accordion_0 = false;
    var accordion_1 = false;
    var accordion_2 = false;

    //阻止#regionBroadcastTree鼠标右键默认事件
    $(document).on("contextmenu", "#regionBroadcastTree", function (e) {
        e.preventDefault();
    });//阻止鼠标右键默认事件

    $.parseDone(function () {
        //初始化树
        $("#regionBroadcastTree").tree("option", "asyncUrl", "${ctx}/common/areadevice/queryAreaBroadcastTree");
        //加载第一个面板
        $("#regionBroadcastTree").tree("reload");
    });

    //树加载完执行
    function regionBroadcastTreeOnLoad(event, id, data) {
        initbroadcastNodes();
        $.parser.parse();
    };

    //初始化广播集合
    function initbroadcastNodes() {
        //获取广播节点
        var broadcastNodes = $("#regionBroadcastTree").tree("getNodesByParam", "nodeType", "broadcast");
        initBroadcastTree(broadcastNodes);
    };

    //切换面板
    function broadcastPlan_onActivate() {
        reViewMode = $("#accordion_broadcastPlan").accordion("option", "active");

        if (reViewMode == 0) {//广播列表
            if (!accordion_0) {
                $("#regionBroadcastTree").tree("reload");
                accordion_0 = true;
            }
        } else if (reViewMode == 1) {//公共预案
            if (!accordion_1) {
                $("#groupCommonTree").tree("reload");
                accordion_1 = true;
            }

        } else if (reViewMode == 2) {//自定义预案
            if (!accordion_2) {
                $("#groupDIYTree").tree("reload");
                accordion_2 = true;
            }
        }
    };
    // 双击广播图标
    function regionBroadcastTreeOnDblClick(event, id, node){
        // 弹出广播窗口
        if(node.nodeType == "broadcast"){
        	
            /* $("#confirmDialog").dialog("option", {
                title: node.title + "广播操作窗口",
                width: 800,
                height: 600,
                url: "${ctx}/broadcastLinkage/controlBroadcastDialog?broadcastId=" + node.id
            });
            $("#confirmDialog").dialog("open"); */
            var event = window.event || event;
			//event.stopPropagation();
			  if ( event && event.stopPropagation ) {
				  event.stopPropagation();
	    		} else {
	           window.event.cancelBubble = true;
	    	}
			//event.preventDefault();
		 
			var url_="${ctx}/broadcastLinkage/controlBroadcastDialog?broadcastId=" + node.id;
			$("#dialog").dialog({
				width : 800,
				height :700,
				 title: node.title + "广播操作窗口",
				url : url_
			});
			$("#dialog").dialog("open");
	   
        }
    };

    // 过滤广播树
    function filterBroadcastTree(e, data){
        $("#regionBroadcastTree").tree("filterNodesByParam", {name: data.value});
    };
    
    function czHandler() {
    	$("#regionBroadcastTree").tree("checkAllNodes", {checked:false});
    };
    
    function bfHandler() {
    	var data = $("#regionBroadcastTree").tree("getCheckedNodes");
    	var id = [];
    	var node = [];
    	if (data && data.length > 0) {
    		for (var i = 0; i < data.length; i++) {
        		if (data[i].nodeType == "broadcast") {
        			id.push(data[i].id);
        			node.push(data[i]);
        		}
        	}
    	}
    	if (node.length == 1) {
    		regionBroadcastTreeOnDblClick(null, null, {
    			id:node[0].id,
    			title:node[0].name,
    			nodeType:"broadcast"
    		});
    	}
    	if (id.length > 1) {
    		$("#plbfDialog").dialog("option", {
                title: "广播操作窗口",
                width: 850,
                height: 600,
                url: "${ctx}/broadcastLinkage/controlMultiDialog?broadcastId=" + id.join(",")
            });
            $("#plbfDialog").dialog("open");
    	}
    };
    
</script>
