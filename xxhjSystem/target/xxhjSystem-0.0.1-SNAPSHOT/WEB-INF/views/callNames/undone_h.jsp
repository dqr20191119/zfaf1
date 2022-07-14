<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:grid id="gridId_callNameUndone" fitStyle="fill" multiselect="true" colModel="gridId_callNameUndone_colModelDate">
			<cui:gridPager gridId="gridId_callNameUndone" />
		</cui:grid>

	</div>
</body>
<cui:dialog id="dialogId_callNameUndone" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;

	$.parseDone(function() {
		var url = "${ctx}/callNames/listAllForUndone.json?cnuRecordId=${recordId}";
		$("#gridId_callNameUndone").grid("reload", url);
	});
	var gridId_callNameUndone_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "CNU_CUS_NUMBER",
		label : "监狱",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : combobox_jy
		}
	}, {
		name : "CNU_PRISONER_NAME",
		label : "罪犯"
	}, {
		name : "CNU_DPRTMNT",
		label : "监区"
	}, {
		name : "CNU_LC",
		label : "楼层",
	}, {
		name : "CNU_JS",
		label : "监舍"
	} , {
		label : "详情",
		name : "check",
		align : "center",
		formatter : "formatterUndone"
	} ];
	
	function formatterUndone(cellValue, options, rowObject) {
		var param1 = rowObject.CNU_PRISONER_INDC;
		var result = "<a href='' style='color: #4692f0;' onclick='toUndonePrisonerInfo( "
				+ param1.toString() + ");return false;'>查看</a>";
		return result;
	}
	
	function toUndonePrisonerInfo(PRSNR_IDNTY) {
		$("#dialogId_callNameUndone").dialog(
				{
					width : 1000,
					height : 800,
					title : '未点到罪犯信息',
					modal : true,
					autoOpen : false,
					url : "${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId=" + PRSNR_IDNTY,
				});
		$("#dialogId_callNameUndone").dialog("open");
	}
	 
</script>