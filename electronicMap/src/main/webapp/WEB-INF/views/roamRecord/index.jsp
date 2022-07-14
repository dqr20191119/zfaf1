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
				<th>巡视人：</th>
				<td>
					<cui:input id="roamUserName" name="roamUserName" componentCls="form-control"></cui:input>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_roamRecord" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" 
			  colModel="gridId_roamRecord_colModelDate">
		<cui:gridPager gridId="gridId_roamRecord" />
	</cui:grid>
</div>
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/roamRecord/search";
	var params = {};
	
	$('#gridId_roamRecord').grid('option', 'postData', params);
	$("#gridId_roamRecord").grid("reload", url);
});

var gridId_roamRecord_colModelDate = [{
	label : "id",
	name : "ID",
	width : "70",
	hidden : true
}, {
	label : "巡视路线",
	name : "roamPathName",
	width : "95", 
	align : "left"
}, {
	label : "巡视部门",
	name : "roamDeptName",
	width : "95", 
	align : "left"
}, {
	label : "巡视人",
	name : "roamUserName",
	width : "75",
	align : "left"
}, {
	label : "关联大屏预案",
	align : "left",
	name : "screenPlanName",
	width : "85"
}, {
	label : "巡视时间",
	align : "left",
	name : "roamTime",
	width : "85"
}];

/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#formId_query").form("formData");
	$('#gridId_roamRecord').grid('option', 'postData', formData);
	$("#gridId_roamRecord").grid("reload");
}

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#formId_query").form("reset");
}
</script>
