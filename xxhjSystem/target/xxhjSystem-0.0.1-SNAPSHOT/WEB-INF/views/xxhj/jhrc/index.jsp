<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_jhrc_query">
		<table class="table">
			<tr>
				<th>部门：</th>
				<td><cui:combobox id="comboboxId_query_jhrcJq" name="cpsDrpmntId"></cui:combobox></td>
				<th>事由 ：</th>
				<td><cui:combobox id="comboboxId_query_jhrcSy" name="cpsPlanDetail" data="comboboxData_PlanDetail"></cui:combobox></td>
				<%-- &nbsp;&nbsp;&nbsp;&nbsp;<th>日期：</th>
				<td><cui:datepicker id ="cpsStartDate"  name ="cpsStartDate" width = "150" dateFormat="yyyy-MM-dd"></cui:datepicker>
				&nbsp;~&nbsp;<cui:datepicker id ="cpsEndDate"  name ="cpsEndDate" startDateId="cpsStartDate" width = "150" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td> --%>
				<td>
	 				&nbsp;&nbsp;&nbsp;&nbsp;<cui:button label="查询" componentCls="btn-primary" onClick="searchjhrc"></cui:button>
					&nbsp;&nbsp;<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_jhrc" data="toolbar_jhrc_query"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_jhrc_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="cpsDrpmntId" hidden="true">部门Id</cui:gridCol>
			<cui:gridCol name="cpsDrpmntName" align="center">部门名称</cui:gridCol>
			<cui:gridCol name="cpsPlanDetail" align="center" formatter="convertCode" revertCode="true" formatoptions="{'data': comboboxData_PlanDetail}">事由</cui:gridCol>
			<cui:gridCol name="cpsScheduleTime" align="center">计划开始时间</cui:gridCol>
			<cui:gridCol name="cpsScheduleEndTime" align="center">计划结束时间</cui:gridCol>
			<cui:gridCol name="cpsStartDate" align="center">生效日期</cui:gridCol>
			<cui:gridCol name="cpsEndDate" align="center">结束日期</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_jhrc_query" />
	</cui:grid>
	
	<cui:dialog id="dialogId_jhrc_query" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_jhrc">
	</cui:dialog>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var comboboxData_PlanDetail = <%=CodeFacade.loadCode2Json("4.20.60")%>;   //日程事由
	var comboboxData_tzsb=[{value:'0',text:'通讯融合'},{value:'1',text:'喇叭'}];//设备
	
	$.parseDone(function() {
		
		if(USER_LEVEL == '1'|| cusNumber=='1100') {
			$("#comboboxId_query_jhrcJq").combobox("option","readonly",true);
			$("#toolbarId_jhrc").toolbar("disable");
			
		} else {
			$("#comboboxId_query_jhrcJq").combobox("reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
			searchjhrc();
		}
	});
	
	function searchjhrc() {
		
		if(USER_LEVEL == '3') {  //监区权限 只能看自己监区的信息
			$("#comboboxId_query_jhrcJq").combobox("setValue",drpmntId);
			$("#comboboxId_query_jhrcJq").combobox("option","readonly",true);
		}
		var formData = $("#formId_jhrc_query").form("formData");
		
		$("#gridId_jhrc_query").grid("option", "postData", formData);
		$("#gridId_jhrc_query").grid("reload");
		$("#gridId_jhrc_query").grid("reload","${ctx}/xxhj/jhrc/searchData"); 
	}
	
	function clear() {
		$("#formId_jhrc_query").form("reset");
	}
	
	var toolbar_jhrc_query = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	}];
	
	var buttons_jhrc = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_jhrc_query").dialog("close");
	    }            
	}];
	
	function openDailog(event, ui) {
	
		var url;
		var dialog_w;
		var dialog_h;
		
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/xxhj/jhrc/toEdit";
			dialog_w = "380";
			dialog_h = "450";
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_jhrc_query").grid("option", "selarrrow");	
			var rowData = $("#gridId_jhrc_query").grid("getRowData", selarrrow[0]);
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/xxhj/jhrc/toEdit?id=" + selarrrow[0]+"&areaId="+rowData.cpjLch;
				dialog_w = "380";
				dialog_h = "450";
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
		$("#dialogId_jhrc_query").dialog({
			width : dialog_w,
			height : dialog_h,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_jhrc_query").dialog("open");
		 
	}
	
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_jhrc_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_jhrc_edit").form("formData");
		formData["cpsDrpmntName"] = $("#comboboxId_edit_jhrcJq").combobox("getText");
		
		$.loading({text:"正在处理中，请稍后..."});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/xxhj/jhrc/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.success) {
					$.messageQueue({ message : data.obj, cls : "success", iframePanel : true, type : "info" });
					$("#dialogId_jhrc_query").dialog("close");
					$("#gridId_jhrc_query").grid("reload");
					
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function deleteByIds() {
		
		var selarrrow = $("#gridId_jhrc_query").grid("option", "selarrrow");			
		if(selarrrow && selarrrow.length > 0) {
			
			$.confirm("确认删除？", function(r) {
				if(r) {
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
					}
					$.loading({text:"正在处理中，请稍后..."});
					$.ajax({
						type : 'post',
						url : '${ctx}/xxhj/jhrc/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if (data.success) {
								$.messageQueue({ message : data.obj, cls : "success", iframePanel : true, type : "info" });
								$("#gridId_jhrc_query").grid("reload");
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"}); 
						}
					});
				}
			}); 		
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}	
	</script>