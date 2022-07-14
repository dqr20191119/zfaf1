<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:grid id="gridId_zfdmxx" fitStyle="fill" colModel="gridId_zfdmxx_colModelDate">
			<cui:gridPager gridId="gridId_zfdmxx" />
		</cui:grid>
	</div>
</body>
<cui:dialog id="dialogId_zf" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	$.parseDone(function() {
		var url = "${ctx}/callNames/master/listAll/zfxx.json?cnrCusNumber=" + cusNumber + "&cnrIsCalled=${isCalled}&cnrNadId=${nadId}&cnrMasterId=${masterId}";
		$("#gridId_zfdmxx").grid("reload", url);
	});

	var gridId_zfdmxx_colModelDate = [ {
		label : "id",
		name : "ID",
		width : 70,
		key : true,
		hidden : true
	}, {
		name : "CNR_MASTER_ID",
		label : "点名主表id",
		hidden : true
	}, {
		name : "CNR_NAD_ID",
		label : "点名区域主表id",
		hidden : true
	}, {
		name : "CNR_PRISONER_INDC",
		label : "罪犯编号",
		align : "center",
	}, {
		name : "PBD_PRISONER",
		label : "罪犯",
		align : "center"
	}, {
		label : "详情",
		name : "check",
		align : "center",
		formatter : "formatterZfxx"
	} ];

	function formatterZfxx(cellValue, options, rowObject) {
		var param1 = rowObject.CNR_PRISONER_INDC;
		var result = "<a href='' style='color: #4692f0;' onclick='toPrisonerInfo( " + param1.toString() + ");return false;'>查看</a>";
		return result;
	}

	function toPrisonerInfo(PRSNR_IDNTY) {
		$("#dialogId_zf").dialog(
				{
					width : 1000,
					height : 800,
					title : '已点到罪犯信息',
					modal : true,
					autoOpen : false,
					url : "${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId=" + PRSNR_IDNTY,
				});
		$("#dialogId_zf").dialog("open");
	}
</script>