<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="float: left; width: 20%; height: 100%; overflow-y: auto">
		<cui:tree id="talkBack_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="djzj_query">
		</cui:tree>
	</div>
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_djzj_query">
			<table class="table">
				<tr>
					<th>主机名称：</th>
					<td>
						<cui:input name="tseName" componentCls="form-control"></cui:input>
					</td>
					<%--<th>所属部门：</th>--%>
					<%--<td>--%>
						<%--<cui:combobox id="comboId_dprtmnt_" name="tseDprtmntId" componentCls="form-control"></cui:combobox>--%>
					<%--</td>--%>
					<%--<th>所属区域：</th>
					<td>
						<cui:combotree id="areaTreeId_" name="tseAreaId" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name"></cui:combotree>
					</td>--%>
					<th>状态：</th>
					<td>
						<cui:combobox name="tseSttsIndc" componentCls="form-control" data="djzj_sttsData"></cui:combobox>
					</td>
					<th>上级主机：</th>
					<td>
						<cui:combobox id="comboId_parent" name="tseParentId" componentCls="form-control"></cui:combobox>
					</td>
                    <th></th>
					<td>
						<cui:button label="查询" onClick="djzj_query"></cui:button>
						<cui:button label="重置" onClick="djzj_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:toolbar data="toolbar_talkBackServerDate"></cui:toolbar>
		<cui:grid id="gridId_talkBackServer" fitStyle="fill" singleselect="true" colModel="gridId_talkBackServer_colModelDate">
			<cui:gridPager gridId="gridId_talkBackServer" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_talkBackServer" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		//$("#comboId_dprtmnt_").combobox( "reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox.json?cusNumber=" + cusNumber);
		$("#areaTreeId_").combotree("tree").tree( "reload", "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0");
		var url = "${ctx}/talkBackServer/listAll.json?tseCusNumber=" + cusNumber + "&P_orders=tse_crte_time,desc";
		$("#gridId_talkBackServer").grid("reload", url);
        $("#talkBack_regionTree").tree( "reload", "${ctx}/common/areadevice/findForCombotree?cusNumber=" + cusNumber + "&deviceType=0");
		$("#comboId_parent").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&deviceType=6");
	});
	//品牌
	var djzj_brandData = <%=CodeFacade.loadCode2Json("4.20.24")%>;
	//状态
	var djzj_sttsData = <%=CodeFacade.loadCode2Json("4.20.58")%>; 
	var gridId_talkBackServer_colModelDate = [ {
		label : "ID",
		name : "ID",
		key : true,
		hidden : true
	}, {
		name : "TSE_IDNTY",
		label : "编号",
		align : "center",
	}, {
		name : "TSE_NAME",
		label : "名称",
		align : "center",
		width : 250
	}, {
		name : "TSE_BRAND",
		label : "品牌",
		align : "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':djzj_brandData }
	}, {
		name : "TSE_IP",
		label : "IP",
		align : "center",
	}, {
		name : "TSE_PC_IP",
		label : "电脑IP",
		align : "center",
	}, {
		name : "TSE_AREA",
		label : "所属区域",
		align : "center",
	},/*  {
		name : "TSE_DPRTMNT",
		label : "所属部门",
		align : "center",
	}, */ {
		name : "TSE_STTS_INDC",
		label : "状态",
		align : "center",
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':djzj_sttsData }
	},  {
		name : "TSE_PARENT_NAME",
		label : "上级主机",
	}/* , {
		name : "TSE_REMARK",
		label : "备注",
		align : "center",
	}  */];
	
	toolbar_talkBackServerDate = [{
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
			url = "${ctx}/talkBackServer/openDialog/save";
			break;

		case "btnId_update":
			var selrow = $("#gridId_talkBackServer").grid("option", "selrow");
			if (selrow != null) {
				var rowData = $("#gridId_talkBackServer").grid("getRowData", selrow)
				dialog_width = "700";
				dialog_height = "460";
				url = "${ctx}/talkBackServer/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;
		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_talkBackServer").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});

			$("#dialogId_talkBackServer").dialog("open");
		}
	}

	function remove() {
		var selrow = $("#gridId_talkBackServer").grid("option", "selrow");
		if (selrow != null) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				var rowData = $("#gridId_talkBackServer").grid("getRowData", selrow.toString());
				if (confirm) {
					$.ajax({
						url : "${ctx}/talkBackServer/delete.json?id=" + rowData.ID,
						dataType : "json",
						type : "post",
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_talkBackServer").grid("reload");
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
	function djzj_query() {
		var formData = $("#formId_djzj_query").form("formData");
        //获取左侧树菜单中选中节点,单选
        var tseAreaIdArr = $("#talkBack_regionTree").tree("getSelectedNodes");
        if( tseAreaIdArr[0] &&  tseAreaIdArr[0].id){
            formData['tseAreaId'] = tseAreaIdArr[0].id;
        }
		$("#gridId_talkBackServer").grid("option", "postData", formData);
		$("#gridId_talkBackServer").grid("reload");
		//关闭当前弹窗		
		$("#dialogId_talkBackServer").dialog("close");
	}

	function djzj_clear() {
		$("#formId_djzj_query").form("reset");
	}
</script>