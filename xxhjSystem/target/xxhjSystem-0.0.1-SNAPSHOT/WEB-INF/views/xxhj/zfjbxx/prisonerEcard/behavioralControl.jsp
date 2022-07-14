<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
/*foot样式*/
.foot_left{
	float:left;
	width:450px;
	height:550px;
}
.foot_left table{
	float: left;
	text-align: center;
}
.foot_left .prisonerFlow{
	float:left;
	width:450px;
	height:260px;
}

.foot_center{
	float:left;
	width:150px;
	height:540px;
	margin-top:40px;
	margin-left:30px;
	font-size: 15px;
}
.foot_center .photo{
	float:left;
	width:140px;
	height:540px;
}
.foot_center .photo ul{
	float:left;
	line-height:35px;
}
.foot_center .inputTime{
	margin-top: 10px;
}

.foot_right{
	float:right;
	width:380px;
	height:540px;
	font-size: 15px;
}
.foot_right .timeLine{
	float:left;
	height: 100%;
	margin-left:25px;
	margin-top:-8px;
}
.foot_right .time_thread{
	float:left;
	width:3px;
	height: 100%;
	background:url(../../../../themes/default/img/long.png);
	margin-left:10px;
	z-index: 100;
}
.foot_right .timeTrackBox{
	width: 385px;
	height: 540px;
	overflow-y:auto;
}
.foot_right .shadow{
	float:left;
	width:300px;
	height:100%;
	color:#FFF;
	font-size:18px;
	margin-left: 10px;
}
.foot_right .shadow .typeBox{
	float:left;
}
.foot_right .shadow .typeBox .flow{
	float:left;
	width:278px;
	border: 1px solid #1465c1;
	border-radius:5px;
	padding-top: 10px;
	padding-bottom: 10px;
}
.foot_right .shadow .typeBox .call{
	float:left;
	width:278px;
	padding-top: 1px;
	padding-bottom: 1px;
}
.foot_right .shadow .typeBox .img_flow{
	float:left;
	width:36px;
	height:20px;
	margin-left: -20px;
	background:url(../css/img/arrows-blue.png) left center no-repeat;
}
.foot_right .shadow .typeBox .img_call{
	float:left;
	width:36px;
	height:20px;
	margin-left: -20px;
	background:url(../css/img/arrows-green.png) left center no-repeat;
}
.foot_right .shadow .typeBox .one_flow{
	height: 20px;
	float: left;
	margin: 4px 0px 0px 5px;
	font-size: 14px;
	background:url(../../../../themes/default/img/time.png) no-repeat;
}
.foot_right .shadow .typeBox .one_flow .text{
	margin-left: 22px;
	float: left;
	color: #000;
}
.foot_right .shadow .typeBox .one_call{
	height: 20px;
	float: left;
	margin: 4px 0px 0px 5px;
	font-size: 13px;
	background:url(../../../../themes/default/img/time.png) no-repeat;
}
.foot_right .shadow .typeBox .one_call .text{
	margin-left: 22px;
	float: left;
	color: #000;
}
.foot_right .shadow .typeBox .two_flow{
	width: 175px;
	float: left;
	margin: 2px 0px 0px 15px;
	font-size: 16px;
	color: #1465c1;
	background:url(../../../../themes/default/img/position.png) no-repeat;
}
.foot_right .shadow .typeBox .two_flow .text{
	margin: 0px 0px 2px 25px;
	float: left;
}
.foot_right .shadow .typeBox .two_call{
	width: 175px;
	float: left;
	margin: 2px 0px 0px 15px;
	font-size: 14px;
	color: rgb(0,166,105);
}
.imgOne{
	width: 125px;
	height: 130px;
 	border-width: 1px 1px 1px 1px; 
}
.arrows_top{
	width: 24px;
	height: 25px;
	background:url(../css/img/arrows-grey-top-2.png) center no-repeat;
	position: absolute;
}
</style>
<div class="foot_left">
	<div class="prisonerFlow">
		<div class="common_content_foot_smallTitle">
			活动轨迹
			<p><img src="<%=basePath %>themes/default/img/blueThread.png" width="450" height="1"></p>
		</div>
		<table id="table"></table>
	</div>
</div>
<!-- center布局-->
<div class="foot_center">
	<div class="photo">
		<div id="pcmPrsnrImg">
			
		</div>
		<div class="inputTime">
			<input id="easyui_dateTime" type="text" class="hz-datebox" size="17" />
		</div>
	</div>
</div>
<!-- left布局-->
<div class="foot_right">
	<div class="common_content_foot_smallTitle">
		活动轨迹时间轴
		<p><img  width="385" height="1"></p>
	</div>
	<div class="notData">暂无数据!</div>
	<div class="timeTrackBox">
		<div class="timeLine">
			<div class="arrows_top"></div>
			<div class="time_thread"></div>
		</div>
		<div class="shadow">
			<div id="timeTrack"></div>
		</div>
	</div>
</div>
<script type="text/javascript">

