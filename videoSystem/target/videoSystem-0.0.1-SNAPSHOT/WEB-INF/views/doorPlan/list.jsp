<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_mjya_query">
			<table class="table">
				<tr>
					<th>名称：</th>
					<td>
						<cui:input name="pdoPlanName" componentCls="form-control"></cui:input>
					</td>
					<th>状态：</th>
					<td>
						<cui:combobox name="pdoSttsIndc" data="mjya_sttsData" componentCls="form-control"></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="mjya_query"></cui:button>
						<cui:button label="重置" onClick="mjya_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_screenPlan" data="toolbar_screenPlanDate"></cui:toolbar>
		<cui:grid id="gridId_doorPlan" fitStyle="fill" multiselect="true" colModel="gridId_doorPlan_colModelDate">
			<cui:gridPager gridId="gridId_doorPlan" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_doorPlan" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	// 状态
	var mjya_sttsData = <%=CodeFacade.loadCode2Json("4.0.11")%>;

	$.parseDone(function() {
		var url = "${ctx}/door/plan/listAll.json?pdoCusNumber=" + cusNumber;
		$("#gridId_doorPlan").grid("reload", url);

	});
	var gridId_doorPlan_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "PDO_PLAN_NAME",
		label : "名称"
	}, {
		name : "PDO_OFF_TIME",
		label : "启用时间"
	}, {
		name : "PDO_TIME_LIMIT",
		label : "有效时限（小时）"
	}, {
		name : "PDO_STTS_INDC",
		label : "状态",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : mjya_sttsData
		}
	}, {
		name : "PDO_REMARK",
		label : "备注"
	} ];

	toolbar_screenPlanDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "增加",
		"onClick" : "openDailog",
	}, {
		"type" : "button",
		"id" : "btnId_update",
		"label" : "修改",
		"onClick" : "openDailog",
	}, {
		"type" : "button",
		"id" : "btnId_del",
		"label" : "删除",
		"onClick" : "remove",
	}, {
		"type" : "button",
		"id" : "btnId_setting",
		"label" : "关联门禁",
		"onClick" : "openDailog",
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {
		case "btnId_add":
			dialog_width = "350";
			dialog_height = "300";
			url = "${ctx}/door/plan/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_doorPlan").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_doorPlan").grid("getRowData", selected[0]);
				dialog_width = "350";
				dialog_height = "300";
				url = "${ctx}/door/plan/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_setting":
			var selected = $("#gridId_doorPlan").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_doorPlan").grid("getRowData", selected[0]);
				dialog_width = "1000";
				dialog_height = "600";
				url = "${ctx}/door/plan/openDialog/setting?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要操作的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_doorPlan").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_doorPlan").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_doorPlan").grid("option", "selarrrow");
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/door/plan/delete.json",
						dataType : 'json',
						type : 'post',
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_doorPlan").grid("reload");
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
	
	function mjya_query() {
		var formData = $("#formId_mjya_query").form("formData");
		$("#gridId_doorPlan").grid("option", "postData", formData);
		$("#gridId_doorPlan").grid("reload");

	}

	function mjya_clear() {
		$("#formId_mjya_query").form("clear");
	}
</script>