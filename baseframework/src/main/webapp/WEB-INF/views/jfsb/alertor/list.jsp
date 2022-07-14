<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">

	<div style="float: left; width: 25%; height: 100%; overflow-y: auto">
		<cui:tree id="alertor_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="query">
		</cui:tree>
	</div>
	<div style="margin-left: 26%; height: 98%; margin: 5px">
		<div>
			<cui:form id="formId_queryInfo">
				<table class="table">
					<tr>
						<th>名称：</th>
						<td>
							<cui:input name="abdName" componentCls="form-control"></cui:input>
						</td>
						<th>类型：</th>
						<td>
							<cui:combobox name="abdTypeIndc" componentCls="form-control" data="abdTypeIndcDate" ></cui:combobox>
						</td>
						<%--<th>所属区域：</th>
						<td>
							<cui:combotree id="comboId_areaId" name="abdAreaId"  componentCls="form-control" url="${ctx}" simpleDataEnable="true" simpleDataIdKey="id" simpleDataPIdKey="pid" keyName="name" showClose="true"></cui:combotree>
						</td>--%>
						<th>状态：</th>
						<td>
							<cui:combobox name="abdSttsIndc" data="abdSttsIndcDate" componentCls="form-control"></cui:combobox>
						</td>
						<td>
							<cui:button label="查询" onClick="query"></cui:button>
							<cui:button label="重置" onClick="clearQuery"></cui:button>
						</td>
					</tr>
				</table>

			</cui:form>

		</div>


		<cui:toolbar id="toolbarId_alertor" data="toolbar_alertorDate"></cui:toolbar>
		<cui:grid id="gridId_alertor" fitStyle="fill" multiselect="true" colModel="gridId_alertor_colModelDate">
			<cui:gridPager gridId="gridId_alertor" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_alertor" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	//报警器类型
	var abdTypeIndcDate = <%=CodeFacade.loadCode4ComboJson("4.20.27", 3, "0")%>;
	//报警器状态
	var abdSttsIndcDate = <%=CodeFacade.loadCode2Json("4.20.55")%>;
	//报警器品牌
	var alertorBrandDate = <%=CodeFacade.loadCode2Json("4.20.36")%>;

	$.parseDone(function() {
		var url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0";
		//$("#comboId_areaId").combotree("tree").tree( "reload", url);
		areaId = '${areaId}';
		url = "${ctx}/alertor/listAll.json?abdCusNumber=" + cusNumber;
		if(areaId!="undefined" && areaId != undefined && areaId != null && areaId != ''){
			url = url + "&abdAreaId=" + areaId;
		}
		url = url + "&P_orders=abd_crte_time,desc"; 
		$("#gridId_alertor").grid("reload", url);
        $("#alertor_regionTree").tree( "reload", "${ctx}/common/areadevice/findForCombotree?cusNumber=" + cusNumber + "&deviceType=0");
	});

	var gridId_alertor_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "ABD_IDNTY",
		label : "编号",
		hidden : true
	}, {
		name : "ABD_NAME",
		label : "名称",
		width : 250
	}, {
		name : "ABD_STTS_INDC",
		label : "状态",
		width : 90,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':abdSttsIndcDate} 
	}, /* {
		name : "ABD_ADDRS",
		label : "报警器地址"
	},  {
		name : "ABD_IP",
		label : "报警器IP"
	}, */ {
		name : "ABD_AREA",
		label : "所属区域"
	},/*  {
		name : "ABD_PRE_NAME",
		label : "报警器名称前缀"
	}, */ {
		name : "ABD_TYPE_INDC",
		label : "类型",
		width : 120,
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':abdTypeIndcDate} 
	},/*  {
		name : "ABD_PORT",
		label : "端口"
	},  {
		name : "ABD_HOST_IDNTY",
		label : "主机编号"
	},*/ {
		name : "ABD_BRAND_INDC",
		label : "品牌",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':alertorBrandDate} 
	}, {
		name : "ABD_REMARK",
		label : "备注"
	} ];

	toolbar_alertorDate = [ {
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
			dialog_width = "860";
			dialog_height = "500";
			url = "${ctx}/alertor/openDialog/save";
			break;

		case "btnId_update":
			var selected = $("#gridId_alertor").grid("option", "selarrrow");
			if (selected.length == 1) {
				var rowData = $("#gridId_alertor").grid("getRowData", selected[0])
				dialog_width = "860";
				dialog_height = "500";
				url = "${ctx}/alertor/openDialog/update?id=" + rowData.ID;
			} else {
				$.messageQueue({ message : "请选择一条要修改的记录", cls : "warning", iframePanel : true, type : "info" });
			}
			break;

		default:
			break;
		}

		if (dialog_width != 0 && dialog_height != 0) {
			$("#dialogId_alertor").dialog({
				width : dialog_width,
				height : dialog_height,
				url : url,
				title : ui.label,
			});
			$("#dialogId_alertor").dialog("open");
		}
	}

	function remove() {
		debugger;
		var selected = $("#gridId_alertor").grid("option", "selarrrow");
		if (selected.length > 0) {
			$.confirm("是否删除选中的记录", "信息确认", function(confirm) {
				if (confirm) {
					$.ajax({
						url : "${ctx}/alertor/delete.json",
						dataType : 'json',
						type : 'post',
						data : JSON.stringify(selected),
						contentType : "application/json",
						success : function(data) {
							if (data.success) {
								$("#gridId_alertor").grid("reload");
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
		var formData = $("#formId_queryInfo").form("formData");
        //获取左侧树菜单中选中节点,单选
        var abdAreaIdArr = $("#alertor_regionTree").tree("getSelectedNodes");
        if( abdAreaIdArr[0] &&  abdAreaIdArr[0].id){
            formData['abdAreaId'] = abdAreaIdArr[0].id;
        }
		$("#gridId_alertor").grid("option", "postData", formData);
		$("#gridId_alertor").grid("reload");
	}

	function clearQuery() {
		$("#formId_queryInfo").form("clear");
	}
	
	/* 区域树下拉点击事件 */
	function onAreaTreeClick_bjq(event, ui) {
		var type = $("#combId_lx").combobox( "getValue");
		if(type!= ""){
			//alert(type);
			if(type == "6"){
				$("#combId_bjq").combobox("option", "disabled", false);
				$("#combId_bjq").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=1");
			}
			if(type == "9"){
				$("#combId_bjq").combobox("option", "disabled", false);
				$("#combId_bjq").combobox( "reload", "${ctx}/common/areadevice/findDeviceList.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id + "&deviceType=2");
			}
			//监舍控件请求数据
			$("#abdAddrs").autocomplete("option","source","${ctx}/common/all/findLcjsh.json?cusNumber=" + cusNumber + "&areaId=" + ui.node.id);
		}else{
			$.messageQueue({ message : "请选择类型", cls : "warning", iframePanel : true, type : "info" });
		}
	}
	//类型下拉点击事件
	function onComboSelect() {
		$("#areaTreeId").combotree("option", "disabled", false);
		$("#combId_bjq").combobox( "clear");
		$("#areaTreeId").combotree("setText","");
		$("#combId_bjq").combobox("option", "disabled", true);
	}
	
	//主机下拉点击事件
	function onHostIdntySelect(event, ui) {
		var name = $("#nameInput").textbox("getText");
		if(name == ""){
			$("#nameInput").textbox("setText", ui.item.text); 	
		}
	}
	
</script>