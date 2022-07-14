<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
.menuDiv{
	height:100%;
}
.menuList{
	height:100%;
}
.coral-accordion {
    color: #fff;
}

.bfvideo {

    color: #0be300;
}

.ztvideo{
    color: red;
}
</style>

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoFor.css" />
<link rel="stylesheet" type="text/css" href="/prison/static/module/video/css/rightVideoPlan.css">
<div class="cameraGroup" style="height:96%;width:98%">
	<cui:accordion id="accordion_reightVideo" heightStyle="fill" icons="accordionIcon">
	<h3>视频轮巡</h3>
	<div class="menuDiv">
		<div class="panel" style="float: right;width: 100%; height: 100%">
			<div class="panel-position" style="text-align:center;">
				<span class="comboboxlabel">画面布局</span>
				<cui:combobox id="layout" style="width: 162px;" emptyText="请选择" data=""></cui:combobox>
		    </div>
		    <div class="panel-position" style="text-align:center;">
				<span class="comboboxlabel">轮巡模式</span>
				<cui:combobox id="for" style="width: 162px;" emptyText="请选择" data=""></cui:combobox>
		    </div>
		    <div class="panel-position" style="text-align:center;">
				<span class="comboboxlabel">轮巡间隔</span>
				<cui:combobox id="time" style="width: 162px;" emptyText="请选择" data=""></cui:combobox>
		    </div>
		    <div style="float:left;width: 100%;padding: 10px;color: #fff;" align="center">
		    	<div class="leftdot">--------------</div>
		    	<span id="forText">单屏轮巡  </span><span id="layoutText">单画面</span><span id="videoText"></span>
		    	<div class="rightdot">--------------</div>
		    </div>

			<div class="menuList">
				<div class="li-hover"></div>

				<div class="tree" style="float: left;width: 99%; height: 65%; overflow: auto;" id='menuTree' >
                   <%-- <div style="width:100%;height:50%;overflow: auto;">--%>
                        <cui:tree id="regionCameraTree" asyncEnable="true" keepParent="true"
                                  asyncType="post"  simpleDataEnable="true" onLoad="regionCameraTreeOnLoad"
                                  checkable="true"  chkboxType="chkboxType" chkStyle="checkbox"
                                  asyncUrl=""
                                  formatter="cameraFormatter"
                                  asyncAutoParam="id,name" rootNode="true"
                                  showRootNode="true">
                        </cui:tree>
                   <%-- </div>--%>
					<cui:tree id="rightVideoForTree" asyncEnable="true" keepParent="true"
						asyncType="post" showLine="true" simpleDataEnable="true" 
						checkable="true" chkboxType="chkboxType" chkStyle="checkbox"
						asyncUrl=""
						asyncAutoParam="id,name" onDblClick="rightVideoForTreeOnDblClick" rootNode="true" 
						showRootNode="true" formatter="cameraFormatter">
					</cui:tree>
				</div>
                <div class="playPanel">
                    <div style="margin: -2px 0px 0px -1px;">
                        <div id="timeText" >
                            <span style="font-size: 20px;">10秒</span>
                        </div>
                        <div id="left">
                            <img src="${ctx}/static/module/video/img/left.png">
                        </div>
                        <div id="playBox" class="bfvideo" style="margin-top: 18px;font-size: 20px;width: 40px" >播放</div>
                        <div id="right">
                            <img src="${ctx}/static/module/video/img/right.png">
                        </div>
                        <div id="stop" style="margin: 10px 50px 0px 130px;" >
                            <span style="font-size: 20px;color: red">停止</span>
                        </div>
                    </div>

                </div>
			</div>

		</div>
	</div>
	</cui:accordion>
</div>
	<input type="hidden" id="index"/>
<script src="${ctx}/static/system/videoClient.js"></script>
<script type="text/javascript"
        src="${ctx}/static/module/video/js/hz.mask.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<script type="text/javascript">
var accordionIcon = {
		activeHeader: " "
};
var chkboxType = {
		'Y' : 'ps',
		'N' : 'ps'
	}
