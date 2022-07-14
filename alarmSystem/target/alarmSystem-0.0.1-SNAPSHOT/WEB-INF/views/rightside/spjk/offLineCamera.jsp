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
</style>

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoFor.css" />
<link rel="stylesheet" type="text/css" href="/prison/static/module/video/css/rightVideoPlan.css">
<div class="cameraGroup" style="height:96%;width:98%">
	<cui:accordion id="accordion_reightVideo" heightStyle="fill" icons="accordionIcon">
	<h3>离线摄像头</h3>
	<div class="menuDiv">
		<div class="panel" style="float: right;width: 100%; height: 100%">
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="offLineCameraTree" asyncEnable="true" keepParent="true"
						asyncType="post"  simpleDataEnable="true" 		
						asyncUrl=""  onClick="openVideo"
						formatter="cameraFormatter"
						asyncAutoParam="id,name" rootNode="true"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>
	</div>
	</cui:accordion>
</div>
	<input type="hidden" id="index"/>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<script type="text/javascript">
var accordionIcon = {
		activeHeader: " "
};
var jsConst = window.top.jsConst;
var videoClient = window.top.videoClient;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID
$.parseDone(function(){
	$(".mscroll").mCustomScrollbar({
		theme:"minimal-dark",
		autoExpandScrollbar:true,
		mouseWheelPixels:130//鼠标滚动一下滑动多少像素
	});   
	//初始化树
	if(jsConst.USER_LEVEL==1){
		$("#offLineCameraTree").tree("option","asyncUrl","${ctx}/provGroupManage/allPrisonAreaCameraTree.json?cbdSttsIndc_except=0");
	}else if(jsConst.USER_LEVEL==2){
		$("#offLineCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cbdSttsIndc_except=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
	}else if(jsConst.USER_LEVEL==3){
		$("#offLineCameraTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cbdSttsIndc_except=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
	}
	$("#offLineCameraTree").tree("reload");
	$("#offLineCameraTree").tree("option","onLoad",function(){
		$("#offLineCameraTree").tree("filterNodesByParam", {node_type:"camera"}); 
	});
});
function openVideo(event, id, node){
	if(node.node_type=="camera"){
		videoClient.playVideoHandle({
			'subType': 1, //1 手动 2 自动
			'layout': 1,
			'data': [{
				'index': 0,
				'cameraId': node.id
			}],
			'callback': function (data) {
			}
		});
	}	
}
</script>