$$.parseDone(function() {
	
	//获取ecard主页隐藏表单的罪犯信息
	var USER_LEVEL =jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER; 
	
	var prisonerId = $("#prisonerId").val();
	var args = '{"zfbh":' + prisonerId + '}';
	//var fontImgUrl = "<%=basePath%>" + "prisoner/getPrisonerImg?args=" + args;
	$("#pcmPrsnrImg").append("<img class='imgOne' src='" + fontImgUrl + "' onerror='this.src=\"<%=basePath %>common/img/noImg.jpg\"'>");
	var timeThread = $(".time_thread");
	var timeTrack = $("#timeTrack");
	//加载时间控件、根据时间加载相应的数据
	$("#easyui_dateTime").datebox({
		
		onSelect : function() {
			
			var time = $("#easyui_dateTime").datebox("getValue");
			queryConvictFluxion(cusNumber,prisonerId,time);
			timeThread.css("height",0);
			timeTrack.empty();
			$(".timeLine").hide();
			$(".notData").html("加载中...");
			$(".notData").show();
			queryConvictFluxion_time(cusNumber,prisonerId,time);
		}
	});
	//获取设置当前时间
	var time = formatterDate(new Date());
	$("#easyui_dateTime").datebox("setValue", time);
	//查询当前罪犯零星流动数据
	queryConvictFluxion(cusNumber,prisonerId,time);
	timeThread.css("height",0);
	timeTrack.empty();
	$(".timeLine").hide();
	$(".notData").html("加载中...");
	$(".notData").show();
	queryConvictFluxion_time(cusNumber,prisonerId,time);
});
	
/**
 * 查询当前罪犯零星流动信息
 */
function queryConvictFluxion(cusNumber,prisonerId,time) {
	var list = new Array();
	list.push(cusNumber);
	list.push(prisonerId);
	list.push(time);
    list.push(time);
	var args = {
		 sqlid : "cds_prisoner_flow_list",
		 whereId : '0',
		 orderId : '0',
		 parasList : list
	};
	loadPrisonerFlow(args);
}
/**
 * 查询当前罪犯零星流动信息
 */
function queryConvictFluxion_time(cusNumber,prisonerId,time) {
	var list = new Array();
	list.push(cusNumber);
	list.push(prisonerId);
	list.push(time);
    list.push(time);
	list.push(cusNumber);
	list.push(prisonerId);
	list.push(time);
    list.push(time);
	var options = {
		 dataType : 1,
		 sync : true,
		 sqlId : "cds_prisoner_flow_time",
		 oid : "0",
		 data : list,
		 success : function(json){
			 if(json.success){
				 var flowData = json.obj.data;
				 loadTimeFlowTrack(flowData);
			 }
		 }
	}
	queryCtrl(options);
}
/**
 * 加载罪犯活动轨迹信息
 */
function loadPrisonerFlow(args){
	
	$(".prisonerFlow #table").datagrid({
		title:'',
		width:450,
		height:555,
		nowrap: false,
		striped: true,
		collapsible:true,
		singleSelect:true,
		url: "<%=basePath %>dataGrid/excute?args="+JSON.stringify(args),
		columns:[[
			{field:'PFD_CRTE_TIME',title:'时间',align:'center',width:140,sortable:true},
			{field:'ENTERAREA',title:'描述',align:'center',width:277,sortable:true,
				formatter:function(value,rowData,rowIndex){
					var desc = "";
					if(rowData.LEAVEAREA == null && rowData.ENTERAREA != null){
						desc = "进入" + rowData.ENTERAREA;
					}else if(rowData.ENTERAREA == null && rowData.LEAVEAREA != null){
						desc = "离开" + rowData.LEAVEAREA;
					}else if(rowData.ENTERAREA == null && rowData.LEAVEAREA == null){
						desc = "";
					}else{
						desc = "离开" + rowData.LEAVEAREA + "进入" + rowData.ENTERAREA;
					}
					return window.top.ellipsis(desc);
				}
			}
		]],
		pagination:true,
		pageNumber:1,
		pageSize:10,
		pageList:[10,20,30,40,50],
		rownumbers:true
	});
}
/**
 * 加载罪犯活动轨迹时间轴
 */
function loadTimeFlowTrack(data) {
	$(".typeBox").remove();
	//$(".time_styleTwo").remove();
	$(".timeTrackBox .notData").hide();
	var timeThread = $(".time_thread");
	timeThread.empty();
	if(data.length == 0){
		$(".foot_right .notData").show();
		$(".notData").html("暂无数据!");
		return;
	}else{
		$(".foot_right .notData").hide();
	}
	var timeTrack = $("#timeTrack");
	var p,div,divImg,divType,divOne,divTwo;
	var height = 0;
	for (var i=0;i<data.length;i++) {
			var divTop = 0;
			if (i == 0) {	
				divTop = 30;
				height += data[i].TYPE == "flow" ?  85.12 : 64;
			} else {
				if(i == data.length-1){
					height += data[i].TYPE == "flow" ? 62.12 : 41;	
				}else{
					height += data[i].TYPE == "flow" ? 62.12 : 41;	
				}
				divTop = 15;	
			}
			var imgTop = data[i].TYPE == "flow" ? 14 : 4;	
			
			div = $("<div class='typeBox' style='margin-top: " + divTop + "px;'></div>");
			
			divImg = ("<div class='img_" + data[i].TYPE  + "' style='margin-top: " + imgTop + "px;'></div>");
			
			divType = $("<div class='" + data[i].TYPE + "'></div>");
			
			divOne = $("<div class='one_" + data[i].TYPE + "'></div>");
			divOne.append("<div class='text'>" + getValue(dateTruncate(data[i].DATE,"hms")) + "</div>");
			
			divTwo = $("<div class='two_" + data[i].TYPE + "'></div>");
			var context = data[i].TYPE == "flow" ? data[i].ENTERAREA || data[i].LEAVEAREA : "工间点名";
			divTwo.append("<div class='text'>" + getValue(context) + "</div>");
			
			divType.append(divOne);
			divType.append(divTwo);
			div.append(divImg);
			div.append(divType);
			
			timeTrack.append(div);
	}
	timeThread.css("height",height);
	$(".timeLine").show();
}

/**
 * 格式化当前时间
 */
function formatterDate(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
			+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
}
</script>