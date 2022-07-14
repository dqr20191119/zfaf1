<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<cui:tabs id="tabId" heightStyle="fill">
	<ul>
		<li>
			<a href="#fragment_type">故障类型</a>
		</li>
		<li>
			<a href="#fragment_content">故障内容</a>
		</li>
	</ul>
	<div id="fragment_type">
		<cui:form id="formId_gzlx_query">
			<table class="table">
				<tr>
					<th>故障类型：</th>
					<td>
						<cui:input name="dftFaultName" componentCls="form-control"></cui:input>
					</td>
					<th>是否启用：</th>
					<td>
						<cui:combobox name="dftSttsIndc" data="dftSttsIndc"></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="queryGzlx('1')"></cui:button>
						<cui:button label="重置" onClick="resetGzlx('1')"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar data="toolbar_gzlxDate"></cui:toolbar>
		<cui:grid id="gridId_gzlx" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_gzlx_colModelDate">
			<cui:gridPager gridId="gridId_gzlx" />
		</cui:grid>

	</div>

	<div id="fragment_content">
		<cui:form id="formId_gznr_query">
			<table class="table">
				<tr>
					<th>故障类型：</th>
					<td>
						<cui:combobox id="combId_gzlx_query" name="dftParentId" componentCls="form-control"></cui:combobox>
					</td>
					<th>故障内容：</th>
					<td>
						<cui:input name="dftFaultName" componentCls="form-control"></cui:input>
					</td>
					<th>是否启用：</th>
					<td>
						<cui:combobox name="dftSttsIndc" data="dftSttsIndc"></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="queryGzlx('2')"></cui:button>
						<cui:button label="重置" onClick="resetGzlx('2')"></cui:button>
					</td>
				</tr>
			</table>

		</cui:form>
		<cui:toolbar data="toolbar_gznrDate"></cui:toolbar>
		<cui:grid id="gridId_gznr" rownumbers="false" singleselect="true" fitStyle="fill" width="auto" colModel="gridId_gznr_colModelDate">
			<cui:gridPager gridId="gridId_gznr" />
		</cui:grid>

	</div>
</cui:tabs>

<cui:dialog id="dialogId_gzlx" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	var dftSttsIndc = <%=CodeFacade.loadCode2Json("4.0.1")%>;//状态
	
	$.parseDone(function() {
		var url = "${ctx}/deviceFaultType/listAll.json?type=1&dftCusNumber=" + cusNumber + "&P_orders=dft_updt_time,desc";

		$("#gridId_gzlx").grid("reload", url);

		url = "${ctx}/deviceFaultType/listAll.json?type=2&dftCusNumber=" + cusNumber + "&P_orders=dft_updt_time,desc";

		$("#gridId_gznr").grid("reload", url);

		$("#combId_gzlx_query").combobox( "reload", "${ctx}/deviceFaultType/seachComboData.json?cusNumber=" + cusNumber + "&typeClassify=1");
	});

	var gridId_gzlx_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "DFT_FAULT_NAME",
		label : "故障类型",
		align : "center",
		width : 280
	}, {
		name : "DFT_STTS_INDC",
		label : "是否启用",
		align : "center",
		width : 80,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dftSttsIndc } 
	} ];

	var gridId_gznr_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "DFT_FAULT_NAME",
		label : "故障内容",
		align : "center",
		width : 240

	}, {
		name : "DFT_PARENT_NAME",
		label : "故障类型",
		align : "center",
		width : 190

	}, {
		name : "DFT_STTS_INDC",
		label : "是否启用",
		align : "center",
		width : 80,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':dftSttsIndc } 
	}, {
		name : "FDR_MAINTAIN_DPRTMNT",
		label : "维修部门",
		align : "center",
		width : 120

	}, {
		name : "FDR_HELP_DPRTMNT",
		label : "协助部门",
		align : "center",
		width : 120

	} ];

	var toolbar_gzlxDate = [ {
		"type" : "button",
		"id" : "btnId_add_type",
		"label" : "增加",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_update_type",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_del_type",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} ];

	var toolbar_gznrDate = [ {
		"type" : "button",
		"id" : "btnId_add_content",
		"label" : "增加",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_update_content",
		"label" : "修改",
		"onClick" : "openDailog",
		//"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_del_content",
		"label" : "删除",
		"onClick" : "remove",
		//"componentCls" : "btn-primary"
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {

		case "btnId_add_type":
			dialog_width = "410";
			dialog_height = "200";
			url = "${ctx}/deviceFaultType/openDialog/add?type=1";
			break;

		case "btnId_update_type":
			var selrow = $("#gridId_gzlx").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_gzlx").grid("getRowData", selrow.toString())
				dialog_width = "410";
				dialog_height = "200";
				url = "${ctx}/deviceFaultType/openDialog/update?id=" + rowData.ID + "&type=1";
			} else {
				$.messageQueue({ message :"请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		case "btnId_add_content":
			dialog_width = "410";
			dialog_height = "315";
			url = "${ctx}/deviceFaultType/openDialog/add?type=2";
			break;

		case "btnId_update_content":
			var selrow = $("#gridId_gznr").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_gznr").grid("getRowData", selrow.toString())
				dialog_width = "410";
				dialog_height = "315";
				url = "${ctx}/deviceFaultType/openDialog/update?id=" + rowData.ID + "&type=2";
			} else {
				$.messageQueue({ message :"请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_gzlx").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_gzlx").dialog("open");
		}
	}

	function remove(event, ui) {
		var type;
		var $grid;
		if (ui.id == "btnId_del_type") {
			type = 1;
			$grid = $("#gridId_gzlx");
		} else {
			type = 2;
			$grid = $("#gridId_gznr");
		}
		var selrow = $grid.grid("option", "selrow");

		if (selrow != null) {
			var rowData = $grid.grid("getRowData", selrow.toString());
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/deviceFaultType/delete.json?id=" + rowData.ID + "&dftUpdtdUserId=" + userId + "&dftActionIndc=3",
						dataType : "json",
						type : "post",
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$grid.grid("reload");
								if(type == "1"){
									$("#combId_gzlx_query").combobox( "reload");
									$("#gridId_gznr").grid("reload");
								}
								$.messageQueue({ message : "删除记录成功", cls : "success", iframePanel : true, type : "info" });
							} else {
								$.messageQueue({ message :data.msg, cls : "warning", iframePanel : true, type : "info" });
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({ message :"请选择要删除的记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}

	function queryGzlx(type) {
		if (type == '1') {
			var formData = $("#formId_gzlx_query").form("formData");
			$("#gridId_gzlx").grid("option", "postData", formData);
			$("#gridId_gzlx").grid("reload");
		} else {
			var formData = $("#formId_gznr_query").form("formData");
			$("#gridId_gznr").grid("option", "postData", formData);
			$("#gridId_gznr").grid("reload");
		}
	}

	function resetGzlx(type) {
		if (type == '1') {
			$("#formId_gzlx_query").form("reset");
		} else {
			$("#formId_gznr_query").form("reset");
		}
	}
</script>