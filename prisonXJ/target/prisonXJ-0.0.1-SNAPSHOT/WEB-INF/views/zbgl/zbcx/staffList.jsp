<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="width: 100%; height: 100%">
	<cui:grid id="gridId_staff_list" singleselect="true" colModel="gridColModel_police_list" width="auto" fitStyle="fill">
		<cui:gridPager gridId="gridId_staff_list" />
	</cui:grid>
</div>

<script type="text/javascript">
	$.parseDone(function() {
		var url = '${ctx}/zbgl/zbcx/queryStaff?cusNumber=${cusNumber}&dprtmntId=${dprtmntId}&date=${date}&type=${type}';
		$("#gridId_staff_list").grid("reload", url);
	});

	var gridColModel_police_list = [ {
		name : "DPRTMNT_NAME",
		align : "center",
		label : "部门"
	},{
		name : "DUTY_DATE",
		align : "center",
		label : "值班日期"
	}, {
		name : "STAFF_NAME",
		align : "center",
		label : "值班人"
	}, {
		name : "JOB_NAME",
		align : "center",
		label : "岗位"
	}, {
		name : "DUTY_ORDER_NAME",
		align : "center",
		label : "班次"
	}, {
		name : "START_TIME",
		align : "center",
		label : "值班开始时间"
	}, {
		label : "值班结束时间",
		name : "END_TIME",
		align : "center",
	} ];

	 

 
</script>


