<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="height: 100%;">
	<cui:form id="formId_query">
		<table class="table">
			<tr>
				<th>值班人员：</th>
				<td>
					<cui:input id="dbdStaffName" name="dbdStaffName" componentCls="form-control"></cui:input>
				</td>
				<td>
					<%-- <cui:input id="categoryName" name="categoryName" type="hidden"></cui:input>
					<cui:input id="modeName" name="modeName" type="hidden"></cui:input> --%>
					<cui:input id="orderName" name="orderName" type="hidden"></cui:input>
					<%-- <cui:input id="deptCode" name="deptCode" type="hidden"></cui:input>
					<cui:input id="dutyJobId" name="dutyJobId" type="hidden"></cui:input> --%>
					<cui:input id="cusNumber" name="cusNumber" type="hidden"></cui:input>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_todayDuty" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_todayDuty_colModelDate">
		<cui:gridPager gridId="gridId_todayDuty" />
	</cui:grid>
</div>
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/zbgl/todayDuty/getTodayDutyList";
	var params = {};
	params["cusNumber"] = cusNumber;
	//var categoryName = "${categoryName}";
	//var modeName = "${modeName}";
	var orderName = "${orderName}";
	//var deptCode = "${deptCode}";
	//var dutyJobId = "${dutyJobId}";
	
	 if(cusNumber) {
		$("form[id='formId_query']").find("#cusNumber").textbox("setValue", cusNumber);
	}
	 /*
	if(modeName) {
		params["modeName"] = modeName;
		$("form[id='formId_query']").find("#modeName").textbox("setValue", modeName);
	} */
	if(orderName) {
		params["orderName"] = orderName;
		$("form[id='formId_query']").find("#orderName").textbox("setValue", orderName);
	}
	/* if(deptCode) {
		params["deptCode"] = deptCode;
		$("form[id='formId_query']").find("#deptCode").textbox("setValue", deptCode);
	}
	if(dutyJobId) {
		params["dutyJobId"] = dutyJobId;
		$("form[id='formId_query']").find("#dutyJobId").textbox("setValue", dutyJobId);
	} */
	
	
	$('#gridId_todayDuty').grid('option', 'postData', params);
	$("#gridId_todayDuty").grid("reload", url);
});

var gridId_todayDuty_colModelDate = [{
	label : "id",
	name : "ID",
	width : "70",
	hidden : true
}, {
	label : "值班人员",
	name : "dbdStaffName",
	width : "95", 
	align : "center"
},/*  {
	label : "值班类别",
	name : "dcaCategoryName",
	width : "95", 
	align : "left"
}, {
	label : "值班模板",
	name : "cdmModeName",
	width : "95", 
	align : "left"
}, */ {
	label : "班次名称",
	name : "dorDutyOrderName",
	width : "95", 
	align : "center"
}, {
	label : "岗位名称",
	name : "cdjJobName",
	width : "95", 
	align : "center"
}, {
	label : "班次开始时间",
	name : "dorStartTime",
	width : "95", 
	align : "center"
}, {
	label : "班次结束时间",
	name : "dorEndTime",
	width : "95", 
	align : "center"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_todayDuty').grid('option', 'postData', formData);
	$("#gridId_todayDuty").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	// $("#formId_query").form("reset");
	$("form[id='formId_query']").find("#dbdStaffName").textbox("setValue", "");
}

/**
 * 操作栏初始化
 */
function operateFormatter(cellValue, options, rowObject) {
	//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
	var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"realTimeVideo()\">实时画面</button>" ;
	
	return result;
}
</script>
