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
				<th>警号：</th>
				<td>
					<cui:input id="policeNo" name="policeNo" componentCls="form-control"></cui:input>
				</td>
				<th>民警姓名：</th>
				<td>
					<cui:input id="policeName" name="policeName" componentCls="form-control"></cui:input>
				</td>
				<td>
					<cui:input id="deptName" name="deptName" type="hidden"></cui:input>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_policeInPrison" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_policeInPrison_colModelDate">
		<cui:gridPager gridId="gridId_policeInPrison" />
	</cui:grid>
</div>
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/policeLocation/search";
	var params = {};
	
	var deptName = "${deptName}";
	if(deptName) {
		params["deptName"] = deptName;
		$("form[id='formId_query']").find("#deptName").textbox("setValue", deptName);
	}
	
	$('#gridId_policeInPrison').grid('option', 'postData', params);
	$("#gridId_policeInPrison").grid("reload", url);
});

var gridId_policeInPrison_colModelDate = [{
	label : "id",
	name : "ID",
	width : "70",
	hidden : true
}, {
	label : "警号",
	name : "policeNo",
	width : "95", 
	align : "left"
}, {
	label : "民警姓名",
	name : "policeName",
	width : "75",
	align : "left"
}, {
	label : "所在监区",
	align : "left",
	name : "locationName",
	width : "85"
}, {
	label : "所在监狱",
	align : "left",
	name : "prisonName",
	width : "85"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_policeInPrison').grid('option', 'postData', formData);
	$("#gridId_policeInPrison").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId_query").form("reset");
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