var jsConst = window.top.jsConst;
var videoClient = window.top.videoClient;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;
//阻止#regionCameraTree鼠标右键默认事件
$(document).on("contextmenu","#regionCameraTree",function(e){
    e.preventDefault();
});//阻止鼠标右键默认事件
$.parseDone(function(){
	$(".mscroll").mCustomScrollbar({
		theme:"minimal-dark",
		autoExpandScrollbar:true,
		mouseWheelPixels:130//鼠标滚动一下滑动多少像素
	});
	//$("#rightVideoForTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupMemberTree.json?gmaTypeIndc=1&id=${grpId }&gmaCusNumber="+cusNumber);
   // $("#regionCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
    //初始化树
   // $("#regionCameraTree").tree("option","asyncUrl","${ctx}/provGroupManage/allPrisonAreaCameraTree.json");
    if(jsConst.USER_LEVEL==2){
        $("#regionCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
    }else if(jsConst.USER_LEVEL==3){
        $("#regionCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
    }else {//省局用户
        $("#regionCameraTree").tree("option","asyncUrl","${ctx}/provGroupManage/allPrisonAreaCameraTree.json");
    }
	$("#rightVideoForTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupMemberTree.json?gmaTypeIndc=1&gmaCusNumber="+cusNumber);
    $("#regionCameraTree").tree("reload");
	$("#rightVideoForTree").tree("reload");

	setCombobox(queryGrpInfo);
    /*$("#regionCameraTree").tree("option","onLoad",function(){
        loadForEvents();
    });*/

	$("#rightVideoForTree").tree("option","onLoad",function(){
		loadForEvents();
	});

});

//树加载完执行
function regionCameraTreeOnLoad(event,id,data){
    initcameraNodes();
    $.parser.parse();
}
//初始化摄像机集合
function initcameraNodes(){
    console.log("========== initcameraNodes start ==========");
    //获取摄像机节点
    var cameraNodes=$('#regionCameraTree').tree("getNodesByParam","node_type","camera");
    console.log(cameraNodes);

    initCameraTree(cameraNodes);
    console.log("========== initcameraNodes end ==========");
}
/**
 * 加载所有群组列表
 */
function queryGrpInfo(){
	return;
	var thisCusNumber = jsConst.CUS_NUMBER;
	var cusNumber = '${cusNumber}';
	if(cusNumber){
		thisCusNumber = cusNumber;
	}
	var params = new Array();
	params.push(thisCusNumber);
	params.push(1, jsConst.DEPARTMENT_ID);
	params.push(1, jsConst.DEPARTMENT_ID);
	params.push(1, jsConst.DEPARTMENT_ID);
   	window.top.queryCtrl({
   		'sqlId': 'sys_query_group_for_tree_2',
   		'wid': 1,
   		'oid': 0,
   		'data': [thisCusNumber, jsConst.USER_ID],
   		'callback': function (result) {
			if(result.success){
			   	window.top.queryCtrl({
			   		'sqlId': 'cds_grp_queryRltnDtlsList',
			   		'wid': 2,
			   		'oid': 2,
			   		'data': params,
			   		'callback': function(data){
						if (data.success) {
							parseData(result.obj.data, data.obj.data);
						}
					}
			   	});
			}
		}
   	});
}

/*
 * 解析数据
 */
function parseData (grpData, dvcData) {
	var list = new Array();

	/*
	 * 创建一个节点
	 */
	function _createNode (data) {
		return {"id":data.TREE_ID, "name":data.TEXT, "childList":[]};
	}

	/*
	 * 查找父节点
	 */
	function _findParent (list, pid) {
		for(var i = 0; i < list.length; i++){
			var temp = list[i];
			if( temp.id == pid && temp.childList ){
				return temp;
			} else {
				if( temp.childList ){
					temp = _findParent(temp.childList, pid);
					if( temp ){
						return temp;
					}
				}
			}
		}
		return null;
	}

	/*
	 * 数据格式化
	 */
	while (grpData.length) {
		// 群组分类
		var group = grpData.shift();
		var node = _createNode(group);
		var parentId = group.PARENT_TREE_ID;
		if( parentId ){
			var parent = _findParent(list, parentId);
			if( parent && parent.childList ){
				parent.childList.push( node );
			}
		} else {
			list.push( node );
		}

		// 关联摄像机
		var tempList = [];
		while (dvcData.length) {
			var dvcInfo = dvcData.shift();
			if( dvcInfo.GRD_GRP_ID == node.id ){
				node.childList.push({
					"id": dvcInfo.GRD_MMBR_IDNTY, 
					"name": dvcInfo.GRD_MMBR_NAME
				});
			} else {
				tempList.push(dvcInfo);
			}
		}
		if( tempList.length ){
			dvcData = tempList;
		}
	}

	setObjects($(".menuDiv"));
	loadDvcList(list,true);
}

/**
 * 设置Combobox控件并填充数据
 */
function setCombobox (handle) {
   	var data = [
   		{"text":"单画面","value":'1'},
   		{"text":"双画面","value":'2'},
   		{"text":"三画面","value":'3'},
   		{"text":"四画面","value":'4'},
   		{"text":"五画面","value":'5'},
   		{"text":"六画面","value":'6'},
   		{"text":"七画面","value":'7'},
   		{"text":"八画面","value":'8'},
   		{"text":"九画面","value":'9'},
   		{"text":"十画面","value":'10'},
   		{"text":"十一画面","value":'11'},
   		{"text":"十二画面","value":'12'},
   		{"text":"十三画面","value":'13'},
   		{"text":"十四画面","value":'14'},
   		{"text":"十五画面","value":'15'},
   		{"text":"十六画面","value":'16'}
   	];

   	$("#layout").combobox({
   		data:data,
   		valueField:'value',
   		textField:'text',
   		onSelect : function(){
   			$("#layoutText").html($("#layout").combobox("getText"));
   			var layout = $("#layout").combobox("getValue");
   			// console.log("rightVideoFor.jsp layout = " + layout);
			if(jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE) {
				videoClient.setLayout(layout);
				$("#stop").click();
			}else if(jsConst.VIDEO_PLAYER_TYPE == '1') {
				// 视频插件播放器显示
				if (jsConst.MAP_TYPE == '0') {// 二维

				} else {// 三维
					$("div[id='map3d_container']").hide();
					$("div[id='signx_show']").show();
				}
				// 布局初始化
				videoPlugin.setLayout($("#signx_show"), layout);
			}
	   		
	   		if (layout == 1){
	   			$("#for").combobox('select', 1);
	   			$("#for").combobox('disable');
	   		} else {
	   			$("#for").combobox('enable');
	   		}
   		}
   	});

   	$("#layout").combobox("setValue","请选择");

	var data = [
   		{"text":"单屏轮巡","value":'1'},
   		{"text":"多屏轮巡","value":'2'},
   		/* {"text":"自定义区域轮巡","value":'3'},
   		{"text":"自定义路线轮巡","value":'4'} */
   	];
   	$("#for").combobox({
   		data:data,
   		valueField:'value',
   		textField:'text',
   		onSelect : function(){
   			$("#forText").html($("#for").combobox("getText") + " > ");
   			if(jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE) {
				$("#stop").click();
   			}
   		}
   	});
   	$("#for").combobox("setValue","请选择");

   	var data = [
   		{"text":"5秒","value":'5'},
   		{"text":"10秒","value":'10'},
   		{"text":"15秒","value":'15'},
   		{"text":"20秒","value":'20'},
   		{"text":"25秒","value":'25'},
   		{"text":"30秒","value":'30'},
   		{"text":"35秒","value":'35'},
   		{"text":"40秒","value":'40'},
   		{"text":"50秒","value":'50'},
   		{"text":"1分钟","value":'60'},
   		{"text":"2分钟","value":'120'},
   		{"text":"3分钟","value":'180'},
   	];
   	$("#time").combobox({
   		data:data,
   		valueField:'value',
   		textField:'text',
   		onSelect : function(){
   			$("#timeText span").html($("#time").combobox("getValue") + "秒");
   		}
   	});
   	$("#time").combobox("setValue",'10');
   	console.log("------");
   	handle();
}

/**
 * 选择数据验证
 */
function checkSelect(){
	var flag = true;
	var layout = $("#layout").combobox("getValue");
	var fors = $("#for").combobox("getValue");
	var checked = false;
	/* $('.menuList').find('[name="child"]').each(function(){
		var check = $(this).attr("checked");
		if(check){
			checked = true;
		}
	}); */
	var checkedNodes=$("#rightVideoForTree").tree("getCheckedNodes");

	//var checkedcNodes regionCameraTree
    var checkedRegionCameraTreeNodes = $("#regionCameraTree").tree("getCheckedNodes");
    debugger;

	if(checkedNodes.length>0 || checkedRegionCameraTreeNodes.length >0){
		checked = true;
	}


	if(layout == "" || layout == "请选择"){
		_alert("请选择画面布局");
		flag = false;
	}else if(fors == "" || fors == "请选择"){
		_alert("请选择轮巡模式");
		flag = false;
	}else if(!checked){
		_alert("请勾选至少一个视频");
		flag = false;
	}
	return flag;
}

//add by zk
function _alert(msg){
	$.messageBox( {
		iframePanel:true,
        message:msg,
        position: {
            my: "right top",   //与上述类似，此为右上角
            at: "right top",
            of: window
        }
    });  
}

function _click () {
	debugger;
	if (checkSelect()) {
		var playBox = $("#playBox");
		/*if (playBox.hasClass('start')) {
			playBox.removeClass('start').addClass('stop');
			breakOffFor();
		}else{
			playBox.removeClass('stop').addClass('start');
			startFor();
		}*/
        if (playBox.html()=="暂停") {
           // playBox.removeClass('start').addClass('stop');
            playBox.html("播放");
            playBox.removeClass('ztvideo').addClass('bfvideo');
            breakOffFor();
        }else{
            playBox.removeClass('bfvideo').addClass('ztvideo');
            playBox.html("暂停")
            startFor();
        }

	}
}

/**
 * 加载轮巡事件
 */
function loadForEvents () {
	debugger;
	//开始、暂停按钮
	$("#playBox").click(_click);

	//左边按钮点击事件
	$("#left").click(function(){
		if(checkSelect()){
			startFor("left");
		}
	});
	//右边按钮点击事件
	$("#right").click(function(){
		if(checkSelect()){
			startFor("right");
		}
	});
	//停止点击事件
	$("#stop").click(function(){
		breakOffFor();
		var num = $("#time").combobox("getValue");
		$("#timeText span").html(num + "秒");
		time = null;
		flag = true;
		startNum = 0;
		selectNum = 0;
		videoList = [];
		setTitleColor([]);
		//$("#playBox").removeClass('').addClass('stop');
        $("#playBox").html("播放");
		if(jsConst.VIDEO_PLAYER_TYPE == '1') {
			// 清空视频播放器
			videoPlugin.videoPlayerClear();

			$("#signx_show").html("<img id='planeLayer'>");
			initToolbar();
			$.sign_show.bindSign('#layerImgDivShow #signx_show');//初始化
		}
	});
	//复选框点击事件
	$(".menuList").find('input[type="checkbox"]').click(function(){
		$("#stop").click();
	});

	var grpId = '${grpId}';
	// 	location.search.replace('?', '');
	if (grpId) {
		if(jsConst.VIDEO_PLAYER_TYPE=='0'){
			setVideoFor(videoClient.curLayout || 9, grpId);
		}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){

		}else if(jsConst.VIDEO_PLAYER_TYPE=='2'){

		}
	}
}

/**
 * 开始轮巡
 */
var videoList = [];
var play = false;
function startFor (obj) {
	debugger;
	videoList = [];
	var i = 0;
	/* $('.menuList').find('[name="child"]').each(function(){
		var checked = $(this).attr("checked");
		if(checked){
			var videoSpan = $(this).parent();
			var videoId = videoSpan.attr("scs-videoid");
			videoList[i] = videoId;
			i++;
		}
	}); */
	
	var checkedNodes=$("#rightVideoForTree").tree("getCheckedNodes");
	var j=0;
	if(checkedNodes.length>0){
		for(var i=0;i<checkedNodes.length;i++){
			if(checkedNodes[i].node_type=="camera"){
				videoList[j++]=checkedNodes[i];
			}
		}
	}
//新增 lh
	var checkNodesRegionCameraTree =$("#regionCameraTree").tree("getCheckedNodes");
	if(checkNodesRegionCameraTree.length>0){
        for(var i=0;i<checkNodesRegionCameraTree.length;i++){
            if(checkNodesRegionCameraTree[i].node_type=="camera"){
                videoList[j++]=checkNodesRegionCameraTree[i];
            }
        }
    }

	
	if (obj == undefined) {
		play = true;
		startTime();
	}else{
		startTime(obj);
	}
}
/**
 * 轮巡中、启动倒计时
 */
var time = null;
var flag = true;
var startNum = 0;
var selectNum = 0;
function startTime (obj) {
	debugger;
	var layout = parseInt($("#layout").combobox("getValue"));
	var fors = parseInt($("#for").combobox("getValue"));
	var forStatus = videoList.length > layout;
	if(!forStatus && !flag &&  fors != 1){
		return;
	}
	var next = false;
	if(obj != undefined && forStatus){
		next = true;
		flag = true;
		clearTimeout(time);
		if(obj == "left"){
			startNum = (startNum == 0 ? videoList.length : startNum);
			if(fors == 1){
				startNum = startNum - 2;
				if(startNum < 0){
					startNum = videoList.length - 1;
				}
			}else if(fors == 2){
				
				if(startNum <= layout){
					startNum = videoList.length - layout;
				}else if(startNum < 0){
					startNum = startNum - selectNum;
				}else{
					startNum = startNum - (layout + selectNum);
				}
			}
		}
	}
	if(flag){
		flag = false;
		var indexs = [];
		var forVideoList = [];
		if(fors == 1){
			if(jsConst.VIDEO_PLAYER_TYPE=='0'){
				videoClient.getIndex(function (data) {
					if (!data.success) {
						_alert(data.msg);
						return false;
					}
					indexs[0] = data.obj;
					forVideoList[0] = videoList[startNum];
					startNum++;
					if(startNum == videoList.length){
						startNum = 0;
					}
					todo();
				});
			}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
				indexs[0] = videoPlugin.getNextIndexOfVideoWindow();
				forVideoList[0] = videoList[startNum];
				startNum++;
				if(startNum == videoList.length){
					startNum = 0;
				}
				todo();
			}
		}else if(fors == 2){
			for(var i=0;i<videoList.length;i++){
				if(startNum == videoList.length){
					startNum = 0;
					break;
				}
				if(i < layout){
					indexs[i] = i;
					forVideoList[i] = videoList[startNum];
					startNum++;
				}
			}
			todo();
		}

		function todo () {
			setTitleColor(forVideoList);
			
			var cameraList = [];
			while(indexs.length){
				cameraList.push({
					'index': indexs.shift(),
					'cameraId': forVideoList.shift().id
				});
			}

			if(jsConst.VIDEO_PLAYER_TYPE=='0'){
				videoClient.playVideoHandle({
					'subType': 2,
					'layout': layout,
					'data': cameraList,
					'callback': function(data){
						//data.success || _click();
					}
				}) || _click();
			}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
				// 视频插件播放器容器显示
				if (jsConst.MAP_TYPE == '0') {// 二维

				} else {// 三维
					$("div[id='map3d_container']").hide();
					$("div[id='signx_show']").show();
				}
				videoPlugin.multiVideoPlayHandle({
					'container': $("#signx_show"),
					'subType': 2,
					'layout': layout,
					'data': cameraList,
					'callback': function (data) {
					}
				}) || _click();
			}
	 		if(!forStatus &&  fors != 1){
				clearTimeout(time);
				return;
			}
		}
	}
	var num = parseInt($("#timeText span").html().replace("秒","")) - 1;
	if(num == 0 || next){
		num = $("#time").combobox("getValue");
	}
	$("#timeText span").html(num + "秒");
	flag = num == 1;
	if(play){
		time = setTimeout(startTime,1000);
	}
}
/**
 * 暂停轮巡
 */
