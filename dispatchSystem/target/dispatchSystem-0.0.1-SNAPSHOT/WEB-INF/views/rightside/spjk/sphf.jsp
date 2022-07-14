<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/hzmask.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/rightVideoPlan.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/video/css/playbackVideo.css" />
<link rel="stylesheet" type="text/css"
      href="${ctx}/static/module/dist/css/flexDate.min.css" />
<style>
.form-control {
	width: 100%;
}
.dateClass{
    width: 200px;
    text-align: center;height: 25px;
    font-weight: bold
}
</style>

<div id="cameraGroup" class="cameraGroup" style="height:96%;width:98%;">
	<input id="btnLayout" type="button"
		class="layout-set-btn layout-set-btn-hover" title="布局"
		onclick="toggleLayout(this)">
	<div id="divLayout" class="div-layout div-layout-transition">
		<div class="inner-panel"></div>
	</div>
	<cui:accordion id="accordion_sphf" onActivate="sphf_onActivate" heightStyle="fill">
		<h3>单点多时段&nbsp;&nbsp;&nbsp;<span style="color: red">(请选择画面布局)</span></h3>
		<div id="active_0" class="multi-date" >
			<div class="tabPlayback_box">
				<div id="tabPlayback" class="tabPlayback">
					<%--<cui:datepicker id="startTime0" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="开始时间"></cui:datepicker>--%>
                        <input type="text" id="startTime0" placeholder="开始时间"  class="flexDate dateClass" format="yyyy-MM-dd HH:mm:ss" clearBtn="true" todayBtn="true" />
					<span class="date-seperate">至</span>
					<%--<cui:datepicker id="endTime0" startDateId="startTime0" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="结束时间"></cui:datepicker>--%>
                        <input type="text" id="endTime0" placeholder="结束时间"  class="flexDate dateClass" format="yyyy-MM-dd HH:mm:ss" clearBtn="true" todayBtn="true" />
				</div>
			</div>
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="dddsdTree" asyncEnable="true" keepParent="true"
						asyncType="post" simpleDataEnable="true"
						asyncUrl=""
						 asyncAutoParam="id,name" rootNode="true" formatter="cameraFormatter"
						 onDblClick="loadBackHandle_camera" 
						showRootNode="true">
					</cui:tree>
				</div>
				<!-- 这个是不是没什么用？先注释掉了
				<div class="layout_table">
					<table></table>
				</div> -->
			</div> 
			
		</div>
		<h3>多点同时段</h3>
		<div id="active_1"  class="multi-date">
			<div class="tabPlayback_box"></div>
			<div class="divTree_box"></div>
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="ddtsdTree" asyncEnable="true" keepParent="true"
						asyncType="post" simpleDataEnable="true"
						asyncUrl=""
						asyncAutoParam="id,name" onDblClick="loadBackHandle_camera" rootNode="true" 
						onClick="" formatter="cameraFormatter"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>
		<h3>多点不同时段</h3>
		<div  id="active_2">
			<div class="tree-box">
				<div style="width:100%;height:100%;" class="mscroll">
					<cui:tree id="ddbtsdTree" asyncEnable="true" keepParent="true"
						asyncType="post" simpleDataEnable="true"
						asyncUrl=""
						asyncAutoParam="id,name" onDblClick="loadBackHandle_camera" rootNode="true" 
						onClick="" formatter="cameraFormatter"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
			<!-- 这个是不是没什么用？先注释掉了
			<div class="layout_table" style="height: 180px;">
				<table style="height: 180px;"></table>
			</div>-->
		</div>
	</cui:accordion>
	
	<!-- 回放时间弹出框 -->
