<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rlsb_List">
		<table class="table">
			<tr>
				<th>名称 ：</th>
				<td><cui:input id="qjName" name="qjName" placeholder="请输单位或警察名称"></cui:input></td>
				<th>状态：</th>
				<td><cui:combobox name="statues" id="statues" 
						data="comboboxData_statues" ></cui:combobox></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
			</tr>
			<tr>
				<th>开始时间 ：</th>
				<td><cui:datepicker id="sTime" name="sTime"
						dateFormat="yyyy-MM-dd HH:mm" width="190"></cui:datepicker></td>
				<th>结束时间 ：</th>
				<td><cui:datepicker id="eTime" name="eTime"
						dateFormat="yyyy-MM-dd HH:mm" width="190"></cui:datepicker></td>
				<td><cui:button label="重置" onClick="clear"></cui:button></td>
			</tr>
		</table>
	</cui:form>


	<cui:toolbar id="toolbarId_jhrc" data="toolbar_rlsb_List"></cui:toolbar>


	<cui:grid id="gridId_rlsb_List" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" rowNum="15"
		url="${ctx}/wghgl/yrzq/qjjc/searchIndex">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="js" align="center">名称</cui:gridCol>
			<cui:gridCol name="cretime" align="center">登记时间</cui:gridCol>
			<cui:gridCol name="policename" align="center">警察姓名</cui:gridCol>
			<cui:gridCol name="status" align="center">状态</cui:gridCol>
			<cui:gridCol name="syouname" align="center">单位名称</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rlsb_List" />
	</cui:grid>
	<cui:dialog id="dialogId_rlsb_view" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>
<script>
	var comboboxData_statues = [ {
		value : 'Y',
		text : '正常'
	}, {
		value : 'N',
		text : '异常'
	} ];

	$.parseDone(function() {
	});

	function search() {
		var formData = $("#formId_rlsb_List").form("formData");
		var sTime = $("#sTime").datepicker("getValue");
		var eTime = $("#eTime").datepicker("getValue");
		var qjName = $("#qjName").textbox("getValue");
		var statues = $("#statues").combobox("getValue");
		if(qjName) {
			qjName = new Base64().multiEncode(qjName, 2);
		}
		$("#gridId_rlsb_List").grid("reload");
		$("#gridId_rlsb_List").grid(
				"reload",
				"${ctx}/wghgl/yrzq/qjjc/searchIndex?sTime=" + sTime + "&eTime="
						+ eTime + "&qjName=" + qjName + "&statues=" + statues);
	}

	function clear() {
		$("#formId_rlsb_List").form("reset");
	}
</script>