function breakOffFor(){
	clearTimeout(time);
	play = false;
	console.log("rightVideoFor breakOffFor jsConst.VIDEO_PLAYER_TYPE = " + jsConst.VIDEO_PLAYER_TYPE);
	if(jsConst.VIDEO_PLAYER_TYPE == '1') {
		videoPlugin.stopVideoPlayInterval();
	}
}
/**
 * 设置在轮巡的摄像机标题高亮显示
 */
function setTitleColor (forVideoList) {
	$('#rightVideoForTree').tree("cancelSelectedNode");
    $('#regionCameraTree').tree("cancelSelectedNode");
	if(forVideoList.length>0){
		for(var i=0;i<forVideoList.length;i++){
			$('#rightVideoForTree').tree("selectNode",forVideoList[i],true);
            $('#regionCameraTree').tree("selectNode",forVideoList[i],true);
		}
	}
	
	console.log("forVideoList    lh ============"+forVideoList);
	selectNum = forVideoList.length;
	
	
	 /*$('.menuList').find('ul.tree-child >li').each(function(){
		var videoSpan = $(this).find(">span");
		var videoId = videoSpan.attr("scs-videoid");

		videoSpan.css("color","#fff");

		for(var i=0;i<forVideoList.length;i++){
			if (videoId == forVideoList[i]) {
				if (videoSpan.find('[name="child"]').attr("checked")) {
					videoSpan.css("color","#66ffff");
				}
			}
		}
	}); */
}