<cui:dialog  id="dateWin" iframePanel="true" autoOpen="false" position="{ my: 'right center', at: 'right center', of: window }">
<center>
	<table class="table" width="90%">
		<tr>
			<td>			
				<%--<cui:datepicker id="startDate" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="开始时间" componentCls="form-control"></cui:datepicker>--%>
                    <input type="text" id="startDate" placeholder="开始时间" class="flexDate" format="yyyy-MM-dd HH:mm:ss" clearBtn="true" todayBtn="true"/>
			</td>
		</tr>
		<tr>
			<td>			
				<%--<cui:datepicker id="endDate" startDateId="startDate" dateFormat="yyyy-MM-dd HH:mm:ss" placeholder="结束时间" componentCls="form-control"></cui:datepicker>--%>
                    <input type="text" id="endDate" placeholder="结束时间"  class="flexDate" format="yyyy-MM-dd HH:mm:ss" clearBtn="true" todayBtn="true"/>
			</td>
		</tr>
	</table>
</center>
</cui:dialog>
	
	<!-- 布局画面的模板 -->
	<div id="template">
		<div class="my-layout-panel" onclick="setLayout_hf('{index}')">
			<div class="layout-img"
				style="background-image: url(${ctx}/static/module/video/img/layout-{index}.png);"></div>
			<div class="layout-desc">{number}画面</div>
		</div>
	</div>
</div>

<script src="${ctx}/static/system/videoClient.js"></script>
<script src="${ctx}/static/module/dist/js/flexDate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/hz.mask.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/playbackVideo.js"></script>
<script type="text/javascript"
	src="${ctx}/static/module/video/js/rightVideoPlan.js"></script>
<script>
//面板第一次触发时置为true，同时加载树，此后不在重复加载
var accordion_0=false;
var accordion_1=false;
var accordion_2=false;

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID
$(".mscroll").mCustomScrollbar({
	theme:"minimal-dark",
	autoExpandScrollbar:true,
	scrollInertia:0,
	mouseWheelPixels:130//鼠标滚动一下滑动多少像素 
}); 
$.parseDone(function(){
	//初始化树
	if(jsConst.USER_LEVEL==2){
		$("#dddsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
		$("#ddtsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);	
		$("#ddbtsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
	}else if(jsConst.USER_LEVEL==3){
		$("#dddsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
		$("#ddtsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);	
		$("#ddbtsdTree").tree("option","asyncUrl","${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=0&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
	}
	//回放时间默认值 (设置默认值后，选择日期相同的情况下会出现初始时间不能选择，暂不设置时间默认值)
/*    var nowDate = new Date(new Date().setHours(0, 0, 0, 0));
    $('#startTime0').datepicker("setDate", nowDate);
    $('#startDate').datepicker("setDate", nowDate);
	nowDate.setDate(nowDate.getDate() + 1);
    $('#endTime0').datepicker("setDate", nowDate);
    $('#endDate').datepicker("setDate", nowDate);*/
	//加载第一个面板
	$("#dddsdTree").tree("reload");
	$("#dddsdTree").tree("option","onLoad",function(){
		$.parser.parse();
	});
	initLayout();
})
//切换面板
function sphf_onActivate(){
	deskLayout = null;
	index_2 = 0;
	reViewMode =$("#accordion_sphf").accordion("option","active");
	$(".layout_table").hide();
	$(".layout_table table").empty();
	var next=null;
	if(reViewMode==0){//单点多时段
		if(!accordion_0){
			$("#dddsdTree").tree("reload");
			accordion_0=true;
		}	
		
		next=$("#active_0");
		next.find(".tabPlayback_box").append($("#tabPlayback"));
		next.find(".layout_table").show();
	}
	else if(reViewMode==1){//多点同时段
		if(!accordion_1){
			$("#ddtsdTree").tree("reload");
			accordion_1=true;
		}	
		
		next=$("#active_1");
		next.find(".tabPlayback_box").append($("#tabPlayback"));
	}else if(reViewMode==2){//多点不同时段
		if(!accordion_2){
			$("#ddtsdTree").tree("reload");
			accordion_2=true;
		}
		
		$("#ddbtsdTree").tree("reload");
		next=$("#active_2");
		next.find(".layout_table").show();
	}
}
</script>