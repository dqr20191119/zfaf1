<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<cui:dialog id="dialogId_door_mjgl" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
<div style="width: 100%; height: 100%;">

	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="door_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="findByRegion">
		</cui:tree>
	</div>

	<div style="margin-left: 26%; height: 100%;">
		<cui:form id="formId_mj_query">
			<table class="table">
				<tr>
					<th>门禁名称：</th>
					<td>
						<cui:input name="dcbName" componentCls="form-control"></cui:input>
					</td>
					<th>品牌：</th>
					<td>
						<cui:combobox name="dcbBrandIndc" data="doorBrandDate" componentCls="form-control" ></cui:combobox>
					</td>
					<th>状态：</th>
					<td>
						<cui:combobox name="dcbSttsIndc" data="doorSttsDate" componentCls="form-control" ></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="mj_query"></cui:button>
						<cui:button label="重置" onClick="mj_clear"></cui:button>
					</td>
				</tr>
			</table>

		</cui:form>
		<cui:toolbar data="toolbar_doorDate"></cui:toolbar>
		<cui:grid id="gridId_door" fitStyle="fill" multiselect="true" colModel="gridId_door_colModelDate">
			<cui:gridPager gridId="gridId_door" />
		</cui:grid>
	</div>

</div>

<script>
	function findByRegion(event, id, node) {
		var postData = {
			dcbAreaId : node.id,
		}
		$('#gridId_door').grid('option', 'postData', postData);
		$('#gridId_door').grid('reload');
	}

	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	//门禁类型
	var doorTypeDate =  <%=CodeFacade.loadCode2Json("4.20.21")%>;
	//门禁品牌
	var doorBrandDate =  <%=CodeFacade.loadCode2Json("4.20.22")%>;
	//门禁状态
	var doorSttsDate = <%=CodeFacade.loadCode2Json("4.20.23")%>;
	//是否被控制
	var whetherCtrlDate = <%=CodeFacade.loadCode2Json("4.0.1")%>;
	//是否进出监门-新增字段
	var isAbData = [{value:'1',text:'进'},{value:'2',text:'出'}];

	$.parseDone(function() {
		var url = "${ctx}/door/listAll.json?dcbCusNumber=" + cusNumber + "&P_orders=dcb_crte_time,desc"
		$("#gridId_door").grid("reload", url);
		$("#door_regionTree").tree( "reload", "${ctx}/common/areadevice/findForCombotree?cusNumber=" + cusNumber + "&deviceType=0");
	});

	var gridId_door_colModelDate = [ {
		label : "ID",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "DCB_IDNTY",
		label : "编号",
		width : 80
	},/*  {
		name : "DCB_CHANNEL_IDNTY",
		label : "门通道号",
	}, */ {
		name : "DCB_NAME",
		label : "名称",
		width : 250
	},/* {
		name : "DCB_TYPE_INDC",
		label : "类型",
		width : 90,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':doorTypeDate } 
	},  {
		name : "DCB_ADDRS",
		label : "门禁地址",
	}, */ {
		name : "DCB_BRAND_INDC",
		label : "品牌",
		width : 90,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':doorBrandDate } 
	}, {
		name : "DCB_STTS_INDC",
		label : "状态",
		width : 90,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':doorSttsDate } 
	}, {
		name : "DCB_CAMERA_NAME",
		label : "关联摄像机",
		formatter : function(cellvalue, options, rawObject) {
					return cellvalue ? "已关联" : "未关联";	
		}
	}, {
		name : "DCB_AREA",
		label : "所属区域"
	}/* , {
		name : "DCB_CTRL_IDNTY",
		label : "门禁控制器编号"
	} */];

	var toolbar_doorDate = [ {
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
			dialog_height = "550";
			url = "${ctx}/door/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_door").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_door").grid("getRowData", selected[0])
				dialog_width = "700";
				dialog_height = "550";
				url = "${ctx}/door/openDialog/update?id=" + rowData.ID;
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
		var selected = $("#gridId_door").grid("option", "selarrrow");
		//console.info(JSON.stringify(selected));
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/door/delete.json",
						dataType : 'json',
						type : 'post',
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_door").grid("reload");
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
		$("#gridId_door").grid("option", "postData", formData);
		$("#gridId_door").grid("reload");
	}

	function mj_clear() {
		$("#formId_mj_query").form("reset");
	}
</script>