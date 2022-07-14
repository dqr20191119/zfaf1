<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<cui:grid id="gridId_callNameDone" fitStyle="fill" multiselect="true" colModel="gridId_callNameDone_colModelDate">
			<cui:gridPager gridId="gridId_callNameDone" />
		</cui:grid>

	</div>
</body>
<cui:dialog id="dialogId_callNameDone" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	//从编码表中取监狱 
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;

	$.parseDone(function() {
		var url = "${ctx}/callNames/listAllForDone.json?cndRecordId=${recordId}";
		$("#gridId_callNameDone").grid("reload", url);
	});
	var gridId_callNameDone_colModelDate = [ {
		name : "ID",
		label : "ID",
		key : true,
		hidden : true
	}, {
		name : "CND_CUS_NUMBER",
		label : "监狱",
		formatter : "convertCode",
		revertCode : true,
		formatoptions : {
			'data' : combobox_jy
		}
	}, {
		name : "CND_PRISONER_NAME",
		label : "罪犯"
	}, {
		name : "CND_DPRTMNT",
		label : "监区"
	}, {
		name : "CND_LC",
		label : "楼层",
	}, {
		name : "CND_JS",
		label : "监舍"
	}, {
		name : "CND_ROLL_TIME",
		label : "点到时间"
	},{
		name : "CND_ROLL_MARK",
		label : "点名标志",
		formatter : function(cellvalue, options, rawObject) {
			if(cellvalue == "1"){
				return "手动";	
			}else {
				return "自动";
			}
		}
	} , {
		label : "详情",
		name : "check",
		align : "center",
		formatter : "formatterDone"
	} ];
	
	function formatterDone(cellValue, options, rowObject) {
		var param1 = rowObject.CND_PRISONER_INDC;
		var result = "<a href='' style='color: #4692f0;' onclick='toDonePrisonerInfo( "
				+ param1.toString() + ");return false;'>查看</a>";
		return result;
	}
	
	function toDonePrisonerInfo(PRSNR_IDNTY) {
		$("#dialogId_callNameDone").dialog(
				{
					width : 1000,
					height : 800,
					title : '已点到罪犯信息',
					modal : true,
					autoOpen : false,
					url : "${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId=" + PRSNR_IDNTY,
				});
		$("#dialogId_callNameDone").dialog("open");
	}
	 
</script>