<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.cesgroup.prison.broadcastPlan.common.BroadcastPlanCommom"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="margin-left: 26%; height: 98%; margin: 5px">
		<cui:form id="formId_gb_query">
			<table class="table">
				<tr>
					<th>广播预案名称：</th>
					<td>
						<cui:input id="dbpBroadcastPlanName" name="dbpBroadcastPlanName" componentCls="form-control"></cui:input>
					</td>
					<th>广播曲目名称：</th>
					<td>
						<cui:input id="dbpBroadcastFileDtlsName" name="dbpBroadCastName" componentCls="form-control"></cui:input>
					</td>
					<td>
						<cui:button label="查询" onClick="gb_query"></cui:button>
						<cui:button label="重置" onClick="gb_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_broadcast_plan" data="toolbar_broadcastDate"></cui:toolbar>
		<cui:grid id="gridId_broadcast" fitStyle="fill" multiselect="true" colModel="gridId_broadcast_colModelDate">
			<cui:gridPager gridId="gridId_broadcast" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_broadcast" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
 	var gb_qm =<%=BroadcastPlanCommom.broadcastFileNameCommbox()%> ;//曲目
	var gb_name= <%=BroadcastPlanCommom.broadcastNameCommbox()%>;//广播
	
	$.parseDone(function() {
		/* var url = "${ctx}/broadcast/listAll.json?bbdCusNumber=" + cusNumber + "&P_orders=bbd_crte_time,desc";
		$("#gridId_broadcast").grid("reload", url); */
			$("#gridId_broadcast").grid("reload","${ctx}/broadcastPlan/listAll.json?bbdCusNumber=" + cusNumber);
	});
	
	var gridId_broadcast_colModelDate = [ {
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		name : "dbpBroadcastPlanName",
		label : "广播预案名称",
		align : "center",
	}, {
		name : "dbpBroadcastName",
		label : "广播名称",
		align : "center",
		width : 250
	}, {
		name : "dbpBroadcastFileDtlsName",
		label : "广播曲目名称",
		align : "center",
		width : 250
	}];

	toolbar_broadcastDate = [ {
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
	}];
	
	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var url;
		switch (ui.id) {
		case "btnId_add":
			dialog_width = "1000";
			dialog_height = "600";
			url = "${ctx}/broadcastPlan/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_broadcast").grid("option", "selarrrow");
			debugger;
			if (selected.length == 1) {
				var rowData = $("#gridId_broadcast").grid("getRowData", selected[0])
				dialog_width = "1000";
				dialog_height = "600";
				url = "${ctx}/broadcastPlan/openDialog/update?id=" + rowData.id;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_broadcast").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_broadcast").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_broadcast").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		debugger;
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/broadcastPlan/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_broadcast").grid("reload");
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
	
	function gb_query() {
		var postData = {};
		var dbpBroadcastPlanName = $('#dbpBroadcastPlanName').val();
		var dbpBroadcastFileDtlsName = $('#dbpBroadcastFileDtlsName').val();
		//var bbdAreaId=$('#bbdAreaId').combotree("getValue");
		//var bbdSttsIndc = $('#comboboxId_bbdSttsIndc').combobox("getValue");
        //获取左侧树菜单中选中节点,单选
      //  var bbdAreaIdArr = $("#broadcast_regionTree").tree("getSelectedNodes");

		if (dbpBroadcastPlanName != "") {
			postData['dbpBroadcastPlanName'] = dbpBroadcastPlanName;
		}
		if (dbpBroadcastFileDtlsName != "") {
			postData['dbpBroadcastFileDtlsName'] = dbpBroadcastFileDtlsName;
		}
		$('#gridId_broadcast').grid('option', 'postData', postData);
		$('#gridId_broadcast').grid('reload',"${ctx}/broadcastPlan/listAll.json?bbdCusNumber="+ cusNumber);
	}

	function gb_clear() {
		$("#formId_gb_query").form("clear");
	}
</script>