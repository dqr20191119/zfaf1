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
	<cui:form id="formId2_query">
		<table class="table">
			<tr>
				<th>区域名称：</th>
				<td>
					<cui:input id="deptName" name="deptName" componentCls="form-control"></cui:input>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_policeSwsbInPrison" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_policeSwsbInPrison_colModelDate">
		<cui:gridPager gridId="gridId_policeSwsbInPrison" />
	</cui:grid>
</div>
<cui:dialog id="dialogId_policeSwsbCount" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"></cui:dialog>
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/policeLocation/swsbSearch";
	var params = {};
	
	$('#gridId_policeSwsbInPrison').grid('option', 'postData', params);
	$("#gridId_policeSwsbInPrison").grid("reload", url);
});

var gridId_policeSwsbInPrison_colModelDate = [{
	label : "监狱名称",
	align : "left",
	name : "PRISON_NAME",
	width : "85"
}, {
	label : "区域名称",
	align : "left",
	name : "LOCATION_NAME",
	width : "85"
}, {
	label : "民警数量",
	name : "POLICE_COUNT",
	width : "75",
	align : "left",
	formatter : "policeCountFormatter"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId2_query").form("formData");
	$('#gridId_policeSwsbInPrison').grid('option', 'postData', formData);
	$("#gridId_policeSwsbInPrison").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId2_query").form("reset");
}

/**
 * 操作栏初始化
 */
function policeCountFormatter(cellValue, options, rowObject) {
	//var result = '<a href="" style="color: #4692f0;" onclick="getAlarmInfo(\''+param1.toString()+'\');return false;">查看</a>';
	var result = "<a href='' style='color: #4692f0;' onClick='showPoliceInPrisonAreaList(\"" + rowObject.LOCATION_NAME + "\");return false;'>" + cellValue + "</a>";
	
	return result;
}

/**
 * 显示在监民警数据列表
 */
function showPoliceInPrisonAreaList(deptName) {
	$("#dialogId_policeSwsbCount").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : '在监民警列表',
		// modal : true, //属性
		//autoOpen : false,
		url : "${ctx}/policeLocation/openDialog?deptName=" + new Base64().multiEncode(deptName, 2)
	});
	$("#dialogId_policeSwsbCount").dialog("open");
}
</script>
