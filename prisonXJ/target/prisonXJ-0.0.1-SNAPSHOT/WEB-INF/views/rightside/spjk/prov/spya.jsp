<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/hzmask.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoPlan.css" />


<div id="cameraGroup" class="cameraGroup" style="height:98%;width:99%">

	<input id="btnLayout" type="button"
		class="layout-set-btn layout-set-btn-hover" title="布局"
		onclick="toggleLayout(this)">
	<div id="divLayout" class="div-layout div-layout-transition">
		<div class="inner-panel"></div>
	</div>
	
	<cui:accordion id="accordion_spya" heightStyle="fill" onActivate="spya_onActivate">
		<h3>视频列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red">(请选择画面布局)</span></h3>
		<div class="vedio-panel" style="width:100%">		
			<div class="panel-toolbar">
				<cui:input texticons="" placeholder="搜索"
					onKeyPress="regionCameraSearch"></cui:input>
				<%--<cui:button id="regionCameraTreeExpendAll" label="展开"
					onClick="regionCameraTreeExpendAll"></cui:button>--%>
			</div>
			<div class="panel-toolbar second">
				<cui:button label="首页" onClick="cameraCtrl.first()"></cui:button>
				<cui:button label="上一页" onClick="lastPage()"></cui:button>
				<cui:button label="自动" id="btnAuto"></cui:button>
				<cui:button label="下一页" onClick="nextPage()"></cui:button>
				<cui:button label="尾页" onClick="cameraCtrl.end()"></cui:button>
			</div>
			<div class="sperator">
				 <span class="left-border"></span>
				 <span class="sperator-icon icon cui-icon-arrow-down3"></span>
				 <span class="right-border"></span>
			</div>
			<div  id="divWaitTime"  class="panel-toolbar-custom">
				<cui:button label="10秒" onClick="startAuto(10)"></cui:button>
				<cui:button label="15秒" onClick="startAuto(15)"></cui:button>
				<cui:button label="20秒" onClick="startAuto(20)"></cui:button>
				<cui:button label="25秒" onClick="startAuto(25)"></cui:button>
				<cui:button label="30秒" onClick="startAuto(30)"></cui:button>
				<cui:input id="txtCustomWaitTime" placeholder="自定义时间（最低3秒）"/> 
				<cui:button label="自定义" id="btnCustomWaitTime"></cui:button>
			</div>
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="regionCameraTree" asyncEnable="true" keepParent="true"
						asyncType="post"  simpleDataEnable="true" onLoad="regionCameraTreeOnLoad" 
						
						asyncUrl=""
						onDblClick="regionCameraTreeOnDblClick" onClick="regionCameraTreeOnClick"
						onRightClick="regionCameraTreeOnRightClick" formatter="cameraFormatter"
						asyncAutoParam="id,name" rootNode="true"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>
		<%--<h3>公共预案</h3>
		<div class="vedio-panel" style="width:100%">
			<div class="panel-toolbar">
				<cui:input texticons="" placeholder="Search"
				onKeyPress="groupCommonSearch"></cui:input>
				<cui:button id="groupCommonTreeExpendAll" label="展开"
				onClick="groupCommonTreeExpendAll"></cui:button>
			</div>
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="groupCommonTree" asyncEnable="true" keepParent="true"
						asyncType="post" showLine="true" simpleDataEnable="true"
						asyncUrl=""
						asyncAutoParam="id,name" onDblClick="group_onDblClick" rootNode="true" formatter="cameraFormatter"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>
		<h3>自定义预案</h3>
		<div class="vedio-panel" style="width:100%">
			<div class="panel-toolbar">
				<cui:input  texticons="" placeholder="Search" onKeyPress="groupDIYSearch"></cui:input>
				<cui:button id="groupDIYTreeExpendAll" label="展开" onClick="groupDIYTreeExpendAll"></cui:button>
			</div>
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="groupDIYTree" asyncEnable="true" keepParent="true"
						asyncType="post" showLine="true"  simpleDataEnable="true" 
						asyncUrl=""
						asyncAutoParam="id,name" onDblClick="group_onDblClick" rootNode="true" formatter="cameraFormatter"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>--%>
		<h3>设置</h3>
		<div id="divSysSet" class="auto-body auto-body-transition auto-body-close">
			<ul class="sys-set" oncontextmenu="return false">
				<div class="line">---------------视频客户端设置-------------</div>
				<li id="topMost" eventType="1">
					<span>视频置顶显示</span>
					<span class="chkbox"></span>
				</li>
				<li id="showFormTitle" eventType="1">
					<span>显示标题栏</span>
					<span class="chkbox checked"></span>
				</li>
				<li id="showVideoTitle" eventType="1">
					<span>显示视频工具栏</span>
					<span class="chkbox"></span>
				</li><br>

				<div class="line">----------------自动翻页设置-------------</div>
				<li id="useDefAutoTime" eventType="2">
					<span>使用固定时间间隔翻页</span>
					<span class="chkbox"></span>
				</li>
				<li id="setDefAutoTime" class="disabled">
					<span>设置固定时间间隔</span>
					<span style="float: right; padding: 0px 1px; margin-right: 5px;">秒</span>
					<input id="txtDefAutoTime" class="btn autotime" value="3" disabled="disabled">
				</li><br>

				<div class="line">----------------预案操作设置-------------</div>
				<li id="autoSetLayout">
					<span>根据打开视频数量自动调整布局</span>
					<span class="chkbox checked"></span>
				</li><br>
				
				<div class="line">----------------视频打开选项-------------</div>
				<li id="cameraOnClick" eventType="3">
					<span>选择左键单击打开视频</span>
					<span class="chkbox"></span>
				</li>
				<!-- <li id="showEmptyNode">
					<span>显示空的区域列表</span>
					<span class="chkbox"></span>
				</li> -->
			</ul>
		</div>
		
	</cui:accordion>

	<!-- 布局画面的模板 -->
	<div id="template">
		<div class="my-layout-panel" onclick="setLayout('{index}')">
			<div class="layout-img"
				style="background-image: url(${ctx}/static/module/video/img/layout-{index}.png);"></div>
			<div class="layout-desc">{number}画面</div>
		</div>
	</div>
