<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>

<div style="height: 100%; margin: 0px 10px;">
<cui:form id="formId_rzcx_query">
		<table class="table">
			<tr>
			
				<th>从：</th>
				<td><cui:datepicker  id="StartTime" name="StartTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>到：</th>
				<td><cui:datepicker  id="EndTime" name="EndTime" dateFormat="yyyy-MM-dd HH:mm:ss" width="190"></cui:datepicker ></td>
				<th>查询：</th>
				<td><cui:input id="SearchDate" type="text"  placeholder="请输入操作,IP或URL" name="SearchDate"  ></cui:input></td>
				<td><cui:button label="查询" componentCls="btn-primary"
						onClick="search" /></td>
				<td><cui:button label="重置" onClick="reset"></cui:button></td>
			</tr>

			
		</table>
	</cui:form>

	<cui:grid id="gridId_rzcx" rownumbers="true" multiselect="true"
		width="auto" fitStyle="fill" url="${ctx}/xxhj/rzcx/searchSwdbPage1"
		rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" align="center" hidden="hidden"></cui:gridCol>
			<cui:gridCol name="opTarget" align="center">操作</cui:gridCol>
			<cui:gridCol name="result" align="center">结果</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rzcx" />
	</cui:grid>
	 
</div>

<script>
	$.parseDone(function() {
		search();
	});

	function search() {
		var formData = $("#formId_rzcx_query").form("formData");
		var STime =$("#StartTime").datepicker("getValue");
		var ETime =$("#EndTime").datepicker("getValue");
		var SearchDate=$("#SearchDate").textbox("getValue");
		$("#gridId_rzcx").grid("option", "postData", formData);
		$("#gridId_rzcx").grid("reload");
		$("#gridId_rzcx").grid("reload","${ctx}/xxhj/rzcx/searchSwdbPage1?STime="+STime+"&ETime="+ETime+"&SearchDate="+SearchDate);
	}

	function reset() {
		$("#formId_rzcx_query").form("reset");
	}

	
</script>