/*
 * 设置视频轮循参数
 */
function setVideoFor(layout, grpId){
	debugger;
	// 设置布局
	$("#layout").combobox("setValue", layout.toString());
	$("#layoutText").html($("#layout").combobox("getText"));

	// 设置轮巡模式
	$("#for").combobox("setValue", '2');
	$("#forText").html($("#for").combobox("getText") + " > ");

	// 设置轮巡时间
	$("#time").combobox("setValue", '20');
	$("#timeText span").html($("#time").combobox("getValue") + "秒");

	// 设置群组
	var grpNode=$("#rightVideoForTree").tree("getNodeByParam","id",grpId);
	$("#rightVideoForTree").tree("expandNode",grpNode,true);
	var childrenNodes=$("#rightVideoForTree").tree("getNodesByParam","pId",grpId);
	for(var index in childrenNodes){
		$("#rightVideoForTree").tree("checkNode",childrenNodes[index],"true");
	}
	
	/* var key = 'grp_Id_' + grpId;
	$("input[id='" + key + "']").attr('checked', 'checked');

	//获取当前选中的节点的父节点
	var thisNode = $("#" + key);
	var parentNode = thisNode.next();
	var p_parentNode = thisNode.parent().parent();
	
	p_parentNode.show();
	p_parentNode.prev().removeClass('node-up');

	//改变样式(小箭头向下)
	thisNode.removeClass('node-up');

	//展开
	parentNode.show();
	$("ul[parent-id='"+ key + "']").find(':checkbox').attr('checked', 'checked'); */

	_click();
}
/**
 * 查找父节点ID并展开节点
 */
