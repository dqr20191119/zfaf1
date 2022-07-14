<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 自定义查询开始 -->
<div class="globalLoginLayout clearfix">

	<cui:form id="queryForm">

		<div class="globalLoginLayout">
			<div id="div_pdnCusNumber">
				<label>机构编号:</label>
				<cui:combobox id="comboboxId_pdnCusNumber" name="pdnCusNumber"  emptyText="请选择"  data="pdnCusNumber_json"></cui:combobox>
			</div>
			<div>
				<label>设施名称:</label>
				<cui:input id="inputId_pdnDeviceName" name="pdnDeviceName" type="text" 
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

<cui:toolbar id="toolbarId_physicalDeviceName" data="toolbarData_physicalDeviceName"
	onClick="toolbarOnClick_physicalDeviceName"></cui:toolbar>
<cui:grid id="gridId_physicalDeviceName" singleselect="true" shrinkToFit="true" 
	colModel="gridColModel_physicalDeviceName" fitStyle="fill"
	datatype="json" url="${ctx}/wfsb/physicalDeviceName/searchPhysicalDeviceName.json" pager="true"></cui:grid>
<cui:dialog id="dialogId_physicalDeviceNameManage" autoOpen="false" iframePanel="true" 
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人
	var userLevel = jsConst.USER_LEVEL; //用户等级
	
	var pdnCusNumber_json=[{'value':'0','text':'通用'},{'value':jsConst.ORG_CODE,'text':'本监狱'}];
	
	$.parseDone(function() {
		if(userLevel == 1){//省局
			$("#div_pdnCusNumber").hide();
		}
	});
	
	function search() {
		var postData = {};
		var pdnDeviceName = $('#inputId_pdnDeviceName').val();
		var pdnCusNumber = $('#comboboxId_pdnCusNumber').combobox("getValue");
	
		if (pdnCusNumber != "") {
			postData['pdnCusNumber'] = pdnCusNumber;
		}
		if (pdnDeviceName != "") {
			postData['pdnDeviceName'] = pdnDeviceName;
		}
	
		$('#gridId_physicalDeviceName').grid('option', 'postData', postData);
		$('#gridId_physicalDeviceName').grid('reload');
	}

	var toolbarData_physicalDeviceName = [ {
		
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

	var toolbarOnClick_physicalDeviceName = function(event, ui) {
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
		$("#dialogId_physicalDeviceNameManage").dialog({
			width : 300,
			height : 'auto',
			subTitle : '新增',
			url : '${ctx}/wfsb/physicalDeviceName/new',
		});
		$("#dialogId_physicalDeviceNameManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_physicalDeviceName").grid("option", "selrow");
		if (selrow != null) {
			if(userLevel != 1){//非省局
				var rowData = $('#gridId_physicalDeviceName').grid("getRowData", selrow);
				if(rowData.PDN_CUS_NUMBER == 0){//0代表通用
					$.message({
						iframePanel:true,
						message : "不可修改通用数据！",
						cls : "warning"
					});
					return;
				}
			}
			$("#dialogId_physicalDeviceNameManage").dialog({
				width : 300,
				height : 'auto',
				subTitle : '修改',
				url : '${ctx}/wfsb/physicalDeviceName/edit?id=' + selrow.toString(),
			});
			$("#dialogId_physicalDeviceNameManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_physicalDeviceName").grid("option", "selrow");
		if (selrow != null) {
			if(userLevel != 1){//非省局
				var rowData = $('#gridId_physicalDeviceName').grid("getRowData", selrow);
				if(rowData.PDN_CUS_NUMBER == 0){//0代表通用
					$.message({
						iframePanel:true,
						message : "不可删除通用数据！",
						cls : "warning"
					});
					return;
				}
			}
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/wfsb/physicalDeviceName/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_physicalDeviceName").grid("reload");
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
	var gridColModel_physicalDeviceName = [ {
		label : "id",
		name : "ID",
		width : 100,
		hidden : true,
		key:true
	},{
		label : "机构编号",
		name : "PDN_CUS_NUMBER",
		align : "center",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':pdnCusNumber_json
		}
	}, {
		label : "设施编号",
		align : "center",
		name : "PDN_DEVICE_IDNTY"
	}, {
		label : "设施名称",
		align : "center",
		name : "PDN_DEVICE_NAME"
	}, {
		label : "备注",
		align : "center",
		name : "PDN_REMARK"
	}, {
		label : "创建时间",
		align : "center",
		name : "PDN_CRTE_TIME"
	}, {
		label : "更新时间",
		align : "center",
		name : "PDN_UPDT_TIME"
	} ];
</script>