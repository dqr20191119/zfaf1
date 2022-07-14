<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script src="${ctx}/static/js/alarm/alarm.js"></script>


<!-- coral4 css start  -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/cui/cui.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/dialog.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/moxing.css" />
<!-- coral4 css  end  -->

<!-- app css define start -->
<link href="${ctx}/static/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/resource/style/css/prettify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/font/iconfont.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/extraFont/iconfont.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/css/rightSide.css" />
<link rel="stylesheet" href="${ctx}/static/css/talkbackControl.css" />
<!-- app css define end -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/style.css" /> 
<style>
	#formId_record_handle_panel .coral-textbox-default {
	 	color: #fff!important;
	}
</style>

<cui:form id="formId_record_handle_panel">
	<table class="table">
		<tr>
			<!-- 应急预案 -->
			<th>预案名称：</th>
			<td>
				<cui:input id="preplanName" name="preplanName" value="${emerHandleRecord.preplanName}" readonly="true"></cui:input>
			</td>
		</tr>
		<tr>
			<!-- 应急预案 -->
			<th>预案来源：</th>
			<td>
				<cui:combobox id="preplanSource" name="preplanSource" data="preplanSourceData" value="${emerHandleRecord.preplanSource}" readonly="true"></cui:combobox>
			</td>
		</tr>
		<tr>
			<!-- 报警预案 -->
			<th>报警设备：</th>
			<td>
				<cui:input id="alarmPlanName" name="alarmPlanName" value="${emerHandleRecord.alarmPlanName}" readonly="true"></cui:input>
				<cui:input id="alarmPlanId" name="alarmPlanId" value="${emerHandleRecord.alarmPlanId}" type="hidden"></cui:input>
			</td>
		</tr>
		<tr>
			<th>报警地点：</th>
			<td>
				<cui:input id="alarmAreaName" name="alarmAreaName" value="${emerHandleRecord.alarmAreaName}" readonly="true"></cui:input>
				<cui:input id="alarmAreaId" name="alarmAreaId" value="${emerHandleRecord.alarmAreaId}" type="hidden"></cui:input>
			</td>
		</tr>
		<tr>
			<th>发生时间：</th>
			<td>
				<cui:datepicker id="alarmTime" name="alarmTime" dateFormat="yyyy-MM-dd HH:mm:ss" value="" readonly="true"></cui:datepicker>
			</td>
		</tr>
		<tr>
			<th>处置时间：</th>
			<td>
				<cui:datepicker id="startTime" name="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" value="${emerHandleRecord.startTime}" readonly="true"></cui:datepicker>
			</td>
		</tr>
	</table>
</cui:form>

<!-- 关联设备显示区域 -->
<div class="rightDivStyle right-zb" id="device_show" style="display: none; height: auto;">
	<div id="divId_device" class="picfrm"></div>
</div>

<!-- 隐藏域 -->
<div id="divId_handle_panel_hidden" style="display: none;">
	<!-- 监狱/单位编号 -->
	<input id="cusNumber" type="hidden" value="${emerHandleRecord.cusNumber}" />
	<!-- 监狱/单位名称 -->
	<input id="cusName" type="hidden" value="${emerHandleRecord.cusName}" />
	<!-- 应急预案编号 -->
	<input id="preplanId" type="hidden" value="${emerHandleRecord.preplanId}" />
	<!-- 报警记录编号 -->
	<input id="alarmRecordId" type="hidden" value="${emerHandleRecord.alarmRecordId}" />
	<!-- 应急处置记录编号 -->
	<input id="emerHandleRecordId" type="hidden" value="${emerHandleRecord.id}" />
	<!-- 应急处置的处置类型【handleType：1-事件处置，2-事件演练，3-报警处置，4-处置记录处置(现在使用中的只有3和4，1和2类型已停用)】 -->
	<input id="handleType" type="hidden" value="${handleType}" />
</div>

<!-- 应急处置 对话框 -->
<cui:dialog id="dialogId_handle_process" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="false" autoDestroy="true">
</cui:dialog>

