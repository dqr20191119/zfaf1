<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_yrzq_List">
		<table class="table">
			<tr>
				<th>名称 ：</th>
				<td><cui:input id="title" name="title"></cui:input></td>
				<th>开始时间 ：</th>
				<td><cui:datepicker  id="startTime" name="startTime" model="timepicker" width="190"></cui:datepicker ></td>
				<th>结束时间 ：</th>
				<td><cui:datepicker  id="endTime" name="endTime" model="timepicker" width="190"></cui:datepicker ></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="searchjhrc"></cui:button>
				</td>
			</tr>
			<tr>
				<th>数据来源 ：</th>
				<td><cui:combobox id="dataType" name="dataType" data="comboboxData_sjly"></cui:combobox></td>
				<th>任务状态 ：</th>
				<td><cui:combobox id="state" name="state" data="comboboxData_rwzt"></cui:combobox></td>
				<th>是否超时 ：</th>
				<td><cui:combobox id="isTimeout" name="isTimeout" data="comboboxData_sfcs"></cui:combobox></td>
				<td>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<%-- <div style="height: 40px;">
		<cui:toolbar id="toolbarId_jhrc" data="toolbar_yrzq_List"></cui:toolbar>	
	</div> --%>	
		
	<cui:grid id="gridId_yrzq_List" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<%-- <cui:gridCol name="cpsPlanDetail" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_PlanDetail}">事由</cui:gridCol> --%>
			<cui:gridCol name="title" align="center">名称</cui:gridCol>
			<cui:gridCol name="startTime" align="center">开始时间</cui:gridCol>
			<cui:gridCol name="endTime" align="center">结束时间</cui:gridCol>
			<cui:gridCol name="dataType" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_sjly}">数据来源</cui:gridCol>
			<cui:gridCol name="state" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_rwzt}">任务状态</cui:gridCol>
			<cui:gridCol name="isTimeout" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_sfcs}">是否超时</cui:gridCol>
			<cui:gridCol name="finishDate" align="center">完成时间</cui:gridCol>
			<cui:gridCol name="mark" align="center">备注</cui:gridCol>
			<cui:gridCol name="finishUserName" align="center">完成人</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_yrzq_List" />
	</cui:grid>
	
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var comboboxData_PlanDetail = <%=CodeFacade.loadCode2Json("4.20.60")%>;   //日程事由
	var comboboxData_sjly=[{value:'0',text:'本部门的计划日程'},{value:'1',text:'事务督办'},{value:'2',text:'任务下达'},{value:'3',text:'视频督察'},{value:'4',text:'设备维修'},{value:'5',text:'风险防控措施'}];//数据来源
	/* var comboboxData_rwzt=[{value:'0',text:'待执行'},{value:'1',text:'执行中'},{value:'2',text:'已完成'}];//任务状态 */
	var comboboxData_rwzt=[{value:'0',text:'待执行'},{value:'1',text:'未完成'},{value:'2',text:'已完成'}];//任务状态
	var comboboxData_sfcs=[{value:'0',text:'未超时'},{value:'1',text:'已超时'}];//是否超时
	
	$.parseDone(function() {
		
		/* if(USER_LEVEL == '1'|| cusNumber=='6500') {
			$("#comboboxId_query_jhrcJq").combobox("option","readonly",true);
			$("#toolbarId_jhrc").toolbar("disable");
			
		} else {
			$("#comboboxId_query_jhrcJq").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
			searchjhrc();
		} */
		if('${state}'!=''){
			$("#state").combobox("setValue",'${state}');
		}
		if('${isTimeout}'!=''){
			$("#isTimeout").combobox("setValue",'${isTimeout}');
		}
		searchjhrc();
	});
	
	function searchjhrc() {
		var type = '${type}';
		if(USER_LEVEL == '3') {  //监区权限 只能看自己监区的信息
			$("#comboboxId_query_jhrcJq").combobox("setValue",drpmntId);
			$("#comboboxId_query_jhrcJq").combobox("option","readonly",true);
		}
		var formData = $("#formId_yrzq_List").form("formData");
		
		$("#gridId_yrzq_List").grid("option", "postData", formData);
		$("#gridId_yrzq_List").grid("reload");
		$("#gridId_yrzq_List").grid("reload","${ctx}/wghgl/yrzq/searchListPage?type="+type); 
	}
	
	function clear() {
		$("#formId_yrzq_List").form("reset");
	}
	
	var toolbar_yrzq_List = [{
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}];
	
	
	function openDailog(event, ui) {
	
		var url;
		var dialog_w;
		var dialog_h;
		
		if(ui.id == "btnId_edit") {
			var selarrrow = $("#gridId_yrzq_List").grid("option", "selarrrow");	
			var rowData = $("#gridId_yrzq_List").grid("getRowData", selarrrow[0]);
			var state=rowData.state;
			if(state=='2'){
				$.message({message:"已完成事件不可修改！", cls:"waring"});
				return;
			}
			if(selarrrow && selarrrow.length == 1) {
				url : "${ctx}/wghgl/yrzq/toEdit?id="+selarrrow[0]
				dialog_w = "400";
				dialog_h = "500";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_yrzq_edit").dialog({
			width : dialog_w,
			height : dialog_h,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_yrzq_edit").dialog("open");
		 
	}
	
	
	
	</script>