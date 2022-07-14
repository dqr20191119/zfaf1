<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:grid id="gridId_callName_h" fitStyle="fill" multiselect="true" colModel="gridId_callName_h_colModelDate">
			<cui:gridPager gridId="gridId_callName_h" />
		</cui:grid>

	</div>
	<cui:dialog id="dialogId_callName_h_done" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
	<cui:dialog id="dialogId_callName_h_undone" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;

	$.parseDone(function() {
		var url = "${ctx}/callNames/listAll.json?cnrIsDone=1&cnrCusNumber=" + cusNumber ;
		$("#gridId_callName_h").grid("reload", url);
	});
	var gridId_callName_h_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "CNR_CUS_NUMBER",
		label : "监狱",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : combobox_jy
		}
	}, {
		name : "CNR_START_TIME",
		label : "点名发起时间"
	}, {
		name : "CNR_TIME_LAG",
		label : "点名时长（分钟）"
	}, {
		name : "CNR_END_TIME",
		label : "点名结束时间",
	}, {
		name : "CNR_PRISONER_SUM",
		label : "罪犯总数"
	}, {
		name : "CNR_CALL_SUM",
		label : "已点到",
		align : "center",
		formatter : "formatter_Done"
	}, {
		name : "UNDONE_SUM",
		label : "未点到",
		align : "center",
		formatter : "formatter_Undone"
	} ];
	
	function formatter_Done(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var result = "<a href=\"javascript:void(0)\" style=\"color: #4692f0;\" onclick=\"openRollcallDone('"
			+ param1.toString() + "')\">"+cellValue+"</a>";
		return result;
	}
	
	function openRollcallDone(recordId) {
		$("#dialogId_callName_h_done").dialog(
				{
					width : 1000,
					height : 800,
					title : '已点到罪犯列表',
					url : "${ctx}/callNames/openDialog/dmls/done?recordId=" + recordId,
				});
		$("#dialogId_callName_h_done").dialog("open");
	}
	
	function formatter_Undone(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var result =  "<a href=\"javascript:void(0)\" style=\"color: #4692f0;\" onclick=\"openRollcallUndone('"
			+ param1.toString() + "')\">"+cellValue+"</a>";
		return result;
	}
	
	function openRollcallUndone(recordId) {
		$("#dialogId_callName_h_undone").dialog(
				{
					width : 1000,
					height : 800,
					title : '未点到罪犯列表',
					url : "${ctx}/callNames/openDialog/dmls/undone?recordId=" + recordId,
				});
		$("#dialogId_callName_h_undone").dialog("open");
	}
	
	 
</script>