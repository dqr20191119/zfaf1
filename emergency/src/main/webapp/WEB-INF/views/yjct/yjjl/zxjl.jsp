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
	#formId_yjjl_edit .coral-textbox-default {
	 	color: #fff!important;
	}
</style>

<cui:form id="formId_yjjl_edit">
	<cui:input id="inputId_yjjl_id" type='hidden' name="id" />
	<table class="table">	 
		<tr>
			<th>???????????????</th>
			<td><cui:combobox id="comboboxId_yjjl_planType" name="epiPlanType" data="combobox_yjct_planType" onChange="loadPlanInfo" required="true"></cui:combobox></td>
		</tr>
		<tr>
			<th>???????????????</th>
			<td><cui:combobox id="comboboxId_yjjl_planName" name="ehrEmPlanFid" required="true"></cui:combobox></td>
		</tr>
		<tr>
			<th>???????????????</th>
			<td><cui:combobox id="comboboxId_yjjl_AlarmPlan" name="ehrAlarmPlanFid" url="${ctx}/common/all/findMasterPlanForCombobox" required="true"></cui:combobox></td>
		</tr>
		<tr>
			<th>???????????????</th>
			<td><cui:combotree id="comboboxId_yjjl_Address" name="ehrAddressFid" url="${ctx}/common/all/findSyncAreaForCombotree" 
					simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pId" keyName="name" required="true"></cui:combotree></td>
		</tr>
		<tr>
			<th>???????????????</th>
			<td><cui:datepicker name="ehrTime" dateFormat="yyyy-MM-dd HH:mm:ss" value="${ehrTime}" required="true"></cui:datepicker></td>				
		</tr>	
	</table>
	<div style="text-align: center;">
		<cui:button id="buttonId_yjjl_saveRecord" componentCls="btn-primary" label="??????" onClick="saveOrUpdate"></cui:button>
	</div>
</cui:form>

<div style="text-align: center; border-top: 3px solid #ebebeb;">
	<b style="font-size: 18px;">??????????????????</b>
	<center>
		<table style="width: 100%;">
			<tr><td style="text-align: center; height: 35px; font-size: 13px;"><a id="btnWorkgroup" onclick="openPlandesc(2)">?????????</a></td></tr>
			<tr><td style="text-align: center; height: 35px; font-size: 13px;"><a id="btnDisProcess" onclick="openPlandesc(3)">????????????</a></td></tr>
			<tr><td style="text-align: center; height: 35px; font-size: 13px;"><a id="btnEnventReport" onclick="openEventReport()">????????????</a></td></tr>
		</table>
	</center>
</div>
	
<div class="rightDivStyle right-zb" id="device_show" style="display: none; height: auto;">
	<div id="divId_device" class="picfrm"></div>
</div>

<cui:dialog id="dialogId_yjjl_zxjl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="false" autoDestroy="true">
</cui:dialog>
	
