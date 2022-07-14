<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<input type="hidden" id="deviceType" value="1" />
<input type="hidden" id="deviceStatus" value="0" />
<input type="hidden" id="url" />
<input type="hidden" id="deviceName" />
<style>
	.rightTable {
		text-align: center;
		font-size: 13px;
		border-collapse: collapse;
		border: solid #4592f0;
		border-width: 1px;
		margin-top: 10px;
		
	}
	 .rightTable td {
		border: solid #4592f0;
		border-width: 1px;
		height: 30px;
		text-align:center;
		
		
	} 
	.rightTable td .sone {
	    color:#f08300
		border: solid #4592f0;
		border-width: 1px;
		height: 30px;
		text-align:center;
		width:90px;
	}
	</style>
	
<div id="rightSideHome_jfsb" class="scorllBar">
	<div class="rightDivStyle right-zb" id="technicalDeviceCount">
		<h4 align="center">监内技防设备统计</h4>
			<h5 align="right"><a href="javascript:void(0);" id="callBack" onclick="callBack();">返回>></a></h5>
		<table class="rightTable" align="center">
			
		</table>
	</div>
	
	<div class="rightDivStyle right-zb" id="deviceInfo">
		<h4 align="center"></h4>
		<h5 align="right"><a href="javascript:void(0);" onClick="toDeviceList();">更多>></a></h5>
			<div id="deviceInfo_table" style="height: 520px;">
				<table class="rightTable1">
				</table>
				<div class="notData" style="width: 284px;" align="center">暂无数据!</div>
			</div>
	</div>
</div>

<cui:dialog id="dialogId_technicalDevice" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" width="1000" height="500" maximizable="false"></cui:dialog>

