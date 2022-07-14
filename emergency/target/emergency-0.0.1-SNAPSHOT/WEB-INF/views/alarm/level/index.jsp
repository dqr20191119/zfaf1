<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:toolbar data="toolbar_alarmTypeAndLevDate"></cui:toolbar>
		<cui:grid id="gridId_alarmTypeAndLev" fitStyle="fill" multiselect="true" colModel="gridId_alarmTypeAndLev_colModelDate">
			<cui:gridPager gridId="gridId_alarmTypeAndLev" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_alarmTypeAndLev" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		var url = "${ctx}/alarmTypeAndLev/listAll.json?altCusNumber=" + cusNumber;
		$("#gridId_alarmTypeAndLev").grid("reload", url);
	});
	//报警等级
	var levelData = <%=CodeFacade.loadCode2Json("4.20.25")%>;
	//报警类型
	var typeData = <%=CodeFacade.loadCode2Json("4.20.27")%>;

	var gridId_alarmTypeAndLev_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "ALT_TYPE_ID",
		label : "报警类型",
		align : "center",
		formatter : "convertCode",
        revertCode : true,
	    formatoptions : { 'data':typeData } 
	}, {
		name : "ALT_LEVEL",
		label : "报警等级",
		align : "center",
		cellattr : function(o) {
		    if ( o.rawObject.ALT_LEVEL == "1"  ) {
	        	return 'style="color: #FF3030"';
		    }
		    if ( o.rawObject.ALT_LEVEL == "2"  ) {
	        	return 'style="color: #FFC125"';
		    }
		    if ( o.rawObject.ALT_LEVEL == "3"  ) {
	        	return 'style="color: #6495ED"';
		    }
		},
		formatter : "convertCode",
        revertCode : true,
	    formatoptions : { 'data':levelData } 
	}];

	toolbar_alarmTypeAndLevDate = [ {
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
			dialog_width = "360";
			dialog_height = "210";
			url = "${ctx}/alarmTypeAndLev/openDialog/add";
			break;
		case "btnId_update":
			var selected = $("#gridId_alarmTypeAndLev").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_alarmTypeAndLev").grid("getRowData", selected[0])
				dialog_width = "360";
				dialog_height = "210";
				url = "${ctx}/alarmTypeAndLev/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_alarmTypeAndLev").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_alarmTypeAndLev").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_alarmTypeAndLev").grid("option", "selarrrow");
		console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/alarmTypeAndLev/delete.json",
						dataType : "json",
						type : "post",
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_alarmTypeAndLev").grid("reload");
								$.messageQueue({ message : "删除记录成功", cls : "success", iframePanel : true, type : "info" });
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
</script>