</div>


<script src="${ctx}/static/system/videoClient.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/hz.mask.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<script type="text/javascript">

var chkboxType = {
	'Y' : 'ps',
	'N' : 'ps'
}
//面板第一次触发时置为true，同时加载树，此后不在重复加载
var accordion_0=false;
var accordion_1=false;
var accordion_2=false;

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人

//阻止#regionCameraTree鼠标右键默认事件
$(document).on("contextmenu","#regionCameraTree",function(e){
	e.preventDefault();
	});//阻止鼠标右键默认事件
$.parseDone(function() {
	$(".mscroll").mCustomScrollbar({
		theme:"minimal-dark",
		autoExpandScrollbar:true,
		mouseWheelPixels:130//鼠标滚动一下滑动多少像素
	/* 	advanced:{
			autoScrollOnFocus:false
		} */
	}); 
	//初始化树
	$("#regionCameraTree").tree("option","asyncUrl","${ctx}/provGroupManage/allPrisonAreaCameraTree.json");
	$("#groupCommonTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupMemberTree.json?gmaUseRange=0&gmaTypeIndc=1&gmaCusNumber="+cusNumber);	
	$("#groupDIYTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupMemberTree.json?gmaUseRange=1&gmaTypeIndc=1&gmaCusNumber="+cusNumber+"&gmaCrteUserId="+userId);
	//加载第一个面板
	$("#regionCameraTree").tree("reload");
	
	initLayout();
	initBtnEvent();
	initSet();
	getConfigs();
})
//树加载完执行
function regionCameraTreeOnLoad(event,id,data){
	initcameraNodes();
	$.parser.parse();
}
//初始化摄像机集合
function initcameraNodes(){
	
	//获取摄像机节点
	var cameraNodes=$('#regionCameraTree').tree("getNodesByParam","node_type","camera");
	console.log(cameraNodes);
	
	initCameraTree(cameraNodes);
	
}
//单击
function regionCameraTreeOnClick(event, id, node){
	if(node.node_type=="camera"){
		if(isClickOpenVideo == '1'){
			__openVideo(node);
		}else{
			__setIndex(node);
		}	
	}	
}
//双击
function regionCameraTreeOnDblClick(event, id, node){
	if(node.node_type=="camera"){
		if(isClickOpenVideo == '0'){
			__openVideo(node);
		}	
	}	
}
//右击
function regionCameraTreeOnRightClick(event, id, node){
	if(node.node_type=="camera"){
		if(isClickOpenVideo == '1'){
			$('#regionCameraTree').tree("cancelSelectedNode");
			$('#regionCameraTree').tree("selectNode",node,true);
			__setIndex(node);
		}	
	}	
}

//预案双击
function group_onDblClick(event, id, node){
	//如果cameraCtrl为null,说明视频列表还没有加载完
	if(cameraCtrl){
		if( cameraCtrl.autoTimerId ){
			_confirm('当前操作会停止自动翻页，是否继续？', function(result){
				if( result ){
					stopAuto();
					openGroup(node);
				}
			});
		} else {
			if(node.node_type=="camera"){
				__openVideo(node);
			}else{
				openGroup(node);
			}
		}
	}else{
		if(node.node_type=="camera"){
			__openVideo(node);
		}else{
			openGroup(node);
		}
	}
}

function treebeforeDrag(treeId, treeNodes) {
    for (var i=0,l=treeNodes.length; i<l; i++) {
        if (treeNodes[i].drag === false) {
            return false;
        }
    }
}
function treeonDrag(event, treeId, treeNodes){
    console.log("treeonDrag")
}
function treeonDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
    console.log("treeonDrop")
}
//切换面板
function spya_onActivate(){
	reViewMode =$("#accordion_spya").accordion("option","active");
	
	if(reViewMode==0){//视频列表
		if(!accordion_0){
			$("#regionCameraTree").tree("reload");
			accordion_0=true;
		}	
	}
	else if(reViewMode==1){//公共预案
		if(!accordion_1){
			$("#groupCommonTree").tree("reload");
			accordion_1=true;
		}
		
	}else if(reViewMode==2){//自定义预案
		if(!accordion_2){
			$("#groupDIYTree").tree("reload");
			accordion_2=true;
		}
		
	}
}
</script>
