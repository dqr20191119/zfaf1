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
				<cui:input id="inputId_ssiServerName" type="text" onEnter="search"
					placeholder=""></cui:input>
			</div>
			<div>
				<label>类型:</label>
				<cui:combobox id="comboboxId_ssiTypeIndc" emptyText="请选择"  data="ssiTypeIndc_json"></cui:combobox>
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
<cui:toolbar id="toolbarId_streamServer" data="toolbarData_streamServer"
	onClick="toolbarOnClick_streamServer"></cui:toolbar>
<cui:grid id="gridId_streamServer" singleselect="true"
	shrinkToFit="false"  colModel="gridColModel_streamServer"
	fitStyle="fill" datatype="json"
	url="" pager="true"></cui:grid>
<cui:dialog id="dialogId_streamServerManage" autoOpen="false" iframePanel="true" 
	reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script>
var ssiTypeIndc_json=<%=CodeFacade.loadCode2Json("4.20.15")%>;

var jsConst=window.top.jsConst;
var cusNumber=jsConst.ORG_CODE;							//监狱编号
var userId=jsConst.USER_ID;				//登录人
$.parseDone(function(){
	$("#gridId_streamServer").grid("reload","${ctx}/sppz/streamServer/searchStreamServer.json?ssiCusNumber="+cusNumber);
});


function search() {
	var postData = {};
	var ssiServerName = $('#inputId_ssiServerName').val();
	var ssiTypeIndc = $('#comboboxId_ssiTypeIndc').combobox("getValue");

	if (ssiServerName != "") {
		postData['ssiServerName'] = ssiServerName;
	}
	if (ssiTypeIndc != "") {
		postData['ssiTypeIndc'] = ssiTypeIndc;
	}

	$('#gridId_streamServer').grid('option', 'postData', postData);
	$('#gridId_streamServer').grid('reload');
}
	var toolbarData_streamServer = [ {
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

	var toolbarOnClick_streamServer = function(event, ui) {
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
		$("#dialogId_streamServerManage").dialog({
			width : 300,
			height : 400,
			subTitle : '新增',
			url : '${ctx}/sppz/streamServer/new',
		});
		$("#dialogId_streamServerManage").dialog("open");
	}
	function f_modify() {
		var selrow = $("#gridId_streamServer").grid("option", "selrow");
		if (selrow != null) {
			$("#dialogId_streamServerManage").dialog({
				width : 300,
				height : 400,
				subTitle : '修改',
				url : '${ctx}/sppz/streamServer/edit?id=' + selrow.toString(),
			});
			$("#dialogId_streamServerManage").dialog("open");
		} else {
			$.message({
				iframePanel:true,
				message : "请先勾选需要处理记录！",
				cls : "warning"
			});
		}
	}
	function f_delete() {
		var selrow = $("#gridId_streamServer").grid("option", "selrow");
		if (selrow != null) {
			$.confirm( {
				message:"确认删除？",
				iframePanel:true,
				callback: function(sure) {
					if (sure == true) {
						$.ajax({
							type : "post",
							url : "${ctx}/sppz/streamServer/destory?id=" + selrow.toString(),
							success : function(data) {
								if(data.exception==undefined){
									$.message({
										iframePanel:true,
										message : "操作成功！",
										cls : "success"
									});
									$("#gridId_streamServer").grid("reload");
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
	var gridColModel_streamServer = [ {
		label : "ID",
		name : "ID",
		key:true,
		hidden:true
	},{
		label : "名称",
		width : 200,
		align : "center",
		name : "SSI_SERVER_NAME"
	}, {
		label : "类型",
		align : "center",
		name : "SSI_TYPE_INDC",
		formatter:'convertCode',
		revertCode : true,
		formatoptions:{
			'dataStructure' : 'list',
			'data':ssiTypeIndc_json
		}
	}, {
		label : "IP地址",
		align : "center",
		name : "SSI_IP_ADDRS"
	}, {
		label : "端口",
		align : "center",
		name : "SSI_PORT"
	}, {
		label : "IP地址2",
		align : "center",
		name : "SSI_IP2_ADDRS"
	}, {
		label : "端口2",
		align : "center",
		name : "SSI_PORT2"
	}, {
		label : "描述",
		align : "center",
		name : "SSI_REMARK"
	}, {
		label : "区域编号",
		align : "center",
		name : "SSI_AREA_ID"
	}, {
		label : "创建时间",
		align : "center",
		name : "SSI_CRTE_TIME"
	} ];
</script>