<script>
	// 视频客户端调用JS
	var videoClient = window.top.videoClient;
	// 常量
	var jsConst = window.top.jsConst;
	// 是否3D模式
	var is3D = jsConst.MAP_TYPE == "0" ? false : true;
	// 预案数据来源
	var preplanSourceData = [
		{value:"0", text:"用户定义"},
		{value:"1", text:"系统同步"}
	];

	$.parseDone(function() {
		// 页面信息初始化
		initPageInfo();
	});

	/**
	 * 页面信息初始化
	 */
	function initPageInfo() {
		// handleType：1-事件处置，2-事件演练，3-报警处置，4-处置记录处置(现在使用中的只有3和4，1和2类型已停用)
		var handleType = $("div[id='divId_handle_panel_hidden']").find("input[id='handleType']").val();

		// 加载报警记录信息
		loadAlarmRecord();

		// 加载报警图层
		if (handleType == "4") {
			loadAlarmMap();
		}
		// 打开应急处置记录处理流程窗口
		openNewStartPreplan();
	}

    /**
	 * 初始化报警记录信息
	 */
	function loadAlarmRecord() {
		// 报警记录编号
		var alarmRecordId = $("div[id='divId_handle_panel_hidden']").find("input[id='alarmRecordId']").val();

		var url = "${ctx}/alarm/findById.json";
		var params = {};
		if(alarmRecordId) {
			params["id"] = alarmRecordId;
		}

		var callBack = function(data) {
			if (data.success) {
				// 填充报警记录信息
				$("#formId_record_handle_panel").find("#alarmTime").datepicker("setValue", data.obj.ARD_ALERT_TIME);
				// 报警信息数据
				var cusNumber = data.obj.ARD_CUS_NUMBER;
				var alertorId = data.obj.ARD_ALERTOR_IDNTY;
				var alarmPlanId = $("#formId_record_handle_panel").find("#alarmPlanId").textbox("getValue");
                loadAlarmPlan(cusNumber, alertorId, alarmPlanId);
			} else {
				$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
			}
		};
		ajaxTodo(url, params, callBack);
	}

	/**
	 * 加载报警图层
	 */
	function loadAlarmMap() {
		// 报警的单位/监狱编号
		var alarmCusNumber = $("div[id='divId_handle_panel_hidden']").find("input[id='cusNumber']").val();
		// 报警的单位/监狱名称
		var alarmCusName = $("div[id='divId_handle_panel_hidden']").find("input[id='cusName']").val();
		// 报警地点编号
		var alarmAreaId = $("#formId_record_handle_panel").find("#alarmAreaId").textbox("getValue");
		// 报警地点名称
		var alarmAreaName = $("#formId_record_handle_panel").find("#alarmAreaName").textbox("getValue");

		// 2D\3D维定位
		if(is3D) {
			// 区域 垂直视角
			map.showHideByAreaId('', alarmAreaId, 1);
			// 显示当前区域以及其子节点的设备
			map.getAllPointByGrandAndType(jsConst.ORG_CODE, alarmAreaId, '', 1);
			// 定位垂直视角
			map.setAdminCameraByAreaId(alarmAreaId, jsConst.ORG_CODE);
		} else {
			try {
				// 判断planeViewPosition函数是否存在
				if(typeof (eval("planeViewPosition")) == "function") {
					// 2维地图定位
					planeViewPosition(alarmAreaId, alarmAreaName);
				}
			} catch (e) {
				// 监狱二维图层定位
				map.loadMapInfo(alarmCusNumber, alarmCusName);
				// 2维地图定位
				setTimeout(function () {
					planeViewPosition(alarmAreaId, alarmAreaName);
				}, 1000);
			}
		}
	}

	/**
	 * 打开新应急处置对话框
	 * @param emerHandleRecord
	 */
	function openNewStartPreplan() {
		// 应急处置记录编号
		var emerHandleRecordId = $("div[id='divId_handle_panel_hidden']").find("input[id='emerHandleRecordId']").val();
		// 应急预案编号
		var preplanId = $("div[id='divId_handle_panel_hidden']").find("input[id='preplanId']").val();
		// 报警记录编号
		var alarmRecordId = $("div[id='divId_handle_panel_hidden']").find("input[id='alarmRecordId']").val();

		var url = "${ctx}/emergency/handle/recordManage/handleStep?emerHandleRecordId=" + emerHandleRecordId + "&preplanId=" + preplanId + "&alarmRecordId=" + alarmRecordId;
		$("#dialogId_handle_process").dialog({
			width: 1065,
			height: 600,
			title: "应急处置流程",
			url: url,
			buttons: [{
				text: "上一步",
				id: "btnId_prevStep",
				componentCls:"btn-primary",
				width: 70,
				click: function () {
					prevStep();
				}
			}, {
				text: "下一步",
				id: "btnId_nextStep",
				componentCls:"btn-primary",
				width: 70,
				click: function () {
					nextStep();
				}
			}, {
				text: "完成",
				id: "btnId_finished",
				componentCls:"btn-primary",
				width: 70,
				click: function () {
					finished();
				}
			}],
			onOpen: function() {//回调事件
				// 工具栏按钮的显示与隐藏
				$("#btnId_prevStep").button({disabled: true});
				$("#btnId_finished").button("hide");
				$("#btnId_nextStep").button("show");
			},
			onClose: function() {//回调事件
				// 关闭处置窗口
				if(handleProcessSi) {
					clearInterval(handleProcessSi);
				}
			}
		});
		$("#dialogId_handle_process").dialog("open");
	}

	/**
	 * 结束应急处置记录
	 * @param id
	 */
	function doAjaxEmerHandleRecordToFinish() {
		// 应急处置记录编号
		var recordId = $("#formId_preplan_view").find("input[id='emerHandleRecordId']").val();
		var params = {};
		if(recordId) {
			params["id"] = recordId;
		}
		params["recordStatus"] = "2";

		// 应急处置记录完成
		var url = "${ctx}/emergency/handle/recordManage/updateRecordStatus.json";
		var callBack = function(data) {
			if(data.success) {
				console.log("====== emergency handle record to finish successfully ======");
			}
		};
		ajaxTodo(url, params, callBack);
	}
</script>