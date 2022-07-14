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
 	<cui:form id="jcjlForm">
		<table class="table">
			<tr>
				<th>姓名：</th>
				<td>
					<cui:input id="zfxm" name="zfxm" componentCls="form-control"></cui:input>
				</td>
				<th>进出时间：</th>
				<td>
					<cui:datepicker componentCls="form-control" id="startTime" name="startTime" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>
				<th align="center">至</th>
				<td>
					<cui:datepicker componentCls="form-control" name="endTime" id ="endTime" startDateId="startTime" dateFormat="yyyy-MM-dd"></cui:datepicker>
				</td>
				<td>
					<cui:button label="查询" onClick="query"></cui:button>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>

 	<cui:grid id="jcjlGrid" rownumbers="true" singleselect="false" fitStyle="fill" width="auto" colModel="jcjlColModel">
		<cui:gridPager gridId="jcjlGrid" />
	</cui:grid>
</div>
 
<script>
var jsConst = window.top.jsConst;

var cusNumber = jsConst.CUS_NUMBER;//监狱号

var userId = jsConst.USER_ID;//当前登陆者

$.parseDone(function() {
	var url = "${ctx}/jctj/jcjl/findByPage";
	var params = {};
	
	$('#jcjlGrid').grid('option', 'postData', params);
	$("#jcjlGrid").grid("reload", url);
});

var jcjlColModel = [{
		label : "id",
		name : "id",
		width : "70",
		hidden : true
	}, {
		label : "姓名",
		name : "zfXm",
		width : "95", 
		align : "center"
	}, {
		label : "监区",
		name : "jqName",
		width : "75",
		align : "center"
	}, {
		label : "场所",
		align : "center",
		name : "cs",
		width : "85"
	}, {
		label : "时间",
		align : "center",
		name : "time",
		width : "85"
	}];


/**
 * 查询按钮时间
 */
function query() {
	var formData = $("#jcjlForm").form("formData");
	$('#jcjlGrid').grid('option', 'postData', formData);
	$("#jcjlGrid").grid("reload");
};

/**
 * 重置按钮事件
 */
var reset = function() {
	$("#jcjlForm").form("reset");
};


</script>
