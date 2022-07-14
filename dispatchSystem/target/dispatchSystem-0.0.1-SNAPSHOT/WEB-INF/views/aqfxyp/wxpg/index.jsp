<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height: 100%; margin: 0px 10px;">

	<cui:form id="formId_yrzq_List">
		<table class="table">
			<tr>
				<th>评估等级：</th>
				<td><cui:combobox id="dataType" name="dataType" data="comboboxData_sjly"></cui:combobox></td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="searchjhrc"></cui:button>
				<td>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<cui:grid id="gridId_wxpg_List" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="zfxm" align="center">罪犯姓名</cui:gridCol>
			<cui:gridCol name="zfbh" align="center">罪犯编号</cui:gridCol>
			<cui:gridCol name="pgdw" align="center">危险等级</cui:gridCol>
			<cui:gridCol name="blwxpgdf" align="center">暴力攻击倾向性</cui:gridCol>
			<cui:gridCol name="zswxpgdf" align="center">自杀倾向性</cui:gridCol>
			<cui:gridCol name="ttwxpgdf" align="center">脱逃倾向性</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_wxpg_List" />
	</cui:grid>
	
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var comboboxData_PlanDetail = <%=CodeFacade.loadCode2Json("4.20.60")%>;   //日程事由
	var comboboxData_sjly=[{value:'0',text:'全部等级'},{value:'1',text:'高等级'},{value:'2',text:'中等级'},{value:'3',text:'低等级'}];
	
	$.parseDone(function() {
		var zt = '${zt}';
		$("#gridId_wxpg_List").grid("reload","${ctx}/aqfxyp/wxpg/searchListPage?zt="+zt); 
	});
	
	function searchjhrc() {
		var zt = $("#dataType").combobox("getValue");
		$("#gridId_wxpg_List").grid("reload");
		$("#gridId_wxpg_List").grid("reload","${ctx}/aqfxyp/wxpg/searchListPage?zt="+zt); 
	};
	
	function clear() {
		$("#formId_yrzq_List").form("reset");
	};
</script>