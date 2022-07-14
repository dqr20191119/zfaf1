<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_dpya_query">
		<table class="table">
			<tr>
				<th>厂家：</th>
				<td>
					<cui:combobox name="splManufacturersId" componentCls="form-control" data="dpya_changjiaData"></cui:combobox>
				</td>
				<th>状态：</th>
				<td>
					<cui:combobox name="splStatusIndc" data="dpya_sttsData" componentCls="form-control"></cui:combobox>
				</td>
				<td>
					<cui:button label="查询" onClick="dpya_query"></cui:button>
					<cui:button label="重置" onClick="dpya_clear"></cui:button>
				</td>
			</tr>
		</table>
		</cui:form>
		<cui:toolbar id="toolbarId_screenPlan" data="toolbar_screenPlanDate"></cui:toolbar>
		<cui:grid id="gridId_screenPlan" fitStyle="fill" singleselect="true" colModel="gridId_screenPlan_colModelDate">
			<cui:gridPager gridId="gridId_screenPlan" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_screenPlan" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	//厂家
	var dpya_changjiaData = <%=CodeFacade.loadCode2Json("4.20.54")%>;
	// 状态
	var dpya_sttsData = <%=CodeFacade.loadCode2Json("4.20.55")%>;
	
	var dpya_isDynamic = <%=CodeFacade.loadCode2Json("4.0.1")%>;

	$.parseDone(function() {
		var url = "${ctx}/screenPlan/listAll.json?splCusNumber=" + cusNumber + "&P_orders=spl_crte_time,desc";
		$("#gridId_screenPlan").grid("reload", url);

	});
	var gridId_screenPlan_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "SPL_PLAN_INDC",
		label : "编号"
	}, {
		name : "SPL_PLAN_NAME",
		label : "名称"
	}, {
		name : "SPL_STATUS_INDC",
		label : "状态",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_sttsData } 
	}, {
		name : "SPL_MANUFACTURERS_ID",
		label : "厂家",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_changjiaData } 
	}, {
		name : "SPL_IS_DYNAMIC",
		label : "是否动态",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dpya_isDynamic } 
	}, {
		name : "SPL_REMARK",
		label : "备注"
	}];

	toolbar_screenPlanDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "增加",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_update",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_del",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} , {
		"type" : "button",
		"id" : "btnId_setting",
		"label" : "信号分组",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {
		case "btnId_add":
			dialog_width = "350";
			dialog_height = "400";
			url = "${ctx}/screenPlan/openDialog/save";
			break;

		case "btnId_update":
			var selrow = $("#gridId_screenPlan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
				dialog_width = "350";
				dialog_height = "400";
				url = "${ctx}/screenPlan/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
			
		case "btnId_setting":
			var selrow = $("#gridId_screenPlan").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
				dialog_width = "1200";
				dialog_height = "600";
				url = "${ctx}/screenPlan/openDialog/setting?id=" + rowData.ID + "&name="+rowData.SPL_PLAN_NAME;
				url = encodeURI(url);
				url = encodeURI(url);
			} else {
				$.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_screenPlan").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_screenPlan").dialog("open");
		}
	}

	function remove() {
		var selrow = $("#gridId_screenPlan").grid("option", "selrow");
		if (selrow != null) {
			var rowData = $("#gridId_screenPlan").grid("getRowData", selrow.toString());
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/screenPlan/delete.json?id=" + rowData.ID,
						dataType : "json",
						type : "post",
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_screenPlan").grid("reload");
								$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							} else {
								$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message : "请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	function dpya_query() {
		var formData = $("#formId_dpya_query").form("formData");
		$("#gridId_screenPlan").grid("option", "postData", formData);
		$("#gridId_screenPlan").grid("reload");
		//关闭当前弹窗		
		$("#dialogId_screenPlan").dialog("close");
	}

	function dpya_clear() {
		$("#formId_dpya_query").form("clear");
	}
</script>