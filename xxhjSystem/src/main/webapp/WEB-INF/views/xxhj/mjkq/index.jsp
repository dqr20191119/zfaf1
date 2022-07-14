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
				<th>姓名：</th>
				<td>
					<cui:input id="xm" name="xm" componentCls="form-control"></cui:input>
				</td>
				<th>考勤日期：</th>
				<td>
					<cui:datepicker componentCls="form-control" id="kqrq" name="kqrq" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_mjkq" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_mjkq_colModelDate">
		<cui:gridPager gridId="gridId_mjkq" />
	</cui:grid>
</div>
<script>
$.parseDone(function() {
	var url = "${ctx}/xxhj/mjkq/search";
	var params = {};
	
	$('#gridId_mjkq').grid('option', 'postData', params);
	$("#gridId_mjkq").grid("reload", url);
});

var gridId_mjkq_colModelDate = [{
	label : "部门名称",
	name : "bm",
	width : "95", 
	align : "center"
}, {
	label : "姓名",
	name : "xm",
	width : "95", 
	align : "center"
}, {
	label : "考勤日期",
	name : "kqrq",
	width : "75",
	align : "center"
}, {
	label : "考勤时间",
	align : "center",
	name : "kssj",
	width : "85"
}, {
	label : "在监时长（小时）",
	align : "center",
	name : "sbgh",
	width : "85"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_mjkq').grid('option', 'postData', formData);
	$("#gridId_mjkq").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId_query").form("reset");
}
</script>
