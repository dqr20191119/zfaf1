<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:toolbar data="toolbar_prisonPathDate"></cui:toolbar>
		<cui:grid id="gridId_prisonPath" fitStyle="fill" multiselect="true" colModel="gridId_prisonPath_colModelDate">
			<cui:gridPager gridId="gridId_prisonPath" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_prisonPath" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		var url = "${ctx}/prisonPath/listAll.json?ppiCusNumber=" + cusNumber + "&P_orders=ppi_crte_time,desc";
		$("#gridId_prisonPath").grid("reload", url);
	});

	var gridId_prisonPath_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "PPI_PATH_NAME",
		label : "路线名称"
	},{
		name : "PPI_PATH_TYPE",
		label : "路线类别",
		formatter: "convertCode",
		revertCode: true,
		formatoptions: {'data': pathType}
	}, {
		name : "PPI_DPRTMNT",
		label : "所属部门"
	}, {
		name : "PPI_START_AREA",
		label : "起始区域"
	}, {
		name : "PPI_END_AREA",
		label : "结束区域"
	}, {
		name : "PPI_REMARK",
		label : "备注"
	} ];

	toolbar_prisonPathDate = [ {
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
	var pathType=[{text: "零星流动", value: "0"}, {text: "外来人车", value: "1"}];
	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;
		switch (ui.id) {
		case "btnId_add":
			dialog_width = "1200";
			dialog_height = "600";
			url = "${ctx}/prisonPath/openDialog/add";
			break;
		case "btnId_update":
			var selected = $("#gridId_prisonPath").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_prisonPath").grid("getRowData", selected[0])
				dialog_width = "1200";
				dialog_height = "600";
				url = "${ctx}/prisonPath/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({
					message : "请选择一条要修改的记录",
					cls : "warning",
					iframePanel : true,
					type : "info"
				});
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_prisonPath").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_prisonPath").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_prisonPath").grid("option", "selarrrow");
		console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/prisonPath/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_prisonPath").grid("reload");
								$.messageQueue({
									message : "删除记录成功",
									cls : "success",
									iframePanel : true,
									type : "info"
								});
							} else {
								$.messageQueue({
									message : data.msg,
									cls : "warning",
									iframePanel : true,
									type : "info"
								});
							}
						}
					});
				}
			});
		} else {
			$.messageQueue({
				message : "请选择要删除的记录",
				cls : "warning",
				iframePanel : true,
				type : "info"
			});
		}
	}
</script>