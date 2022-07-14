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
				<th>罪犯名称 ：</th>
				<td><cui:input id="zfName" name="zfName"></cui:input></td>
				<th>罪犯心情：</th>
				<td><cui:combobox name="zfMood" id="zfMood"
						data="comboboxData_zfMood"></cui:combobox></td>
				<th>处理状态：</th>
				<td><cui:combobox name="spstatus" id="spstatus"
						data="comboboxData_statues"></cui:combobox></td>
			</tr>
			<tr>
				<th>开始时间 ：</th>
				<td><cui:datepicker id="sTime" name="sTime"
						dateFormat="yyyy-MM-dd HH:mm" width="190"></cui:datepicker></td>
				<th>结束时间 ：</th>
				<td><cui:datepicker id="eTime" name="eTime"
						dateFormat="yyyy-MM-dd HH:mm" width="190"></cui:datepicker></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /><cui:button label="重置" onClick="clear"></cui:button></td>
			</tr>
		</table>
	</cui:form>


	<cui:toolbar id="toolbarId_jhrc" data="toolbar_rlsb_List"></cui:toolbar>


	<cui:grid id="gridId_rlsb_List" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" rowNum="15"
		url="${ctx}/wghgl/yrzq/xqdjjl/searchIndex">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="name" align="center">罪犯姓名</cui:gridCol>
			<cui:gridCol name="bh" align="center">罪犯编号</cui:gridCol>
			<cui:gridCol name="photo" align="center">照片</cui:gridCol>
			<cui:gridCol name="syouname" align="center">单位名称</cui:gridCol>
			<cui:gridCol name="js" align="center">监舍名称</cui:gridCol>
			<cui:gridCol name="spstatus" align="center">审批状态</cui:gridCol>
			<cui:gridCol name="mood" align="center">心情</cui:gridCol>
			<cui:gridCol name="status" align="center">心情标志</cui:gridCol>
			<cui:gridCol name="cretime" align="center">登记时间</cui:gridCol>
			<cui:gridCol name="username" align="center">审批人</cui:gridCol>
			<cui:gridCol name="opinion" align="center">审批意见</cui:gridCol>
			<cui:gridCol name="sptime" align="center">审批时间</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rlsb_List" />
	</cui:grid>
	<cui:dialog id="dialogId_rlsb_view" autoOpen="false" resizable="false"
		iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>
<script>
	var comboboxData_statues = [ {//处理状态data
		value : '0',
		text : '待处理'
	}, {
		value : '1',
		text : '已处理'
	} ];
	var comboboxData_zfMood = [ {//罪犯心情data
		value : 'H',
		text : '开心快乐'
	}, {
		value : 'N',
		text : '正常平和'
	} ,{
		value : 'S',
		text : '伤心低落'
	}];
	$.parseDone(function() {
	});

	function search() {
		var formData = $("#formId_rlsb_List").form("formData");
		var sTime = $("#sTime").datepicker("getValue");//开始时间
		var eTime = $("#eTime").datepicker("getValue");//结束时间
		var zfName = $("#zfName").textbox("getValue");//罪犯姓名
		var statues = $("#spstatus").combobox("getValue");//处理状态
		var zfMood=$("#zfMood").combobox("getValue");//罪犯心情
		if (zfName) {
			zfName = new Base64().multiEncode(zfName, 2);
		}
		$("#gridId_rlsb_List").grid("reload");
		$("#gridId_rlsb_List").grid(
				"reload",
				"${ctx}/wghgl/yrzq/xqdjjl/searchIndex?sTime=" + sTime
						+ "&eTime=" + eTime + "&zfName=" + zfName + "&statues="
						+ statues+"&zfMood="+zfMood);
	}

	function clear() {
		$("#formId_rlsb_List").form("reset");
	}
</script>