<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_bcgl_edit" heightStyle="fill">
		<cui:input type='hidden' name="id" />
		<cui:input type='hidden' name="dorCusNumber" />
		<cui:input type='hidden' name="dorStatus" />
		<table class="table" style="width: 98%">
			<tr>
				<th>班次名称：</th>
				<td>
					 <cui:input id="jobName" componentCls="form-control" name="dorDutyOrderName" required="true"></cui:input> 
					<%-- <cui:combobox id ="dorDutyOrderName"  name="dorDutyOrderName" data="combox_dorDutyOrderName" required="true" onChange="setDatepickerValue" ></cui:combobox> --%>
				</td>
			</tr>
			<tr>
				<th>起始时间：</th>
				<td>
					<cui:datepicker componentCls="form-control" model="timepicker" id="startTime" name="dorStartTime" required="true" timeFormat="HH:mm"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<cui:datepicker componentCls="form-control" model="timepicker" id="endTime" name="dorEndTime" startDateId="startTime" required="true" timeFormat="HH:mm"></cui:datepicker>
				</td>
			</tr>
			<tr>
				<th>统计类别：</th>
				<td>
					<cui:combobox componentCls="form-control" name="dorStatisticsType" data="tjlb_data" required="true"></cui:combobox>
				</td>
			</tr>
		</table>
	</cui:form>
</div>

<script>
	$.parseDone(function() {
		var id = '${id}';
		if (id) {
			loadForm("formId_bcgl_edit", "${ctx}/zbgl/bcgl/getById?id=" + id,
					function(data) {
					});
		}
	});
	
	function setDatepickerValue() {
		var dorDutyOrderName =	$("#dorDutyOrderName").combobox("getValue");
		if(dorDutyOrderName=="早班"){
			 $("#startTime").datepicker("setValue", "08:00");
			 $("#endTime").datepicker("setValue", "16:00");
		}else if(dorDutyOrderName=="中班"){
			$("#startTime").datepicker("setValue", "16:00");
			 $("#endTime").datepicker("setValue", "23:00");
		}else if(dorDutyOrderName=="晚班"){
			$("#startTime").datepicker("setValue", "23:00");
			 $("#endTime").datepicker("setValue", "08:00");
		}else{
			$("#startTime").datepicker("setValue", "08:00");
			 $("#endTime").datepicker("setValue", "08:00");
		}
	}
</script>