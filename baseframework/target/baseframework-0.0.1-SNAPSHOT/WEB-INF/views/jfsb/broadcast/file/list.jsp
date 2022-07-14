<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_broadcastFile_query">
			<table class="table">
				<tr>
					<th>曲目名称：</th>
					<td>
						<cui:input id="inputId_bfdName" name="bfdName" componentCls="form-control"></cui:input>
					</td>
					<td>
						<cui:button label="查询" onClick="broadcastFile_query"></cui:button>
						<cui:button label="重置" onClick="broadcastFile_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_broadcastFile" data="toolbar_broadcastFileDate"></cui:toolbar>
		<cui:grid id="gridId_broadcastFile" fitStyle="fill" multiselect="true" colModel="gridId_broadcastFile_colModelDate">
			<cui:gridPager gridId="gridId_broadcastFile" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_broadcastFile" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	
	$.parseDone(function() {
		$("#gridId_broadcastFile").grid("reload","${ctx}/broadcastFile/listAll.json?bfdCusNumber=" + cusNumber + "&P_orders=bfd_order,asc");
	});
	
	var gridId_broadcastFile_colModelDate = [ {
		name : "id",
		label : "主键",
		key : true,
		hidden : true
	}, {
		name : "bfdIdnty",
		label : "曲目编号",
		align : "center",
	}, {
		name : "bfdName",
		label : "曲目名称",
		align : "center",
		width : 250
	}, {
		name : "bfdUpdtTime",
		label : "最近更新时间",
		align : "center",
		width : 250
	}];

	toolbar_broadcastFileDate = [{
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
			dialog_width = "440";
			dialog_height = "360";
			url = "${ctx}/broadcastFile/openDialog/save";
			break;
		case "btnId_update":
			var selected = $("#gridId_broadcastFile").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_broadcastFile").grid("getRowData", selected[0])
				dialog_width = "440";
				dialog_height = "360";
				url = "${ctx}/broadcastFile/openDialog/update?id=" + rowData.id;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_broadcastFile").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_broadcastFile").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_broadcastFile").grid("option", "selarrrow");
		console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/broadcastFile/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_broadcastFile").grid("reload");
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
	
	function broadcastFile_query() {
		var postData = {};
		var bfdName = $('#inputId_bfdName').val();

		if (bfdName != "") {
			postData['bfdName'] = bfdName;
		}

		$('#gridId_broadcastFile').grid('option', 'postData', postData);
		$('#gridId_broadcastFile').grid('reload',"${ctx}/broadcastFile/listAll.json?bfdCusNumber="+ cusNumber);
	}

	function broadcastFile_clear() {
		$("#formId_broadcastFile_query").form("clear");
	}
</script>