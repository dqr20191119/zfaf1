<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_mjkzq_query">
			<table class="table">
				<tr>
					<th>名称：</th>
					<td>
						<cui:input name="dcdName" type="text" ></cui:input>
					</td>
					<td>
						<cui:button label="查询" onClick="query"></cui:button>
						<cui:button label="重置" onClick="clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar  data="toolbar_doorControlDate"></cui:toolbar>
		<cui:grid id="gridId_doorControl" fitStyle="fill" multiselect="true" colModel="gridId_doorControl_colModelDate">
			<cui:gridPager gridId="gridId_doorControl" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_doorControl" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	
	var userId = jsConst.USER_ID;//当前登陆者
	
	$.parseDone(function() {
		var url="${ctx}/doorControl/listAll.json?dcdCusNumber=" + cusNumber + "&P_orders=dcd_crte_time,desc" 
		$("#gridId_doorControl").grid("reload", url);
	});
	var gridId_doorControl_colModelDate = [ { name : "ID", label : "ID", hidden : true, key : true }, 
		{ name : "DCD_IDNTY", label : "编号" }, 
		{ name : "DCD_NAME", label : "名称", }, 
		{ name : "DCD_IP_ADDRS", label : "IP",  }, 
		{ name : "DCD_PORT", label : "端口",  }, 
		{ name : "DCD_SN", label : "序列号",  }, 
		{ name : "DCD_USER_NAME", label : "用户名", }, 
		{ name : "DCD_USER_PASSWORD", label : "密码", }, 
		{ name : "DCD_REMARK", label : "备注", }];

	var toolbar_doorControlDate = [ { "type" : "button", "id" : "btnId_add", "label" : "增加", "onClick" : "openDailog", /* "componentCls" : "btn-primary" */ }, 
		{ "type" : "button", "id" : "btnId_update", "label" : "修改", "onClick" : "openDailog", /* "componentCls" : "btn-primary" */ }, 
		{ "type" : "button", "id" : "btnId_del", "label" : "删除", "onClick" : "remove", /* "componentCls" : "btn-primary" */ }];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {

		case "btnId_add":
			dialog_width = "640";
			dialog_height = "300";
			url = "${ctx}/doorControl/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_doorControl").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_doorControl").grid("getRowData", selected[0])
				dialog_width = "640";
				dialog_height = "300";
				url = "${ctx}/doorControl/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_doorControl").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_doorControl").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_doorControl").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/doorControl/delete.json",
						dataType : 'json',
						type : 'post',
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_doorControl").grid("reload");
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
	function query() {
		var formData = $("#formId_mjkzq_query").form("formData");
		$("#gridId_doorControl").grid("option", "postData", formData);
		$("#gridId_doorControl").grid("reload");
	}

	function clear() {
		$("#formId_mjkzq_query").form("clear");
	}
</script>