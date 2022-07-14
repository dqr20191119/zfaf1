<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<div>
	<cui:form id="formId_jjb_zbrz_edit">
		<cui:input type='hidden' name="id" />
 		<table class="table" style="width: 100%">
			<tr>
				<td><label>班次：</label></td>
				<td ><cui:input id="orderName" name="orderName" readonly="true"></cui:input></td>
				<td><label>值班日期：</label></td>
				<td colspan="3"><cui:input id="dutyDate" name="dutyDate" readonly="true" ></cui:input></td>								
			</tr>
			<tr>	
				<td><label>值班长：</label></td>
				<td><cui:input  name="zbzName" readonly="true"></cui:input></td>	
				<td><label>值班员：</label></td>
				<td><cui:input  name="zbyName" readonly="true"></cui:input></td>
				<td><label>值班时间：</label></td>
				<td><cui:input  name="dutyTime" readonly="true"></cui:input></td>					
			</tr>
			<tr>
			  <td colspan="6" style="color: red">---------------------------------------------------------------------值班日志汇总----------------------------------------------------------------------------------------------</td>
			</tr>
		</table>
	</cui:form>
	<div style="width: 100%;height: 550px">
		<cui:grid id="gridId_jjb_rzhz_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20"  >
			<cui:gridCols>
				<cui:gridCol name="zbDetial" align="center" formatter="textarea" formatoptions="{'readonly' : true}" >值班记录详情</cui:gridCol>
				<cui:gridCol name="ylWt" align="center" formatter="textarea" formatoptions="{'readonly' : true}">遗留问题</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_jjb_rzhz_query" />
		</cui:grid>
	</div>
</div>
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var parma = '${parma}'
	var id = '${id}';                     
	//var zbYf = '${zbYf}';  
	//var zbDh = '${zbDh}';
	$.parseDone(function() {
		if(id) {
			loadForm("formId_jjb_zbrz_edit", "${ctx}/zbgl/jjb/getById?id="+id, function(data) {
               
			});
			$("#gridId_jjb_rzhz_query").grid("reload","${ctx}/zbgl/jjb/searchRzData?id="+id); 
			
		}
	});
	
</script>