<script>
	
	var combobox_yjct_planType = <%=CodeFacade.loadCode2Json("4.20.4")%>;
	var combobox_yjct_actionType = <%=CodeFacade.loadCode2Json("4.20.5")%>;
	var combobox_yjct_sex = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	
	var videoClient = window.top.videoClient;
	var jsConst = window.top.jsConst;
	var is3D = jsConst.MAP_TYPE == "0" ? false : true;
	var opType = "";
	var nextPos = 0;
	
	$.parseDone(function() {
		
		opType = '${type}';		// 1-???????????????2-???????????????3-???????????????4-??????????????????
		console.log("opType = " + opType);
		if(opType == "3") {
			
			// ?????????????????????
			var ehrAlarmPlanFid = '${ehrAlarmPlanFid}';
			var ehrAddressFid = '${ehrAddressFid}';
			
			$("#comboboxId_yjjl_AlarmPlan").combobox("setValue", ehrAlarmPlanFid);
			$("#comboboxId_yjjl_Address").combotree("setValue", ehrAddressFid);			
			$("#comboboxId_yjjl_AlarmPlan").combobox("option", "readonly", true);
			$("#comboboxId_yjjl_Address").combotree("option", "readonly", true);
		} else if(opType == "4") {
			console.log("is3D = " + is3D);
			// ???????????????-??????
			loadForm("formId_yjjl_edit", "${ctx}/yjct/yjjl/getById?id=" + '${recordId}', function(data) {
				
				$("#formId_yjjl_edit").form("setReadOnly", true);
				$("#comboboxId_yjjl_planName").combobox("setText", data.epiPlanName);
				$("#buttonId_yjjl_saveRecord").hide();
				
				// ??????????????????
				openPlandesc(3);
				
				// 2\3?????????
				if(is3D) {
					// ?????? ????????????
					map.showHideByAreaId('', data.ehrAddressFid, 1);
					// ?????????????????????????????????????????????
					map.getAllPointByGrandAndType(jsConst.ORG_CODE, data.ehrAddressFid, '', 1);
					// ??????????????????
			        map.setAdminCameraByAreaId(data.ehrAddressFid, jsConst.ORG_CODE);
				} else {
					// 2???????????????	
					var areaName = $("#comboboxId_yjjl_Address").combotree("getText");
					planeViewPosition(data.ehrAddressFid, areaName);
				}
				
				// ?????????????????????????????????
				loadAlarmPlan(jsConst.ORG_CODE, null, data.ehrAlarmPlanFid);
			});
		} else if(opType == "1") {
			
			// ????????????????????????
		}
	});
	
	function loadPlanInfo(event, ui) {

		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yabz/searchAllData',
			data: {
				epiPlanType: ui.newValue,
				epiPlanStatus: 4
			},
			dataType : 'json',
			success : function(data) {	 
 				$("#comboboxId_yjjl_planName").combobox("reload", data); 	
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.message({message:"?????????????????????", cls:"error"});
			}
		});
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_yjjl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_yjjl_edit").form("formData");
		formData.ehrAddress = $("#comboboxId_yjjl_Address").combotree("getValue");
		formData.ehrStatus = "1";
		formData.ehrAlarmRecordId = '${alarmRecordId}';
		if(opType == "2") {
			formData.ehrType = "2";
		} else {
			formData.ehrType = "1";
		}
		
		$.loading({text:"???????????????????????????..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yjjl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					// $("#formId_yjjl_edit").form("setIsLabel", true);
					$("#formId_yjjl_edit").form("setReadOnly", true);
					$("#inputId_yjjl_id").val(data.data);
					
					$("#buttonId_yjjl_saveRecord").hide();
					
					// ??????????????????
					openPlandesc(3);

					// 2\3?????????
					if(opType == "1") {
						if(is3D) {
							// ?????? ????????????
							map.showHideByAreaId('', formData.ehrAddressFid, 1);
							// ?????????????????????????????????????????????
							map.getAllPointByGrandAndType(jsConst.ORG_CODE, formData.ehrAddressFid, '', 1);
							// ??????????????????
					        map.setAdminCameraByAreaId(formData.ehrAddressFid, jsConst.ORG_CODE);
						} else {
							// 2???????????????	
							var areaName = $("#comboboxId_yjjl_Address").combotree("getText");
							planeViewPosition(formData.ehrAddressFid, areaName);
						}
					}
					
					// ?????????????????????????????????
					loadAlarmPlan(jsConst.ORG_CODE, null, formData.ehrAlarmPlanFid);
 				} else {
					$.message({message:"???????????????", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"???????????????", cls:"error"});
			}
		});
	}
	
	function openPlandesc(type) {
		
		if($("#inputId_yjjl_id").val() == "") {
			$.message({message:"???????????????????????????", cls:"waring"});
			return;
		}
		
		var title = "";
		if(opType == "2") {
			title = "??????????????????";
		} else {
			title = "??????????????????";
		}
		
		var planId = $("#comboboxId_yjjl_planName").combobox("getValue");
		var recordId = $("#inputId_yjjl_id").val();
		// ??????????????????????????????dialog???????????????????????????, ??????????????????autoDestroy=true
		$("#dialogId_yjjl_zxjl").dialog({
			width : 1000,
			height : 600,
			title : title,
			url : "${ctx}/yjct/yabz/toView?id=" + planId + "&recordId=" + recordId + "&openPos=" + type				 
		});
		$("#dialogId_yjjl_zxjl").dialog("open");
	}
	
	function openEventReport() {
		
		if($("#inputId_yjjl_id").val() == "") {
			$.message({message:"???????????????????????????", cls:"waring"});
			return;
		}
		 
		$("#dialogId_yjjl_zxjl").dialog({
			width : 1000,
			height : 600,
			title : "????????????",
			url : "${ctx}/yjct/yjjl/toReport?type=1&ehrEmPlanFid=" + $("#comboboxId_yjjl_planName").combobox("getValue") + "&recordId=" + $("#inputId_yjjl_id").val()			 
		});
		$("#dialogId_yjjl_zxjl").dialog("open");
	}
	
	function saveRecordProcess(type) { 
		debugger;
		var process = $("#textareaId_yabz_edit_process").textbox("getValue");
		if(process == "") {
			$.message({message:"????????????????????????", cls:"waring"});
			return;
		}
		
		var methodId = $("#inputId_yabz_czlcMethodId").val();
		if(methodId == "") {
			$.message({message:"????????????????????????", cls:"waring"});
			return;
		}
		
		var data = {};
		if($("#inputId_yabz_edit_processId").val() != "") {
			data.id = $("#inputId_yabz_edit_processId").val();
		}
		data.ehpHandleProcess = process;		
		data.ehpHandleFid = $("#inputId_yjjl_id").val();
		data.ehpMethodFid = methodId;
		data.czlcNum = czlcCheckDatasView.length;
		console.info(data.ehpHandleFid);
		if(methodId == czlcCheckDatasView[czlcCheckDatasView.length - 1].id) {
			data.sfUpdateStatus = "1";
		}
		$.loading({text:"???????????????????????????..."});
		var alarmRecordId = '${alarmRecordId}';
		$.ajax({
			type : 'post',
			url : '${ctx}/yjct/yjjlgc/saveOrUpdate?alarmRecordId='+alarmRecordId+"&type="+type,
			data: data,
			dataType : 'json',
			success : function(data) {
				debugger;
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"???????????????", cls:"success"});
					nextPos++;
					if(methodId == czlcCheckDatasView[czlcCheckDatasView.length - 1].id) {
						$("#dialogId_yjjl_zxjl").dialog("close");
						if(opType == "3" && is3D) {
							map.removeAlarm();	// ??????????????????
						}
					} else {
						$("#divId_yabz_czlcmiddle ul").find("li[sort-num='" + nextPos + "']").click();	
						$("#textareaId_yabz_edit_process").textbox("setText", "");
					}
					if(type == 1){
						window.location.reload();
					}
 				} else if(data.code == "2") {
 					$.message({message:data.msg, cls:"waring"});
 				} else {
					$.message({message:"???????????????", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"???????????????", cls:"error"});
			}
		});
	}
	
</script>