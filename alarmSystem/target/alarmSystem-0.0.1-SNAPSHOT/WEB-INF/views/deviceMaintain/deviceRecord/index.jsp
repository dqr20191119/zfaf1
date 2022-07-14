<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_wxjl_query">
			<table class="table">
				<tr>
					<th>设备类型：</th>
					<td>
						<cui:combobox name="dmrDeviceType" data="deviceTypeDate"></cui:combobox>
					</td>
					<th>设备名称：</th>
					<td>
						<cui:input name="dmrDeviceName"></cui:input>
					</td>
					<th>维修人：</th>
					<td>
						<cui:input name="dmrFaultMaintainer"></cui:input>
					</td>
				</tr>
				<tr>
					<th>维修时间：</th>
					<td>
						<cui:datepicker name="dmrFaultMaintainTime" dateFormat="yyyy-MM-dd"></cui:datepicker>
					</td>
					<td>
						<cui:button   label="查询" onClick="query_wxjl"></cui:button>
						<cui:button   label="重置" onClick="reset_wxjl"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_record" data="toolbar_recordDate"></cui:toolbar>
		<cui:grid id="gridId_record" fitStyle="fill" multiselect="true" colModel="gridId_record_colModelDate">
			<cui:gridPager gridId="gridId_record" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_record" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true" ></cui:dialog>
	<cui:dialog id="dialogId_rwxf" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true" ></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	/*  
	 摄像头	1	 
	 对讲分机	2	 
	 报警器	3 
	 门禁		4 
	 广播		5	 
	 对讲主机	6	 
	 高压电网	7 */
	var deviceTypeDate = <%=CodeFacade.loadCode2Json("4.20.57")%>;

	$.parseDone(function() {
		var url = "${ctx}/deviceMaintain/record/listAll.json?dmrCusNumber=" + cusNumber + "&P_orders=dmr_crte_time,desc";
		$("#gridId_record").grid("reload", url);

	});

	var gridId_record_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "DMR_DEVICE_TYPE",
		label : "设备类型",
		align : "center",
		width : 80,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':deviceTypeDate } 
	}, {
		name : "DMR_DEVICE_NAME",
		label : "设备名称",
		align : "center",
		width : 148,
	}, {
		name : "DMR_FAULT_CONTENT",
		label : "维修情况",
		align : "center",
		width : 140,
	}, {
		name : "DMR_FAULT_MAINTAINER",
		label : "维修人",
		align : "center",
		width : 70,
	}, {
		name : "DMR_FAULT_MAINTAIN_TIME",
		label : "维修时间",
		align : "center",
		width : 90
	}, {
		name : "PBD_POLICE_NAME",
		label : "记录人",
		align : "center",
		width : 70
	}, {
		name : "DMR_CRTE_TIME",
		label : "记录时间",
		align : "center",
		width : 140
	}];

	toolbar_recordDate = [ {
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
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {

		case "btnId_add":
			dialog_width = "1000";
			dialog_height = "600";
			url = "${ctx}/deviceMaintain/record/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_record").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_record").grid("getRowData", selected[0])
				dialog_width = "550";
				dialog_height = "300";
				url = "${ctx}/deviceMaintain/record/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_record").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_record").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_record").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/deviceMaintain/record/deleteByIds.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_record").grid("reload");
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

	function query_wxjl() {
		var formData = $("#formId_wxjl_query").form("formData");
		$("#gridId_record").grid("option", "postData", formData);
		$("#gridId_record").grid("reload");
	}

	function reset_wxjl() {
		$("#formId_wxjl_query").form("reset");
	}
</script>