<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:form id="formId_gb_query">
			<table class="table">
				<tr>
					<th>开始播放时间：</th>
					<td>
						<cui:datepicker id="startTime" name="startTime" componentCls="form-control"></cui:datepicker>
					</td>
					<th>广播名称：</th>
					<td>
						<cui:input id="broadcastName" name="broadcastName" componentCls="form-control"></cui:input>
					</td>
					<th>广播类型：</th>
					<td>
						<cui:combobox id="contentType" name="contentType" data="contentType_data" componentCls="form-control"></cui:combobox>
					</td>
					<td>
						<cui:button label="查询" onClick="gb_query"></cui:button>
						<cui:button label="重置" onClick="gb_clear"></cui:button>
					</td>
				</tr>
			</table>
		</cui:form>
		<cui:grid id="gridId_broadcastRecord" fitStyle="fill" multiselect="true" colModel="gridId_broadcastRecord_colModelDate">
			<cui:gridPager gridId="gridId_broadcastRecord" />
		</cui:grid>
	</div>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	// 广播类型
	var contentType_data = <%=CodeFacade.loadCode2Json("4.20.62")%>;
	
	$.parseDone(function() {
		var url = "${ctx}/broadcastRecord/listAll.json";
		$("#gridId_broadcastRecord").grid("reload", url);
	});
	
	var gridId_broadcastRecord_colModelDate = [{
		label : "id",
		name : "id",
		key : true,
		hidden : true
	}, {
		name : "broadcastName",
		label : "广播设备",
		align : "left",
	}, {
		name : "contentType",
		label : "播放类型",
		align : "center",
		width : 250,
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {'data': contentType_data}
	}, {
		name : "startTime",
		label : "开始播放时间",
		align : "center",
		width : 250
	}, {
		name : "endTime",
		label : "停止播放时间",
		align : "center",
		width : 250
	}, {
		name : "contentValue",
		label : "播放内容",
		align : "left",
		width : 250
	}];

	function gb_query() {
		var formData = $("#formId_gb_query").form("formData");
		$("#gridId_broadcastRecord").grid("option", "postData", formData);
		$("#gridId_broadcastRecord").grid("reload");
	}

	function gb_clear() {
		$("#formId_gb_query").form("clear");
	}
</script>