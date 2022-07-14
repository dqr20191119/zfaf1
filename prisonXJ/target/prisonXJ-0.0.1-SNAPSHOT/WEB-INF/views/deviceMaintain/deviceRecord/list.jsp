<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 95%; margin: 5px">
		<cui:grid id="gridId_record" fitStyle="fill" multiselect="true" colModel="gridId_record_colModelDate">
			<cui:gridPager gridId="gridId_record" />
		</cui:grid>
	</div>
</body>
<script>
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	$.parseDone(function() {
		var url = "";
		//雁南异常设备从运维平台取数
		if ("4303" == jsConst.CUS_NUMBER) {
			url = "${ctx}/deviceMaintain/record/findYnSbyc";
		} else {
			url = "${ctx}/deviceMaintain/record/listAll.json?dmrCusNumber=" + jsConst.CUS_NUMBER;
		}
		$("#gridId_record").grid("reload", url);
	});

	var gridId_record_colModelDate = [{
		name : "DMR_DEVICE_NAME",
		label : "设备名称",
		align : "center",
		width : 250
	}, {
		name : "ZT",
		label : "设备情况",
		align : "center",
		width : 100,
		formatter: function() {
			return "故障";
		}
	}, {
		name : "DMR_CRTE_TIME",
		label : "记录时间",
		align : "center",
		width : 150
	}];

</script>