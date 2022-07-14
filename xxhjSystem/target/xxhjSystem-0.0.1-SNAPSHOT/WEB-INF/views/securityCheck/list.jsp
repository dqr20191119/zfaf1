<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
String date = simpleDateFormat.format(new Date());
String STime = date + " 00:00:00";
String ETime = date + " 23:59:59";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body style="height: 100%">
	<div style="height: 98%; margin: 5px">
		<%-- <cui:toolbar id="toolbarId_securityCheck" data="toolbar_securityCheckDate"></cui:toolbar> --%>
		<cui:grid id="gridId_securityCheck" fitStyle="fill" multiselect="true" colModel="gridId_securityCheck_colModelDate">
			<cui:gridPager gridId="gridId_securityCheck" />
		</cui:grid>
	</div>
</body>
<script>
	var bjlx_data = <%=CodeFacade.loadCode2Json("4.20.27")%>;//报警类型
	var combobox_jy = <%=CodeFacade.loadCode2Json(GroupKeyConst.GROUP_CODE_KEY_JY)%>;
	var bjdj_data = <%=CodeFacade.loadCode2Json("4.20.25")%>;//报警等级
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	
	$.parseDone(function() {
		var url = "${ctx}/alarm/searchRecordList.json";
		$('#gridId_securityCheck').grid('option', 'postData', {
			ardCusNumber: cusNumber,
			ardRemark: "生命探测仪",
        	STime: "<%=STime%>",
        	ETime: "<%=ETime%>"
		});
		$("#gridId_securityCheck").grid("reload", url);
	});
	
	var gridId_securityCheck_colModelDate = [{
		label : "监所名称",
		name : "ARD_CUS_NUMBER",
		width : "95", 
		align : "center",
		formatter : "convertCode",  revertCode : true, formatoptions : { 'data':combobox_jy } 
	},{
		label : "报警日期",
		align : "center",
		name : "ALARM_DATE",
		width : "85"
	}, {
		label : "报警时间",
		align : "center",
		name : "ALARM_TIME",
		width : "75"
	}, {
		label : "报警等级",
		align : "center",
		name : "ARD_ALERT_LEVEL_INDC",
		width : "75",
		cellattr : function(o) {
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "1"  ) {
	        	return 'style="color: #FF3030"';
		    }
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "2"  ) {
	        	return 'style="color: #FFC125"';
		    }
		    if ( o.rawObject.ARD_ALERT_LEVEL_INDC == "3"  ) {
	        	return 'style="color: #6495ED"';
		    }
		},
		formatter : "convertCode",
	    revertCode : true,
	    formatoptions : { 'data':bjdj_data } 
	}];

</script>