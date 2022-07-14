<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_criminal_List">
		<table class="table">
			<tr>
				<th>罪犯姓名 ：</th>
				<td><cui:input id="zfxm" name="title"></cui:input></td>
				<th>罪犯编号 ：</th>
				<td><cui:input id="zfbh" name="title"></cui:input></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="searchCriminal"></cui:button>
				</td>
				<td>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
		
	<cui:grid id="gridId_criminal_List"  pager="true" rownumbers="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="bh" align="center">罪犯编号</cui:gridCol>
			<cui:gridCol name="xm" align="center">罪犯姓名</cui:gridCol>
			<cui:gridCol name="qy" align="center">所在区域</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_criminal_List" />
	</cui:grid>
	
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	$.parseDone(function() {
		searchCriminal();
	});
	
	function searchCriminal() {
		var deptName = '${deptName}';
		var xm = $("#zfxm").textbox('getValue');
		var bh = $("#zfbh").textbox('getValue');
		$("#gridId_criminal_List").grid("reload","${ctx}/criminal/searchListPage?deptName="+deptName+"&xm="+xm+"&bh="+bh);
	}
	
	function clear() {
		$("#formId_criminal_List").form("reset");
	}
	
	
	</script>