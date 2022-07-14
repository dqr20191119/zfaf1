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
				<label>器材名称:</label>
				<cui:input id="inputId_poeDeviceName" name="poeDeviceName" type="text" 
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

<cui:toolbar id="toolbarId_policeDevice" data="toolbarData_policeDevice"
	onClick="toolbarOnClick_policeDevice"></cui:toolbar>
<cui:grid id="gridId_policeDevice" singleselect="true" shrinkToFit="false" 
	 colModel="gridColModel_policeDevice" fitStyle="fill"
	datatype="json" url="" pager="true"></cui:grid>
<cui:dialog id="dialogId_policeDeviceManage" autoOpen="false" iframePanel="true" 
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
var poeTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.34")%>;
var poeSttsIndc_json=<%=CodeFacade.loadCode2Json("4.20.55")%>;

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE							//监狱编号
var userId=jsConst.USER_ID					//登录人

$.parseDone(function(){
	$("#gridId_policeDevice").grid("reload","${ctx}/wfsb/policeDevice/searchPoliceDevice.json?poeCusNumber="+cusNumber);
});

function search() {
	var postData = {};
	var poeDeviceName = $('#inputId_poeDeviceName').val();
	
	if (poeDeviceName != "") {
		postData['poeDeviceName'] = poeDeviceName;
	}

	$('#gridId_policeDevice').grid('option', 'postData', postData);
	$('#gridId_policeDevice').grid('reload');
}

	var toolbarData_policeDevice = [ {
		
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

	var toolbarOnClick_policeDevice = function(event, ui) {
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
		$("#dialogId_policeDeviceManage").dialog({
			width : 700,
			height : 'auto',
			subTitle : '新增',
			url : '${ctx}/wfsb/policeDevice/new',
		});
		$("#dialogId_policeDeviceManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_policeDevice").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_policeDeviceManage").dialog({
				width : 700,
				height : 'auto',
				subTitle : '修改',
				url : '${ctx}/wfsb/policeDevice/edit?id=' + selrow.toString(),
			});
			$("#dialogId_policeDeviceManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_policeDevice").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/wfsb/policeDevice/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_policeDevice").grid("reload");
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
	var gridColModel_policeDevice = [ {
		label : "id",
		name : "ID",
		hidden : true,
		key:true
	}, {
		label : "器材名称",
		width : 200,
		align : "center",
		name : "POE_DEVICE_NAME"
	}, {
		label : "器材类型",
		align : "center",
		name : "POE_TYPE_INDC",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':poeTypeIndc_json
		}
	}, {
		label : "所属部门",
		align : "center",
		name : "POE_DPRTMNT_NAME"
	}, {
		label : "型号",
		align : "center",
		name : "POE_MODEL"
	}, {
		label : "计量单位",
		align : "center",
		name : "POE_UNIT"
	}, {
		label : "数量",
		align : "center",
		name : "POE_COUNT"
	}, {
		label : "责任人",
		align : "center",
		name : "POE_ADMINISTRATOR"
	}, {
		label : "装备功能描述",
		align : "center",
		name : "POE_FUNCTION_DESC"
	}, {
		label : "状态",
		align : "center",
		name : "POE_STTS_INDC",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':poeSttsIndc_json
		}
	}, {
		label : "状态描述",
		align : "center",
		name : "POE_STTS_DESC"
	},  {
		label : "保管措施",
		align : "center",
		name : "POE_SAVE_MEHTOD"
	}, {
		label : "创建时间",
		align : "center",
		name : "POE_CRTE_TIME"
	}, {
		label : "更新时间",
		align : "center",
		name : "POE_UPDT_TIME"
	} ];
</script>