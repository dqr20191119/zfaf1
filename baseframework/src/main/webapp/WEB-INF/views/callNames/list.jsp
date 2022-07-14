<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:toolbar id="toolbarId_callName" data="toolbar_callNameDate"></cui:toolbar>
		<cui:grid id="gridId_callName" fitStyle="fill" multiselect="true" colModel="gridId_callName_colModelDate">
			<cui:gridPager gridId="gridId_callName" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_callName" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;

	$.parseDone(function() {
		var url = "${ctx}/callNames/listAll.json?cnrIsDone=0&cnrCusNumber=" + cusNumber ;
		$("#gridId_callName").grid("reload", url);
	});
	var gridId_callName_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "CNR_CUS_NUMBER",
		label : "监狱",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : combobox_jy
		}
	}, {
		name : "CNR_START_TIME",
		label : "点名发起时间"
	}, {
		name : "CNR_TIME_LAG",
		label : "点名时长（分钟）"
	}/* , {
		name : "CNR_END_TIME",
		label : "点名结束时间",
	}, {
		name : "CNR_PRISONER_SUM",
		label : "罪犯总数"
	}, {
		name : "CNR_CALL_SUM",
		label : "已点到"
	} */ ];

	toolbar_callNameDate = [ {
		"type" : "button",
		"id" : "btnId_add",
		"label" : "发起",
		"onClick" : "openDailog"
	}, {
		"type" : "button",
		"id" : "btnId_end",
		"label" : "结束",
		"onClick" : "endIngRollcall"
	}, {
		"type" : "button",
		"id" : "btnId_check",
		"label" : "查看",
		"onClick" : "openDailog"
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {
		case "btnId_add":
			dialog_width = "350";
			dialog_height = "150";
			url = "${ctx}/callNames/openDialog/add";
			break;
		case "btnId_check":
			var selected = $("#gridId_callName").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_callName").grid("getRowData", selected[0]);
				dialog_width = "1200";
				dialog_height = "800";
				url = "${ctx}/callNames/openDialog/index?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要查看的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_callName").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_callName").dialog("open");
		}
	}

	function endIngRollcall() {
		var selected = $("#gridId_callName").grid("option", "selarrrow");
		if (selected.length == 1) {
			var rowData = $("#gridId_callName").grid("getRowData", selected[0]);
			$.ajax({
				type : "post",
				url : "${ctx}/callNames/endIngRollcall.json?id=" + rowData.ID,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						saveEndRollcallList(rowData.ID);
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		} else {
			$.messageQueue({ message : "请选择一条记录", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	
	function saveEndRollcallList(rollcallId) {
		var data = {};
		data["rollcallId"] = rollcallId;
		var url = jsConst.basePath + "/callNames/saveEndRollcallList.json";
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					if (!isEmpty(data.msg)){
						$("#gridId_callName").grid("reload");
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						alert("点名已结束，" + data.msg);
					}
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
</script>