<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->
<div class="globalLoginLayout clearfix">

	<cui:form id="queryForm">

		<div class="globalLoginLayout">
			<div>
				<label>名称:</label>
				<cui:input id="inputId_pnbName" name="pnbName" type="text"
					placeholder=""></cui:input>
			</div>

			<div class="last">
				<cui:button cls="cyanbtn" id="s_searchButton" label="查询"
					onClick="search" componentCls="coral-btn-blue" />
				<cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button>
			</div>
		</div>

	</cui:form>
</div>
<!-- 自定义查询结束 -->

<cui:toolbar id="toolbarId_powerNetwork" data="toolbarData_powerNetwork"
	onClick="toolbarOnClick_powerNetwork"></cui:toolbar>
<cui:grid id="gridId_powerNetwork" singleselect="true" shrinkToFit="false"
	colModel="gridColModel_powerNetwork" fitStyle="fill" datatype="json" url=""
	pager="true"></cui:grid>
<cui:dialog id="dialogId_powerNetworkManage" autoOpen="false"
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
	var pnbBrand_json =<%=CodeFacade.loadCode2Json("4.20.40")%>;
	var pnbSttsIndc_json=[{"value":'0',"text":"正常"},{"value":'1',"text":"故障"}];
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE //监狱编号
	var userId = jsConst.USER_ID //登录人

	$.parseDone(function() {
		//$("#gridId_powerNetwork").grid("option","url","${ctx}/jfsb/powerNetwork/searchPowerNetwork.json?pnbCusNumber="+cusNumber);
		$("#gridId_powerNetwork").grid("reload","${ctx}/jfsb/powerNetwork/searchPowerNetwork.json?pnbCusNumber="+ cusNumber);
		
	});
	function search() {
		var postData = {};
		var pnbName = $('#inputId_pnbName').val();

		if (pnbName != "") {
			postData['pnbName'] = pnbName;
		}

		$('#gridId_powerNetwork').grid('option', 'postData', postData);
		$('#gridId_powerNetwork').grid('reload',"${ctx}/jfsb/powerNetwork/searchPowerNetwork.json?pnbCusNumber="+ cusNumber);
	}

	var toolbarData_powerNetwork = [ {

		"id" : "create",
		"label" : "新增",
		"disabled" : "false",
		"type" : "button",
		"cls" : "",
		"icon" : ""
	}, {
		"id" : "update",
		"label" : "修改",
		"disabled" : "disable",
		"type" : "button",
		"cls" : "",
		"icon" : ""
	}, {
		"id" : "delete",
		"label" : "删除",
		"disabled" : "false",
		"type" : "button",
		"cls" : "",
		"icon" : ""

	}];

	var toolbarOnClick_powerNetwork = function(event, ui) {
		var id = ui.id;
		if (id == 'create') {
			f_add();
		} else if (id == 'update') {
			f_modify();
		} else if (id == 'delete') {
			f_delete();
		} 
	}
	function f_add() {
		$("#dialogId_powerNetworkManage").dialog({
			width : 600,
			height : 'auto',
			subTitle : '新增',
			url : '${ctx}/jfsb/powerNetwork/new',
		});
		$("#dialogId_powerNetworkManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_powerNetwork").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_powerNetworkManage").dialog({
				width : 600,
				height : 'auto',
				subTitle : '修改',
				url : '${ctx}/jfsb/powerNetwork/edit?id=' + selrow.toString(),
			});
			$("#dialogId_powerNetworkManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_powerNetwork").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/jfsb/powerNetwork/destory?id="
									+ selrow.toString(),
							success : function(data) {
								if (data.exception == undefined) {
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_powerNetwork").grid("reload");
								} else {
									$.message({
										iframePanel:true,
										message : data.exception.cause.message,
										type : "danger"
									});
								}

							}
						});
					}
					if (sure == false) {
						console.log('cancel');
					}
				}
			});
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	var gridColModel_powerNetwork = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key : true
	}, {
		label : "电网名称",
		name : "PNB_NAME",
		align: "center",
		width : 300
	}, /* {
		label : "电网品牌",
		align: "center",
		name : "PNB_BRAND",
		width : 100,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : pnbBrand_json
		}
	}, {
		label : "电网IP",
		align: "center",
		width : 100,
		name : "PNB_IP"
	},{
		label : "端口",
		align: "center",
		width : 100,
		name : "PNB_PORT"
	}, */ {
		label : "所属区域",
		align: "center",
		width : 200,
		name : "PNB_AREA_NAME"
	}, /* {
		label : "设备安装地址",
		align: "center",
		width : 200,
		name : "PNB_ADDRS"
	}, {
		label : "排序序号",
		align: "center",
		width : 100,
		name : "PNB_ORDER_ID"
	}, */ {
		label : "A相电压",
		align: "center",
		width : 100,
		name : "PNB_A_BOX_VOLTAGE"
	}, {
		label : "B相电压",
		align: "center",
		width : 100,
		name : "PNB_B_BOX_VOLTAGE"
	},/* {
		label : "电源电压(V)",
		align: "center",
		width : 100,
		name : "PNB_POWER_SOURCE_VOLTAGE"
	}, */{
		label : "A相电流",
		align: "center",
		width : 100,
		name : "PNB_A_BOX_POWER_FLOW"
	}, {
		label : "B相电流",
		align: "center",
		width : 100,
		name : "PNB_B_BOX_POWER_FLOW"
	}/* ,{
		label : "电源电流(A)",
		align: "center",
		width : 100,
		name : "PNB_POWER_SOURCE_FLOW"
	},{
		label : "状态",
		align: "center",
		name : "PNB_STTS_INDC",
		width : 100,
		formatter : 'convertCode',
		revertCode : true,
		formatoptions : {
			'dataStructure' : 'list',
			'data' : pnbSttsIndc_json
		}
	} */];
</script>