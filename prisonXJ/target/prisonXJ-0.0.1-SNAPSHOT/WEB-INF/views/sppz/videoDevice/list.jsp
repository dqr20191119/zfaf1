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
				<cui:input id="inputId_vdiDeviceName" type="text" 
					placeholder=""></cui:input>
			</div>
			<div>
				<label>类型:</label>
				<cui:combobox id="comboboxId_vdiTypeIndc" emptyText="请选择"  data="VDI_TYPE_INDC_json"></cui:combobox>
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
<cui:toolbar id="toolbarId_videoDevice" data="toolbarData_videoDevice"
	onClick="toolbarOnClick_videoDevice"></cui:toolbar>
<cui:grid id="gridId_videoDevice" singleselect="true" shrinkToFit="false"
	 colModel="gridColModel_videoDevice" fitStyle="fill"
	datatype="json" url="" pager="true"></cui:grid>
<cui:dialog id="dialogId_videoDeviceManage" autoOpen="false" iframePanel="true" 
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
var VDI_TYPE_INDC_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;
var VDI_BRAND_json=<%=CodeFacade.loadCode2Json("4.20.16")%>;

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;					//登录人
$.parseDone(function(){
	$("#gridId_videoDevice").grid("reload","${ctx}/sppz/videoDevice/searchVideoDevice.json?vdiCusNumber="+cusNumber);
});

function search() {
	var postData = {};
	var vdiDeviceName = $('#inputId_vdiDeviceName').val();
	var vdiTypeIndc = $('#comboboxId_vdiTypeIndc').combobox("getValue");

	if (vdiDeviceName != "") {
		postData['vdiDeviceName'] = vdiDeviceName;
	}
	if (vdiTypeIndc != "") {
		postData['vdiTypeIndc'] = vdiTypeIndc;
	}

	$('#gridId_videoDevice').grid('option', 'postData', postData);
	$('#gridId_videoDevice').grid('reload');
}

	var toolbarData_videoDevice = [ {
	
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

	var toolbarOnClick_videoDevice = function(event, ui) {
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
		$("#dialogId_videoDeviceManage").dialog({
			width : 300,
			height : 400,
			subTitle : '新增',
			url : '${ctx}/sppz/videoDevice/new',
		});
		$("#dialogId_videoDeviceManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_videoDevice").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_videoDeviceManage").dialog({
				width : 300,
				height : 400,
				subTitle : '修改',
				url : '${ctx}/sppz/videoDevice/edit?id=' + selrow.toString(),
			});
			$("#dialogId_videoDeviceManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_videoDevice").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/sppz/videoDevice/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_videoDevice").grid("reload");
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
	var gridColModel_videoDevice = [ {
		label : "ID",
		name : "ID",
		key:true,
		hidden:true
	}, {
		label : "名称",
		width : 200,
		align : "center",
		name : "VDI_DEVICE_NAME"
	}, {
		label : "类型",
		align : "center",
		name : "VDI_TYPE_INDC",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':VDI_TYPE_INDC_json
		}
	}, {
		label : "品牌",
		align : "center",
		name : "VDI_BRAND",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':VDI_BRAND_json
		}			
	}, {
		label : "型号",
		align : "center",
		name : "VDI_MODE"
	}, {
		label : "IP地址",
		align : "center",
		name : "VDI_IP_ADDRS"
	}, {
		label : "IP地址2",
		align : "center",
		name : "VDI_IP2_ADDRS"
	}, {
		label : "端口",
		align : "center",
		name : "VDI_PORT"
	}, {
		label : "用户名",
		align : "center",
		name : "VDI_USER_NAME"
	}/*, {
		label : "密码",
		align : "center",
		name : "VDI_USER_PASSWORD"
	}*/, {
		label : "创建时间",
		align : "center",
		name : "VDI_CRTE_TIME"
	} ];
</script>