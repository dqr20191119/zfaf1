<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<cui:dialog id="dialogId_police_list_info" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false" maximizable="false" autoDestroy="true"></cui:dialog>

<div style="width: 100%; height: 100%">
	<cui:grid id="gridId_police_list" singleselect="true" colModel="gridColModel_police_list" width="auto" fitStyle="fill">
		<cui:gridPager gridId="gridId_police_list" />
	</cui:grid>
</div>

<script type="text/javascript">
	$.parseDone(function() {
		var url = '${ctx}/xxhj/jnmj/queryPoliceByDid?pbdcusNumber=${pbdcusNumber}&pbdDrptmntId=${pbdDrptmntId}';
		$("#gridId_police_list").grid("reload", url);
	});

	var gridColModel_police_list = [ {
		name : "PBD_CUS_NUMBER",
		width : "150",
		align : "center",
		hidden : true,
		label : "监所"
	}, {
		name : "PBD_POLICE_IDNTY",
		align : "center",
		label : "警号"
	}, {
		name : "PBD_POLICE_NAME",
		align : "center",
		label : "姓名"
	}, {
		name : "PBD_DRPTMNT",
		align : "center",
		label : "部门"
	}, {
		name : "PBD_BIRTH_DATE",
		align : "center",
		label : "出生日期"
	}, {
		name : "PBD_PSTN_NAME",
		align : "center",
		label : "职务"
	}, {
		label : "详情",
		name : "PBD_USER_ID",
		align : "center",
		formatter : "Formatter"
	} ];

	function Formatter(cellValue, options, rowObject) {
		var param1 = rowObject.PBD_USER_ID;
		var result = "<a href='' style='color: #4692f0;' onclick='toPoliceInfo( " + param1 + ");return false;'>查看</a>";
		return result;
	}

	function toPoliceInfo(USER_ID) {
		$("#dialogId_police_list_info").dialog({
			width : 640,
			height : 550,
			title : '民警信息',
			modal : true,
			autoOpen : false,
			url : "${ctx}/xxhj/jnmj/openPoliceInfo?pbdcusNumber=${pbdcusNumber}" + "&pbdUserId=" + USER_ID,
		});
		
		$("#dialogId_police_list_info").dialog("open");
	}
</script>