<script type="text/javascript">

	var USER_LEVEL =  jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER;  
	var prisonId = '${prisonId}';          //省局传来监狱编号
	var prisonName = '${prisonName}';      //省局传来监狱名称
	
	$.parseDone(function() {
		
		$("#deviceInfo_table").mCustomScrollbar({//滚动条初始化
			theme:"minimal-light",
			autoExpandScrollbar:true
		});
		
		if(prisonName != null && USER_LEVEL == 1) {
			$("#callBack").show();
			cusNumber = prisonId ;
			$("#technicalDeviceCount h4").html(prisonName+'技防设备统计')
		} else if (USER_LEVEL != 1) {
			cusNumber = thisCusNumber;
			$("#callBack").hide();
			$("#technicalDeviceCount h4").show();
		}
		queryTechnicalDeviceCount();
	});
	
	/**
	 * 返回全疆监狱列表模块
	 */
	 function callBack() {
	    	
    	var panel = $("#layout1").layout("panel", "east");
    	panel.panel("refresh", "${ctx}/xxhj/jfsb/pjfsb");
	 }
	
	/**
	 * 查询技防设备数量总信息
	 */
	function queryTechnicalDeviceCount() {
 
		var url = "${ctx}/xxhj/jfsb/listDeviceMasterInfo?cusNumber=" + cusNumber + "&typeIndc=1";
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data) {
					loadTechnicalDeviceCount(data);
				}
			}
		});
	}
	
	/**
	 * 加载技防设备统计信息
	 */
 	function loadTechnicalDeviceCount(data) {
		
		var table = $("#technicalDeviceCount table");
		var tr = $("<tr id='title'></tr>");
		tr.append('<td width="150" class="one">设备名称</td>');
		tr.append('<td width="100" class="one">单位</td>');
		tr.append('<td width="100" class="one">总数</td>');
		tr.append('<td width="100" class="one">故障</td>');
		table.append(tr);
		
		var unit = "";
		for (var i = 0; i < data.length; i++) {
			 
			var tr = $("<tr></tr>");
 			tr.append("<td>"+data[i].DMA_DEVICE_NAME+"</td>");
 			
			if (data[i].DMA_DEVICE_IDNTY == 1) {
					unit = "个";
				} else if (data[i].DMA_DEVICE_IDNTY == 2) {
					unit = "个";
				} else if (data[i].DMA_DEVICE_IDNTY == 3) { 
					unit = "对";
				} else if (data[i].DMA_DEVICE_IDNTY == 4) {
					unit = "段";
				} else if (data[i].DMA_DEVICE_IDNTY == 5) {
					unit = "个";
				} else if (data[i].DMA_DEVICE_IDNTY == 6) {
					unit = "点";
				} else if (data[i].DMA_DEVICE_IDNTY == 7) {
					unit = "区";
				}
			tr.append("<td>"+unit+"</td>");
			
			if (data[i].DMA_TOTAL_COUNT != 0) {
				
				tr.append("<td><a href='javascript:void(0);' onclick='methodControler("+ data[i].DMA_DEVICE_IDNTY
						+ ",0,this);' id='one_"+data[i].DMA_DEVICE_IDNTY+"'>"+data[i].DMA_TOTAL_COUNT+"</a></td>");
			} else {
				tr.append("<td >"+data[i].DMA_TOTAL_COUNT+"</td>");
			}
			
			if (data[i].DMA_ABNORMAL_COUNT != 0) {
				
				tr.append("<td ><a href='javascript:void(0);'onclick='methodControler("
						+ data[i].DMA_DEVICE_IDNTY+ ",1,this);' id='two_"+ data[i].DMA_DEVICE_IDNTY+ "'>"
						+data[i].DMA_ABNORMAL_COUNT+"</a></td>");

			} else {
				tr.append("<td >"+data[i].DMA_ABNORMAL_COUNT+"</td>");
			}
			table.append(tr);
		} 
			$("#one_1").click();    
	} 
	
	/**
	 *  deviceType 是设备编号   deviceStatus0是正常 1是故障的
	 */
	function methodControler(deviceType, deviceStatus, aId) {

		var obj = $(aId);
		$("#deviceType").val(deviceType);
		$("#deviceStatus").val(deviceStatus);
		var deviceName = obj.parent().parent().children().eq(0).html(); 

		if(deviceStatus == 0) {
				
			$("#deviceInfo h4").html(deviceName + "设备列表");
		} else if(deviceStatus == 1) {
			
			$("#deviceInfo h4").html(deviceName + "设备故障列表");
			}
		queryPartDeviceList(deviceType, deviceStatus);
	}
	/**
	 * 查询技防设备信息
	 */
	function queryPartDeviceList(deviceType, deviceStatus) {
		var url;
		deviceStatus = deviceStatus == 0 ? "" : deviceStatus;

	   /* 摄像头的状态有多种状态 所以查询故障的就是  状态不等于0       其他设备故障时 是状态等于1    查询总数时就是无状态限制*/
		if(deviceType == 1 && deviceStatus == 1) {
			
			var sttsIndc = 0;
		} else {
			
			sttsIndc= deviceStatus;
		}
        //右边栏显示20条数据
		var url = "${ctx}/xxhj/jfsb/listOnePrisonDevicePart?cusNumber="+cusNumber+ "&sttsIndc="+ sttsIndc+"&type="+deviceType;
		var deviceName=	$("#deviceName").val();
		var table = $("#deviceInfo table");
		table.empty();
		
		if(url == null) {
			
			$(".notData").show();
		} else {
			
			$(".notData").hide();
		}
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {

				if (data) {
					
					loadDeviceInfo(deviceType, data, table);
				}
			}
		});
		
		queryDeviceInfo(cusNumber,sttsIndc,deviceType);
	}

	//加载 分页查询设备列表参数
	function  queryDeviceInfo(cusNumber,sttsIndc,deviceType ) {
	
		var list = new Array();
		switch(deviceType) {
		
		case 1:
			list.push('listOnePrisonCameraInfo'); 	
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("摄像机");
			break;
		case 2:
			list.push('listOnePrisonAlertorInfo');
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("网络报警");
			break;
		case 3:
			list.push('listOnePrisonPowerNetworkInfo');
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("数字电网");
			break;
		case 4:
			list.push('listOnePrisonSnakeInfo');
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("周界震动报警");
			break;
		case 5:
			list.push('listOnePrisonInfraredInfo');
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("周界红外报警");
			break;
		case 6:
			list.push('listOnePrisonDoorInfo');
			
			$("#url").val("${ctx}/xxhj/jfsb/technicalDeviceList?cusNumber="
					+cusNumber+ "&sttsIndc=" + sttsIndc+ "&sql=" + list[0]);
			
			$("#deviceName").val("门禁");
			break;
		};
	}
	
	/**
	 * 加载技防设备信息
	 */
	function loadDeviceInfo(deviceType, data, table) {
		
		if (data == null || data == "") {
			$(".notData").show();
		
		} else {
			$(".notData").hide();
		}
		$("#prisonList").hide();
		
		for(var i = 0; i < data.length; i++) {
			
			tr = $("<tr></tr>");
			tr.append("<td style='padding-left:50px;padding-top:20px;'><a href='#' onclick='toTechnicalDeviceInfo("+ deviceType+ ",\""+ data[i].DEVICEID+ "\")' >"
							+ data[i].DEVICENAME + "</a></td>");
			tr.append("<input type='hidden' class='hid' value='" + data[i].DEVICENAME + "'/>");
			table.append(tr);
		}
	}
	
	/**
	 * 弹出技防设备信息详情页面
	 */
	function toTechnicalDeviceInfo(deviceType, deviceId) {
		
		var titleName = null;
		switch(deviceType) {
			case 1:
				titleName = "摄像机信息详情";
				break;
			case 2:
				titleName = "报警器信息详情";
				break;
			case 3:
				titleName = "电网信息详情";
				break;
			case 4:
				titleName = "蛇腹网详情";
				break;
			case 5:
				titleName = "红外报警信息详情";
				break;
			case 6:
				titleName = "门禁信息详情";
				break;
		};

		$("#dialogId_technicalDevice").dialog("option", {
			width : 1000,
			height : 600,
			title : titleName,
			url : "${ctx}/xxhj/jfsb/technicalDeviceInfo?cusNumber="
					+ cusNumber+ "&deviceType="+ deviceType+ "&deviceId=" + deviceId,
		});
		$("#dialogId_technicalDevice").dialog("open");
	}
	/**
	 * 弹出所有设备列表页面 分页查询
	 */
	function toDeviceList() {

		var url = $("#url").val();
		var deviceName = $("#deviceName").val();

		$("#dialogId_technicalDevice").dialog({
			width : 1000, 
			height : 800, 	
			title : deviceName,
			url : url
		});
		$("#dialogId_technicalDevice").dialog("open");
	}
</script>
