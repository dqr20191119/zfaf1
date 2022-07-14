<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="broadcast_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="gb_query">
		</cui:tree>
	</div>
	<div style="margin-left: 26%; height: 98%; margin: 5px">
		<cui:form id="formId_gb_query">
			<table class="table">
				<tr>
					<th>广播名称：</th>
					<td>
						<cui:input id="inputId_bbdName" name="bbdName" componentCls="form-control"></cui:input>
					</td>
					<th>状态：</th>
					<td>
						<cui:combobox id="comboboxId_bbdSttsIndc" name="bbdSttsIndc" componentCls="form-control" data="gb_sttsData"></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="gb_query"></cui:button>
						<cui:button label="重置" onClick="gb_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar id="toolbarId_broadcast" data="toolbar_broadcastDate"></cui:toolbar>
		<cui:grid id="gridId_broadcast" fitStyle="fill" multiselect="true" colModel="gridId_broadcast_colModelDate">
			<cui:gridPager gridId="gridId_broadcast" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_broadcast" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var gb_sttsData = <%=CodeFacade.loadCode2Json("4.20.12")%>;//状态
	var gb_brandData = <%=CodeFacade.loadCode2Json("4.20.35")%>;//品牌
	
	$.parseDone(function() {
		/* var url = "${ctx}/broadcast/listAll.json?bbdCusNumber=" + cusNumber + "&P_orders=bbd_crte_time,desc";
		$("#gridId_broadcast").grid("reload", url); */
		var areaId='${areaId}';
		
		if(areaId){
			$("#gridId_broadcast").grid("reload","${ctx}/broadcast/listAll.json?bbdCusNumber=" + cusNumber + "&bbdAreaId=" + areaId + "&P_orders=bbd_crte_time,desc");
		}else{
			$("#gridId_broadcast").grid("reload","${ctx}/broadcast/listAll.json?bbdCusNumber=" + cusNumber + "&P_orders=bbd_crte_time,desc");
		}
        $("#broadcast_regionTree").tree( "reload", "${ctx}/common/areadevice/findForCombotree?cusNumber=" + cusNumber + "&deviceType=0");

	});
	
	var gridId_broadcast_colModelDate = [ {
		label : "ID",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "BBD_IDNTY",
		label : "编号",
		align : "center",
	}, {
		name : "BBD_NAME",
		label : "名称",
		align : "center",
		width : 250
	}, {
		name : "BBD_BRAND",
		label : "品牌",
		align : "center",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':gb_brandData } 
	}, {
		name : "BBD_MODEL",
		label : "型号",
		align : "center",
	}, {
		name : "BBD_IP_ADDRS",
		label : "网络地址",
		align : "center",
	}, /* {
		name : "BBD_DPRTMNT",
		label : "所属部门",
		align : "center",
	}, */ {
		name : "BBD_AREA",
		label : "所属区域",
		align : "center",
	},/*  {
		name : "BBD_ADDRS",
		label : "安装地址",
		align : "center",
	}, */ {
		name : "BBD_STTS_INDC",
		label : "状态",
		align : "center",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':gb_sttsData } 
	} ];

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
			dialog_width = "640";
			dialog_height = "360";
			url = "${ctx}/broadcast/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_broadcast").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_broadcast").grid("getRowData", selected[0])
				dialog_width = "700";
				dialog_height = "550";
				url = "${ctx}/broadcast/openDialog/update?id=" + rowData.ID;
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
		console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/broadcast/delete.json",
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
		var bbdName = $('#inputId_bbdName').val();
		//var bbdAreaId=$('#bbdAreaId').combotree("getValue");
		var bbdSttsIndc = $('#comboboxId_bbdSttsIndc').combobox("getValue");
        //获取左侧树菜单中选中节点,单选
        var bbdAreaIdArr = $("#broadcast_regionTree").tree("getSelectedNodes");

		if (bbdName != "") {
			postData['bbdName'] = bbdName;
		}
		if (bbdSttsIndc != "") {
			postData['bbdSttsIndc'] = bbdSttsIndc;
		}
        if(bbdAreaIdArr[0] && bbdAreaIdArr[0].id){
            postData['bbdAreaId'] = bbdAreaIdArr[0].id;
        }

		$('#gridId_broadcast').grid('option', 'postData', postData);
		$('#gridId_broadcast').grid('reload',"${ctx}/broadcast/listAll.json?bbdCusNumber="+ cusNumber);
	}

	function gb_clear() {
		$("#formId_gb_query").form("clear");
	}
</script>