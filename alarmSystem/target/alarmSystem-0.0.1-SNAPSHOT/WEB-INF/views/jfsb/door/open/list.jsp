<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<cui:dialog id="dialogId_door_mjgl" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
 <div style="width: 100%; height: 100%;font-size: 30px">
		<cui:toolbar data="toolbar_doorOpenDate"></cui:toolbar>
		<cui:grid id="gridId_door_open" fitStyle="fill" rownumbers="true" multiselect="true" colModel="gridId_door_open_colModelDate">
			<cui:gridPager gridId="gridId_door_open"  />
		</cui:grid>
</div>


<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;
	$.parseDone(function() {
		var url = "${ctx}/dooropen/listAll.json?jyid=" + cusNumber;
		$("#gridId_door_open").grid("reload", url);
	});

	var gridId_door_open_colModelDate = [
		 {
				label : "ID",
				name : "ID",
				key : true,
				hidden : true
			},
		{name:"JYID",label:"监狱名称",
			align:"center",
			width:110,formatter : "convertCode",  
			revertCode : true, 
			formatoptions : { 'data':combobox_jy } 
		},
		{
		name : "OPEN_PASSWORD",
		label : "门禁开启密码",
		width : 110,
		align :"center"
	}];

	var toolbar_doorOpenDate = [ {
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
	} ];

	function openDailog(event, ui) {
		var dialog_width = 0;
		var dialog_height = 0;
		var dialog_name;
		var url;

		switch (ui.id) {

		case "btnId_add":
			dialog_width = "700";
			dialog_height = "550";
			url = "${ctx}/dooropen/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_door_open").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_door_open").grid("getRowData", selected[0]);
				dialog_width = "700";
				dialog_height = "550";
				url = "${ctx}/dooropen/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_door_mjgl").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_door_mjgl").dialog("open");
		}
	}

	function remove() {
		var selected = $("#gridId_door_open").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/dooropen/delete.json",
						dataType : 'json',
						type : 'post',
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_door_open").grid("reload");
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
	function mj_query() {
		var formData = $("#formId_mj_query").form("formData");
		$("#gridId_door_open").grid("option", "postData", formData);
		$("#gridId_door_open").grid("reload");
	}

	function mj_clear() {
		$("#formId_mj_query").form("reset");
	}
</script>