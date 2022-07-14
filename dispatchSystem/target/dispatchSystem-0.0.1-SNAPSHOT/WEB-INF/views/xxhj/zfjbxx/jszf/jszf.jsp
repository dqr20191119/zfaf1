<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<cui:dialog id="dialogId_jszf" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>

<div style="width: 100%; height: 100%">
	<cui:grid id="gridId_jszf" singleselect="true" colModel="gridColModel_jszf" width="auto" fitStyle="fill">
		<cui:gridPager gridId="gridId_jszf" />
	</cui:grid>
</div>

<script type="text/javascript">
	var fgdj_data = <%=CodeFacade.loadCode2Json("4.6.6")%>;
	
	$.parseDone(function() {
		var url = '${ctx}/xxhj/zfjbxx/queryJSPrisonerInfo?cusNumber=${cusNumber}&lch=${lch}&jsh=${jsh}';
		$("#gridId_jszf").grid("reload", url);
	});
	
	var gridColModel_jszf = [ {
		label : "组号",
		name : "BED_NUMBER",
		align : "center"
	}, {
		label : "罪犯编号",
		name : "PRSNR_IDNTY",
		align : "center"
	}, {
		label : "姓名",
		name : "NAME",
		align : "center",
	}, {
		label : "危险等级",
		name : "DANGER_LEVEL",
		align : "center",
		formatter : "convertCode",  revertCode : true, formatoptions : { 'data':fgdj_data } 
	}, {
		label : "详情",
		name : "check",
		align : "center",
		formatter : "Formatter"
	} ]

	function Formatter(cellValue, options, rowObject) {
		var param1 = rowObject.PRSNR_IDNTY;
		var param2 = rowObject.SEX;
		var param3 = rowObject.NAME;
		var result = "<a href='' style='color: #4692f0;' onclick='toPrisonerInfo( "
				+ param1.toString() + ");return false;'>查看</a>";
		return result;
	}

	function toPrisonerInfo(PRSNR_IDNTY) {
		$("#dialogId_jszf").dialog(
				{
					width : 1000,
					height : 800,
					title : '罪犯信息',
					modal : true,
					autoOpen : false,
					url : "${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId="
							+ PRSNR_IDNTY,
				});
		$("#dialogId_jszf").dialog("open");
	}
</script>


