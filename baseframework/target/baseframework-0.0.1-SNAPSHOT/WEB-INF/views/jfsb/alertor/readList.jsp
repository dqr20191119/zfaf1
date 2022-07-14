<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<div>
			<cui:form id="formId_queryInfo">
				<table class="table">
					<tr>
						<th>报警器名称：</th>
						<td>
							<cui:input name="abdName" componentCls="form-control"></cui:input>
						</td>
						<th>报警器类型：</th>
						<td>
							<cui:combobox name="abdTypeIndc" componentCls="form-control" data="abdTypeIndcDate" ></cui:combobox>
						</td>
						<th>报警器状态：</th>
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

		<cui:grid id="gridId_alertor" fitStyle="fill" multiselect="true" colModel="gridId_alertor_colModelDate">
			<cui:gridPager gridId="gridId_alertor" />
		</cui:grid>
	</div>
	
	<cui:dialog id="dialogId_alertor" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>

<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;    //监狱号
	//报警器类型
	var abdTypeIndcDate = <%=CodeFacade.loadCode4ComboJson("4.20.27", 3, "0")%>;
	//报警器状态
	var abdSttsIndcDate = <%=CodeFacade.loadCode2Json("4.20.55")%>;
	//报警器品牌
	var alertorBrandDate = <%=CodeFacade.loadCode2Json("4.20.36")%>;

	$.parseDone(function() {
		
		var url = "${ctx}/common/areadevice/findForCombotree.json?cusNumber=" + cusNumber + "&deviceType=0";
		
		areaId = '${areaId}';
		url = "${ctx}/alertor/listAll.json?abdCusNumber=" + cusNumber;
		
		if(areaId != "undefined" && areaId != undefined && areaId != null && areaId != '') {
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
		width : 250,
		align : "center"
	}, {
		name : "ABD_STTS_INDC",
		label : "状态",
		width : 90,
		align : "center",
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
		label : "所属区域",
		align : "center"
	},/*  {
		name : "ABD_PRE_NAME",
		label : "报警器名称前缀"
	}, */ {
		name : "ABD_TYPE_INDC",
		label : "类型",
		width : 120,
		align : "center",
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
		align : "center",
		formatter : "convertCode",
        revertCode : true,
        formatoptions : { 'data':alertorBrandDate} 
	}, {
		name : "ABD_REMARK",
		label : "备注",
		align : "center"
	} ];

	function query() {
		
		var formData = $("#formId_queryInfo").form("formData");
		$("#gridId_alertor").grid("option", "postData", formData);
		$("#gridId_alertor").grid("reload");
	}

	function clearQuery() {
		
		$("#formId_queryInfo").form("clear");
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