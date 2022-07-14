<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div style="width: 100%; height: 100%;">
	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="treeId_talkBack" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="queryList">
		</cui:tree>
	</div>

	<div style="margin-left: 26%; height: 100%;">
		<cui:toolbar data="toolbar_talkBackServerDate" ></cui:toolbar>
		<cui:grid id="gridId_talkBackBase" fitStyle="fill" singleselect="true" colModel="gridId_talkBackBase_colModelDate">
			<cui:gridPager gridId="gridId_talkBackBase" />
		</cui:grid>
	</div>
</div>



<cui:dialog id="dialogId_talkBackBase" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者

	var tbdMainIdnty = "";//主机id

	//品牌
	var djfj_brandData = <%=CodeFacade.loadCode2Json("4.20.24")%>;
	//状态
	var djfj_sttsData = <%=CodeFacade.loadCode2Json("4.20.58")%>; 
	
	$.parseDone(function() {
		var url = "${ctx}/talkBackBase/listAll.json?tbdCusNumber=" + cusNumber + "&P_orders=tbd_crte_time,desc";
		$("#gridId_talkBackBase").grid("reload", url);
		$("#treeId_talkBack").tree( "reload", "${ctx}/talkBackServer/seachTreeData.json?cusNumber=" + cusNumber);
		tbdMainIdnty = "";
	});

	var gridId_talkBackBase_colModelDate = [ {
		label : "ID",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "TBD_IDNTY",
		label : "编号",
		align : "center",
	}, {
		name : "TBD_NAME",
		label : "名称",
		align : "center",
		width : 250
	}, {
		name : "TBD_BRAND",
		label : "品牌",
		align : "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':djfj_brandData }
	}, {
		name : "TBD_STTS_INDC",
		label : "状态",
		align : "center",
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':djfj_sttsData }
	}, {
		name : "TBD_AREA",
		label : "所属区域",
		align : "center",
	}, /* {
		name : "TBD_DPRTMNT",
		label : "所属部门",
		align : "center",
	},  */{
		name : "TBD_REMARK",
		label : "备注",
		align : "center",
	} ];

	toolbar_talkBackServerDate = [ {
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
			dialog_width = "700";
			dialog_height = "460";
			url = "${ctx}/talkBackBase/openDialog?type=1";
			break;

		case "btnId_update":
			var selrow = $("#gridId_talkBackBase").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_talkBackBase").grid("getRowData", selrow)
				dialog_width = "700";
				dialog_height = "460";
				url = "${ctx}/talkBackBase/openDialog?type=2&id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_talkBackBase").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_talkBackBase").dialog("open");
		}
	}

	function remove() {
		var selrow = $("#gridId_talkBackBase").grid("option", "selrow");
		if (selrow != null) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				var rowData = $("#gridId_talkBackBase").grid("getRowData", selrow.toString());
				if (confirm) {
					$.ajax({
						url : "${ctx}/talkBackBase/delete.json?id=" + rowData.ID,
						dataType : "json",
						type : "post",
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_talkBackBase").grid("reload");
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

	function queryList(event, id, node) {
		//$.alert(node.name + "=====" + node.id + "======");
		if (node.isParent) {
			$("#gridId_talkBackBase").grid( "reload", "${ctx}/talkBackBase/listAll.json?tbdCusNumber=" + cusNumber + "&P_orders=tbd_crte_time,desc");
			return;
		}

		tbdMainIdnty = node.id;
		var url = "${ctx}/talkBackBase/listAll.json?tseCusNumber=" + cusNumber + "&tbdMainIdnty=" + tbdMainIdnty + "&P_orders=tbd_crte_time,desc";
		$("#gridId_talkBackBase").grid("reload", url);

	}
</script>