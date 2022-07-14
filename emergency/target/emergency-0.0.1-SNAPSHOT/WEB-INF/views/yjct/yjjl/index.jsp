<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%">
	<cui:form id="formId_yjjl_query">
		<table class="table">			
			<tr>
				<td>预案名称：</td>
				<td><cui:input name="epiPlanName"></cui:input></td>		
				<td>发生时间：</td>
				<td>
					<cui:datepicker name="ehrTimeStart" dateFormat="yyyy-MM-dd"></cui:datepicker>
					至
					<cui:datepicker name="ehrTimeEnd" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>		 
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
 	
	<div style="height: 480px; margin: 0px 10px;">
		<cui:toolbar id="toolbarId_yjjl" data="toolbar_yjjl"></cui:toolbar>			
		<cui:grid id="gridId_yjjl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
			url="${ctx}/yjct/yjjl/searchData"
			sortname="ehrCrteTime, desc" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="ehrEmPlanFid" hidden="true">ehrEmPlanFid</cui:gridCol>
				<cui:gridCol name="ehrAlarmRecordId" hidden="true">ehrAlarmRecordId</cui:gridCol>
				<cui:gridCol name="epiPlanName" width="100">预案名称</cui:gridCol>
				<cui:gridCol name="abdAreaName" width="100">地点</cui:gridCol>
				<cui:gridCol name="ehrTime" width="100" align="center">发生时间</cui:gridCol>	
				<cui:gridCol name="ehrSaveTime" width="100" align="center">存档时间</cui:gridCol>							 
				<cui:gridCol name="ehrStatus" width="100" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_yjct_recordStatus
				}">状态</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_yjjl" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_yjjl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
	
	var combobox_yjct_recordType = <%=CodeFacade.loadCode2Json("4.20.56")%>;
	var combobox_yjct_recordStatus = <%=CodeFacade.loadCode2Json("4.20.6")%>;
	var combobox_yjct_actionType = <%=CodeFacade.loadCode2Json("4.20.5")%>;
	var combobox_yjct_planType = <%=CodeFacade.loadCode2Json("4.20.4")%>;
	
 	var toolbar_yjjl = [{
		"type" : "button",
		"id" : "btnId_deal",
		"label" : "处置",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_report",
		"label" : "事件报告",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看处置过程",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];

	$.parseDone(function() {
	
	});
	
	function openDailog(event, ui) {
		 
		var url;		 
		if(ui.id == "btnId_view") {	
			
			var selarrrow = $("#gridId_yjjl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
								
				var rowData = $("#gridId_yjjl").grid("getRowData", selarrrow[0]);				 
				url = "${ctx}/yjct/yabz/toView?id=" + rowData.ehrEmPlanFid + "&recordId=" + selarrrow[0] + "&openPos=3&isDisplay=0";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		} else if(ui.id == "btnId_deal") {
			
			var selarrrow = $("#gridId_yjjl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
								
				var rowData = $("#gridId_yjjl").grid("getRowData", selarrrow[0]);
				if(rowData.ehrStatus != "1") {
					$.message({message:"请选择处置中的记录！", cls:"waring"});
					return;
				}
				
				var panel = $("#layout1").layout("panel", "east");
				panel.panel("refresh", "${ctx}/yjct/yjjl/toZxjl?type=4&recordId=" + selarrrow[0] + "&alarmRecordId=" + rowData.ehrAlarmRecordId);
				$("#dialog").dialog("close");
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
			}
			
			return;
		} else if(ui.id == "btnId_report") {
			
			var selarrrow = $("#gridId_yjjl").grid("option", "selarrrow");			
			if(selarrrow && selarrrow.length == 1) {
								
				var rowData = $("#gridId_yjjl").grid("getRowData", selarrrow[0]);
				if(rowData.ehrStatus == "1") {
					$.message({message:"请选择处置完成或已归档的记录！", cls:"waring"});
					return;
				}
				
				var rowData = $("#gridId_yjjl").grid("getRowData", selarrrow[0]);				 
				url = "${ctx}/yjct/yjjl/toReport?type=2&ehrEmPlanFid=" + rowData.ehrEmPlanFid + "&recordId=" + selarrrow[0];
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}
		}
		
		$("#dialogId_yjjl").dialog({
			width : 1000,
			height : 600,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yjjl").dialog("open");
	}
	
	function search() {		
		
		var formData = $("#formId_yjjl_query").form("formData");
		$("#gridId_yjjl").grid("option", "postData", formData);
		$("#gridId_yjjl").grid("reload");
	}
	
	function reset() {
		
		$("#formId_yjjl_query").form("reset");
	}
</script>