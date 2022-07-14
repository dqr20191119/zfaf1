<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<div>	
	<cui:form id="formId_zbrz_edit">
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
			  <td colspan="6" style="color: red">-------------------------------------------------------------日志填写------------------------------------------------------------------------------------</td>
			</tr>
			<tr>
			  <td><label>时间：</label></td>
			  <td><cui:datepicker id ="time" name ="time"  dateFormat="yyyy-MM-dd HH:mm:ss" minDate="new Date()"></cui:datepicker></td>
			  <td><label>地点：</label></td>
			  <td colspan="3"><cui:input  name="location"></cui:input></td>	
			</tr>
			<tr>
			  <td><label>详情描述：</label></td>
			  <td  colspan="5"><cui:textarea  name="zbDetial"  width="800"></cui:textarea></td>	
			</tr>
			<tr>
			  <td><label>遗留问题：</label></td>
			  <td colspan="5"><cui:textarea  name="ylWt" width="800"></cui:textarea></td>	
			</tr>
			<tr>
			  <td><label>执勤民警：</label></td>
			  <td ><cui:input  name="cjrName" value="${cjrName }"  readonly="true"></cui:input></td>
			  <td><label>填写日期：</label></td>
			  <td colspan="3"><cui:input  name="cjrq"  value="${cjrq }" readonly="true"></cui:input></td>	
			</tr>
		</table>
	</cui:form>
</div>
	
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	var id = '${id}';                      
	//var zbYf = '${zbYf}';  
	//var zbDh = '${zbDh}';
	$.parseDone(function() {
		if(id) {
			loadForm("formId_zbrz_edit", "${ctx}/zbgl/jjb/getById?id="+id, function(data) {
               
			});
			//$("#edit_zbYf").datepicker("option","readonly",true);
		}
	});
	
	
	

</script>