function findParent (thisNode) {
	var nodeId = '';
	function getParentId (node) {
		var  _nodeId = node.attr('parent-id');
		var  parent =  node.parent().parent();
		var  parentId = parent.attr('parent-id')
		if( _nodeId != undefined && parentId == undefined ){
			nodeId =  _nodeId;
		}else{
			var parentNode = $("ul[parent-id ='"+parentId+"']");
			var parentDiv = $("div[id='" + parentId + "']");
			//改变样式(小箭头向下)
			parentDiv.removeClass('node-up');
			//展开
			parentNode.show();
			getParentId(parent);
		}
	};
	getParentId(thisNode);
	return nodeId;
}
//双击
function rightVideoForTreeOnDblClick(event, id, node){
	if(node.node_type=="camera"){
		var dvcId = node.id;
		if (dvcId) {
			if (jsConst.VIDEO_PLAYER_TYPE == '0') {
				videoClient.getIndex(function (data) {
					if (data && data.success) {
						var index = data.obj;
						//index为-1说明视频窗口关闭,此时设置为0,即第一个index---add by zk
						if(index == -1){
							index=0;
						}
						// 打开视频
						videoClient.playVideoHandle({
							'subType': 1,
							'layout': videoClient.curLayout,
							'data': [{
								'index': index,
								'cameraId': dvcId
							}],
							'callback': function (data) {

							}
						});
					} else {
						alert(data.msg);
					}
				});
			}else if(jsConst.VIDEO_PLAYER_TYPE=='1'){
				// 视频插件播放器容器显示
				if (jsConst.MAP_TYPE == '0') {// 二维

				} else {// 三维
					$("div[id='map3d_container']").hide();
					$("div[id='signx_show']").show();
				}
				// 下一个播放器index
				var index = videoPlugin.getNextIndexOfVideoWindow();

				// 播放选中的摄像头实时视频
				videoPlugin.multiVideoPlayHandle({
					'container': $("#signx_show"),
					'subType': 1,
					'layout': videoPlugin.curLayout,
					'data': [{
						'index': index,
						'cameraId': dvcId
					}],
					'callback': function (data) {
					}
				});
			}
		}
	}	
}
</script>