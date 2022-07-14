<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
	<cui:form id="formId_jhrc_edit">
		<cui:input type='hidden' name="id" />
 		<table class="table">
			<tr>
				<th style="text-align:right">部门：</th>
				<td><cui:combobox id="comboboxId_edit_jhrcJq" name="cpsDrpmntId" required="true" width="250"></cui:combobox></td>	
			</tr>
			<tr>			
				<th style="text-align:right">计划开始时间：</th>
				<td><cui:datepicker  id="cpsScheduleTime" name="cpsScheduleTime" model="timepicker" required="true" width="250"></cui:datepicker ></td>
			</tr>
			<tr>			
				<th style="text-align:right">计划结束时间：</th>
				<td><cui:datepicker  id="cpsScheduleEndTime" name="cpsScheduleEndTime" model="timepicker" required="true" width="250"></cui:datepicker ></td>
			</tr>
			<tr>		
				<th style="text-align:right">事由：</th>
				<td><cui:combobox id="cpsPlanDetail" name="cpsPlanDetail" data="comboboxData_PlanDetail" required="true" width="250"></cui:combobox></td>
			</tr>
			<tr>		
				<th style="text-align:right">生效日期：</th>
				<td><cui:datepicker id="cpsStartDate" name="cpsStartDate" dateFormat="yyyy-MM-dd" required="true" width="250"></cui:datepicker></td>
			</tr>
			<tr>		
				<th style="text-align:right">结束日期：</th>
				<td><cui:datepicker id="cpsEndDate" name="cpsEndDate" startDateId="cpsStartDate" dateFormat="yyyy-MM-dd" width="250"></cui:datepicker></td>
			</tr>
			<tr>
				<th style="text-align:right">路线选择：</th>
				<td><cui:combobox id="cpsLx" name="cpsLx" width="250"></cui:combobox></td>
			</tr>
			<tr>			
				<th style="text-align:right">通知时间：</th>
				<td><cui:datepicker  id="tzsj" name="tzsj" model="timepicker" width="250"></cui:datepicker ></td>
			</tr>
			<tr>			
				<th style="text-align:right">通知内容：</th>
				<td id = "ss"><cui:textarea id="tznr" name="tznr" componentCls="form-control"></cui:textarea></td>
			</tr>
			<tr>
				<th style="text-align:right">通知设备：</th>
				<td ><cui:combobox id = "tzsb" name="tzsb" data="comboboxData_tzsb" multiple = "true"></cui:combobox></td> 
			</tr>
		</table>
	</cui:form>
</div>

<script>
	$.parseDone(function() {
		var cusNumber = jsConst.CUS_NUMBER;
		var id = '${id}';
		$("#comboboxId_edit_jhrcJq").combobox("reload", "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
        $("#cpsLx").combobox("reload", "${ctx}/prisonPath/findPathInfoForCombobox.json?cusNumber=" + cusNumber);
        if(USER_LEVEL == '3') {
			
			$("#comboboxId_edit_jhrcJq").combobox("setValue",drpmntId);
			$("#comboboxId_edit_jhrcJq").combobox("option","readonly",true);
		}
		
		if(id) {
			loadForm("formId_jhrc_edit", "${ctx}/xxhj/jhrc/getById?id="+id, function(data) {
			});
		} else {
			$("#cpsStartDate").datepicker("option","minDate", new Date());
		}
	});
</script>
