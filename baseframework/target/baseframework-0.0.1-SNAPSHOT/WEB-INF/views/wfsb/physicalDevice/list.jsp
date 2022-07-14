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
				<label>设施名称:</label>
				<cui:input id="inputId_pdeDeviceName" name="pdeDeviceName" type="text" 
					placeholder=""></cui:input>
			</div>
			<div>
				<label>设施类型:</label>
				<cui:combobox id="comboboxId_pdeTypeIndc" name="pdeTypeIndc" data="pdeTypeIndc_json" 
					emptyText="请选择" ></cui:combobox>
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

<cui:toolbar id="toolbarId_physicalDevice" data="toolbarData_physicalDevice"
	onClick="toolbarOnClick_physicalDevice"></cui:toolbar>
<cui:grid id="gridId_physicalDevice" singleselect="true" shrinkToFit="true" 
	colModel="gridColModel_physicalDevice" fitStyle="fill"
	datatype="json" url="" pager="true"></cui:grid>
<cui:dialog id="dialogId_physicalDeviceManage" iframePanel="true"  autoOpen="false"
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>  
<script>
var pdeTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.34")%>;
var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人
$.parseDone(function(){
	$("#gridId_physicalDevice").grid("reload","${ctx}/wfsb/physicalDevice/searchPhysicalDevice.json?pdeCusNumber="+cusNumber);
});

function search() {
	var postData = {};
	var pdeDeviceName = $('#inputId_pdeDeviceName').val();
	var pdeTypeIndc = $('#comboboxId_pdeTypeIndc').combobox("getValue");

	if (pdeDeviceName != "") {
		postData['pdeDeviceName'] = pdeDeviceName;
	}
	if (pdeTypeIndc != "") {
		postData['pdeTypeIndc'] = pdeTypeIndc;
	}
	if (cusNumber != "") {
		postData['pdeCusNumber'] = cusNumber;
	}
	$('#gridId_physicalDevice').grid('option', 'postData', postData);
	$("#gridId_physicalDevice").grid("reload","${ctx}/wfsb/physicalDevice/searchPhysicalDevice.json");
}

	var toolbarData_physicalDevice = [ {
		
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

	var toolbarOnClick_physicalDevice = function(event, ui) {
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
		$("#dialogId_physicalDeviceManage").dialog({
			width : 300,
			height : 'auto',
			subTitle : '新增',
			url : '${ctx}/wfsb/physicalDevice/new',
		});
		$("#dialogId_physicalDeviceManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_physicalDevice").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_physicalDeviceManage").dialog({
				width : 300,
				height : 'auto',
				subTitle : '修改',
				url : '${ctx}/wfsb/physicalDevice/edit?id=' + selrow.toString(),
			});
			$("#dialogId_physicalDeviceManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_physicalDevice").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/wfsb/physicalDevice/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_physicalDevice").grid("reload","${ctx}/wfsb/physicalDevice/searchPhysicalDevice.json?pdeCusNumber="+cusNumber);
								}else{
									$.message( {
										iframePanel:true,
								        message:data.exception.cause.message,
								        type:"danger"
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
	var gridColModel_physicalDevice = [ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key:true
	}, {
		label : "设施名称",
		align : "center",
		name : "PDE_DEVICE_NAME"
	}, {
		label : "设施类型",
		name : "PDE_TYPE_INDC",
		align : "center",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':pdeTypeIndc_json
		}
	}, {
		label : "安装位置",
		align : "center",
		name : "PDE_LOCATION"
	}, {
		label : "正常总数",
		align : "center",
		name : "PDE_NORMAL_COUNT"
	}, {
		label : "异常总数",
		align : "center",
		name : "PDE_ABNORMAL_COUNT"
	}, {
		label : "数量单位",
		align : "center",
		name : "PDE_UNIT"
	}/* , {
		label : "创建时间",
		name : "PDE_CRTE_TIME"
	} */, {
		label : "更新时间",
		align : "center",
		name : "PDE_UPDT_TIME"
	} ];
</script>