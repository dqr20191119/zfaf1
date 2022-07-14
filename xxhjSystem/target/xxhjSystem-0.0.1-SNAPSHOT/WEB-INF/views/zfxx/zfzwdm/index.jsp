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
				<th>监区名称：</th>
				<td>
					<cui:input id="deptname" name="deptname" componentCls="form-control"></cui:input>
				</td>
				<th>点名日期：</th>
				<td>
					<cui:datepicker componentCls="form-control" id="dtdmsj" name="dtdmsj" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_zfZwdm" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_zfZwdm_colModelDate">
		<cui:gridPager gridId="gridId_zfZwdm" />
	</cui:grid>
</div>
<script>
$.parseDone(function() {
	var url = "${ctx}/zfxx/zfZwdm/search";
	var params = {};
	
	$('#gridId_zfZwdm').grid('option', 'postData', params);
	$("#gridId_zfZwdm").grid("reload", url);
});

var gridId_zfZwdm_colModelDate = [{
	label : "监狱名称",
	name : "corpname",
	width : "95", 
	align : "left"
}, {
	label : "监区名称",
	name : "deptname",
	width : "95", 
	align : "left"
}, {
	label : "点名类型",
	align : "left",
	name : "dmlx",
	width : "85"
}, {
	label : "总人数",
	name : "zrs",
	width : "75",
	align : "left"
}, {
	label : "实到人数",
	align : "left",
	name : "sdrs",
	width : "85"
}, {
	label : "缺勤人数",
	align : "left",
	name : "qqrs",
	width : "85"
}, {
	label : "点名时间",
	align : "left",
	name : "dtdmsj",
	width : "85"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_zfZwdm').grid('option', 'postData', formData);
	$("#gridId_zfZwdm").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId_query").form("reset");
}
</script>
