<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:grid id="gridId_dmjsxq" fitStyle="fill" colModel="gridId_dmjsxq_colModelDate">
			<cui:gridPager gridId="gridId_dmjsxq" />
		</cui:grid>
	</div>
	<cui:dialog id="dialogId_dmjsxq" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true"></cui:dialog>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		var url = "${ctx}/callNames/master/listAll/jsxq.json?nadCusNumber=" + cusNumber + "&nadMasterId=${ID}";
		$("#gridId_dmjsxq").grid("reload", url);
	});

	var gridId_dmjsxq_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "NAD_MASTER_ID",
		label : "点名主表id",
		hidden : true
	}, {
		name : "LC",
		label : "楼层",
		align : "center",
	}, {
		name : "NAD_JSH",
		label : "监舍",
		align : "center"
	}, {
		name : "NAD_PRISONER_TOTAL",
		label : "总人数",
		align : "center"
	}, {
		name : "NO_CALLED_TOTAL",
		label : "未点到",
		align : "center",
		formatter : "FormatterWdd"
	}, {
		name : "NAD_PRISONER_CALLED_TOTAL",
		label : "已点到",
		align : "center",
		formatter : "FormatterYdd"
	} ];

	function FormatterWdd(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var param2 = rowObject.NAD_MASTER_ID;
		var result = "<a href=\"javascript:void(0)\" style=\"color: #4692f0;\" onclick=\"openZfxx('"
				+ param1 + "','" + param2 + "','0')\">" + cellValue + "</a>";
		return result;
	}

	function FormatterYdd(cellValue, options, rowObject) {
		var param1 = rowObject.ID;
		var param2 = rowObject.NAD_MASTER_ID;
		var result = "<a href=\"javascript:void(0)\" style=\"color: #4692f0;\" onclick=\"openZfxx('"
				+ param1 + "','" + param2 + "','1')\">" + cellValue + "</a>";
		return result;
	}

	function openZfxx(recordId, masterId, isCalled) {

		$("#dialogId_dmjsxq").dialog(
			{
				width : 800,
				height : 600,
				url : "${ctx}/callNames/master/openDialog/record/zfInfo?isCalled="+ isCalled
						+ "&nadId="+ recordId
						+ "&masterId=" + masterId,
				title : '点名人员详情',
			});
		$("#dialogId_dmjsxq").dialog("open");

	}
</script>