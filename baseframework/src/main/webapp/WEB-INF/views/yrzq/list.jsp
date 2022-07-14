<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page import="com.cesgroup.framework.utils.Util"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_swdb_List">
		<table class="table">
			<tr>
				<th>执勤日期 ：</th>
				<td><cui:datepicker  id="sxsj" value="<%=Util.getCurrentDate(false) %>" name="sxsj" dateFormat="yyyy-MM-dd" width="190"></cui:datepicker ></td>
				<th>任务状态 ：</th>
				<td><cui:combobox id="state" name="state" data="comboboxData_rwzt"></cui:combobox></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="searchswdb"></cui:button>
				</td>
				<td>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_swdb" data="toolbar_swdb_List"></cui:toolbar>	
	</div>
	<cui:grid id="gridId_swdb_List" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="state" hidden="true">state</cui:gridCol>
			<cui:gridCol name="departId" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_jq}">部门名称</cui:gridCol>
			<cui:gridCol name="sxsj" align="center">执勤日期 </cui:gridCol>
			<cui:gridCol name="cs" align="center">执勤次数</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_swdb_List" />
	</cui:grid>
	<cui:dialog id="dialogId_swdb_view" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var comboboxData_jq = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var comboboxData_rwzt=[{value:'0',text:'未完成'},{value:'1',text:'未完成'},{value:'2',text:'已完成'}];//任务状态
	$.parseDone(function() {
		searchswdb();
	});
	
	function searchswdb() {
		var formData = $("#formId_swdb_List").form("formData");
		$("#gridId_swdb_List").grid("option", "postData", formData);
		$("#gridId_swdb_List").grid("reload","${ctx}/wghgl/yrzq/searchSwdbPage"); 
	}
	
	function clear() {
		$("#formId_swdb_List").form("reset");
	}
	
	var toolbar_swdb_List = [{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];
	
	
	function openDailog(event, ui) {
	
		var url;
		var dialog_w;
		var dialog_h;
		
		if(ui.id == "btnId_view") {
			var selarrrow = $("#gridId_swdb_List").grid("option", "selarrrow");	
			var rowData = $("#gridId_swdb_List").grid("getRowData", selarrrow[0]);
			var state = rowData.state;
			var dateTime = rowData.sxsj;
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/wghgl/yrzq/toView?departId="+rowData.departId+"&dateTime="+dateTime+"&state="+state
				dialog_w = "1000";
				dialog_h = "600";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_swdb_view").dialog({
			width : dialog_w,
			height : dialog_h,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_swdb_view").dialog("open");
		 
	}
	
	
	
	</script>